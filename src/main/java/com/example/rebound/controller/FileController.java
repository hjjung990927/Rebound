package com.example.rebound.controller;

import com.example.rebound.dto.FileDTO;
import com.example.rebound.service.CounselorService;
import com.example.rebound.service.FileService;
import com.example.rebound.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/**")
public class FileController {
    private final CounselorService counselorService;
    private final MemberService memberService;
    private final FileService fileService;

    @GetMapping("/display")
    public ResponseEntity<Resource> display(
            @RequestParam String filePath,
            @RequestParam String fileName
    ) {
        try {
            String fullPath = "/home/ubuntu/upload_file/" + filePath + "/" + fileName;
            File file = new File(fullPath);

            if (!file.exists() || !file.isFile()) {
                log.warn("파일 없음: {}", fullPath);
                File defaultFile = new File("src/main/resources/static/images/member/no-profile.png");

                if (!defaultFile.exists()) {
                    log.error("기본 이미지 없음: {}", defaultFile.getAbsolutePath());
                    return ResponseEntity.notFound().build();
                }

                Resource defaultResource = new FileSystemResource(defaultFile);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                        .body(defaultResource);
            }

            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()))
                    .body(resource);

        } catch (Exception e) {
            log.error("파일 표시 에러", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMemberProfileFile(@PathVariable Long id) {
        try {
            memberService.deleteProfile(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("회원 프로필 삭제 실패: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete-counselor/{id}")
    public ResponseEntity<Void> deleteCounselorProfileFile(@PathVariable Long id) {
        try {
            counselorService.deleteProfile(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("상담사 프로필 삭제 실패: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/profile/upload")
    public ResponseEntity<FileDTO> uploadProfile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("memberId") Long memberId) throws IOException {
        if(fileService.findFileByMemberId(memberId).isPresent()) {
            memberService.deleteProfile(memberId);
        }
        FileDTO fileDTO = memberService.saveProfileFile(file, memberId);
        return ResponseEntity.ok(fileDTO);
    }

    @PostMapping("/profile-counselor/upload")
    public ResponseEntity<FileDTO> uploadCounselorProfile(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("counselorId") Long counselorId) throws IOException {
        if(counselorService.showFileById(counselorId).isPresent()) {
            counselorService.deleteProfile(counselorId);
        }
        FileDTO fileDTO = counselorService.saveCounselorProfileFile(file, counselorId);
        return ResponseEntity.ok(fileDTO);
    }
}
