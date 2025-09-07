package com.example.rebound.service;

import com.example.rebound.dto.*;
import com.example.rebound.repository.CounselorDAO;
import com.example.rebound.repository.CounselorProfileFileDAO;
import com.example.rebound.repository.CounselorQualificationsFileDAO;
import com.example.rebound.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class CounselorServiceImpl implements CounselorService {
    final CounselorDAO counselorDAO;
    private final FileDAO fileDAO;
    private final CounselorQualificationsFileDAO counselorQualificationsFileDAO;
    private final CounselorProfileFileDAO counselorProfileFileDAO;

    @Override
    public void joinCounselor(CounselorDTO counselorDTO, List<MultipartFile> files) {
        String todayPath=getPath();
        String rootPath="/home/ubuntu/upload_file/"+todayPath;

        counselorDAO.saveCounselor(counselorDTO);

        files.forEach(file -> {
            if(file.getOriginalFilename().equals("")){
                return;
            }
            log.info(rootPath);
            UUID uuid = UUID.randomUUID();
            FileDTO fileDTO=new FileDTO();
            CounselorQualificationsFileDTO counselorQualificationsFileDTO=new CounselorQualificationsFileDTO();

            fileDTO.setFileName(uuid.toString()+"_"+file.getOriginalFilename());
            fileDTO.setFileOriginalName(file.getOriginalFilename());
            fileDTO.setFileContentType(file.getContentType());
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(file.getSize()));
            fileDAO.uploadFile(fileDTO);

//            System.out.println(fileDTO.getId()); => null로 나옴

            counselorQualificationsFileDTO.setCounselorId(counselorDTO.getId());
            counselorQualificationsFileDTO.setId(fileDTO.getId());
            counselorQualificationsFileDAO.saveCounselorQualificationsFile(counselorQualificationsFileDTO);

            File directory=new File(rootPath);
            if(!directory.exists()){
                directory.mkdirs();
            }

            try {
                file.transferTo(new File(rootPath, uuid.toString()+"_"+file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean isExistCounselorEmail(String counselorEmail) {
        return counselorDAO.isExistCounselorEmail(counselorEmail);
    }

    @Override
    public boolean isExistCounselorPhoneNumber(String counselorPhoneNumber) {
        return counselorDAO.isExistCounselorPhoneNumber(counselorPhoneNumber);
    }

    @Override
    public Optional<CounselorDTO> login(CounselorDTO counselorDTO) {
        return counselorDAO.findCounselorByEmailAndPassword(counselorDTO);
    }

    @Override
    public Optional<CounselorDTO> showFileById(Long counselorId) {
        Optional<CounselorDTO> counselorOpt = counselorDAO.selectCounselorById(counselorId);
        Optional<FileDTO> fileOpt = fileDAO.findFileByCounselorId(counselorId);

        if (counselorOpt.isPresent()) {
            CounselorDTO counselor = counselorOpt.get();
            fileOpt.ifPresent(counselor::setFileInfo);
            return Optional.of(counselor);
        }

        return Optional.empty();
    }

    @Override
    public void counselorRename(CounselorDTO counselorDTO) {
        counselorDAO.counselorRename(counselorDTO);
    }

    @Override
    public FileDTO saveCounselorProfileFile(MultipartFile file, Long counselorId) throws IOException {
        String todayPath = getPath();
        String rootPath = "/home/ubuntu/upload_file/" + todayPath;

        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        File directory = new File(rootPath);
        if (!directory.exists()) directory.mkdirs();

        String fileName = UUID.randomUUID().toString() + ".jpg";
        File fileToSave = new File(rootPath, fileName);

        file.transferTo(fileToSave);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(fileName);
        fileDTO.setFileOriginalName(file.getOriginalFilename() != null ? file.getOriginalFilename() : "kakao_profile.jpg");
        fileDTO.setFileContentType(file.getContentType() != null ? file.getContentType() : "image/jpeg");
        fileDTO.setFilePath(todayPath);
        fileDTO.setFileSize(String.valueOf(file.getSize()));

        fileDAO.uploadFile(fileDTO);

        CounselorProfileFileDTO profileDTO = new CounselorProfileFileDTO();
        profileDTO.setCounselorId(counselorId);
        profileDTO.setId(fileDTO.getId());
        counselorProfileFileDAO.saveCounselorProfileFile(profileDTO);

        return fileDTO;
    }

    @Override
public void deleteProfile(Long counselorId) {
    Optional<FileDTO> fileOpt = counselorProfileFileDAO.findCounselorProfileFileById(counselorId);

    if (fileOpt.isPresent()) {
        FileDTO fileDTO = fileOpt.get();

        counselorProfileFileDAO.deleteCounselorProfileById(counselorId);

        Path filePath = Paths.get(fileDTO.getFilePath() + File.separator + fileDTO.getFileName());
        try {
            Files.deleteIfExists(filePath);
            System.out.println("파일 삭제 성공: " + filePath);
        } catch (IOException e) {
            System.err.println("파일 삭제 실패: " + filePath);
            e.printStackTrace();
        }

    } else {
        System.out.println("삭제할 파일이 없습니다: counselorId = " + counselorId);
    }
}


    @Override
    public void updateCounselorPhoneNumber(CounselorDTO counselorDTO){
        counselorDAO.updateCounselorPhoneNumber(counselorDTO);
    }

    @Override
    public void updateCounselorEmail(CounselorDTO counselorDTO){
        counselorDAO.updateCounselorEmail(counselorDTO);
    }

    @Override
    public void updateCounselorPassword(String counselorPassword, String counselorEmail) {
        counselorDAO.updateCounselorPassword(counselorPassword, counselorEmail);
    }

    @Override
    public CounselorDTO findEmailByPhone(String counselorPhoneNumber) {
        return counselorDAO.selectEmailByPhoneNumber(counselorPhoneNumber);
    }
}
