package com.example.rebound.controller;

import com.example.rebound.common.exception.LoginFailCounselorException;
import com.example.rebound.dto.CounselorDTO;
import com.example.rebound.dto.MemberDTO;
import com.example.rebound.mapper.CounselorMapper;
import com.example.rebound.repository.CounselorDAO;
import com.example.rebound.service.CounselorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counselor/**")
public class CounselorController {
    private final CounselorService counselorService;
    private final HttpSession session;
    private final CounselorDAO counselorDAO;
    private final CounselorDTO counselorDTO;

    //    상담사 회원가입 페이지로 이동
    @GetMapping("join")
    public String goToCounselorJoin(CounselorDTO counselorDTO, Model model) {
        model.addAttribute("counselorDTO", counselorDTO);
        return "member/join-counselor";
    }

    //    이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> counselor){
        String counselorEmail = counselor.get("counselorEmail");

        boolean isExist = counselorService.isExistCounselorEmail(counselorEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("counselorEmail", counselorEmail);
        result.put("isExist", isExist);

//        System.out.println("이메일 체크 실행");
        if(isExist){
//            System.out.println("이미 존재하는 이메일");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return ResponseEntity.ok().body(result);
    }

    //    전화번호 중복 검사
    @PostMapping("check-phone-number")
    @ResponseBody
    public ResponseEntity<?> checkPhoneNumber(@RequestBody Map<String, String> counselor){
        String counselorPhoneNumber = counselor.get("counselorPhoneNumber");

        boolean isExist = counselorService.isExistCounselorPhoneNumber(counselorPhoneNumber);
        Map<String, Object> result = new HashMap<>();
        result.put("counselorPhoneNumber", counselorPhoneNumber);
        result.put("isExist", isExist);

//        System.out.println("전화번호 체크 실행");
        if(isExist){
//            System.out.println("이미 존재하는 전화번호");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return ResponseEntity.ok().body(result);
    }

//    회원가입 완료
    @PostMapping("join")
    public RedirectView joinCounselor(CounselorDTO counselorDTO, @RequestParam("file") List<MultipartFile> counselorQualificationsFiles) {
        counselorService.joinCounselor(counselorDTO, counselorQualificationsFiles);
        return new RedirectView("/counselor/login");
    }

    //    로그인 페이지로 이동
    @GetMapping("login")
    public String goToLogin(CounselorDTO counselorDTO, Model model) {
//        로그인 페이지 이동
        model.addAttribute("counselorDTO", counselorDTO);
        return "member/login-counselor";
    }

    //    로그인 완료
    @PostMapping("login")
    public String Login(CounselorDTO counselorDTO) {
        CounselorDTO counselor=counselorService.login(counselorDTO).orElseThrow(LoginFailCounselorException::new);
        session.setAttribute("counselor", counselor);
        counselorDAO.setLatelyDate(counselorDTO.getCounselorEmail());
        return "redirect:/"; }

//    마이페이지로 이동
    @GetMapping("mypage")
    public String goToMyPageCounselor(HttpSession session, Model model) {
        CounselorDTO counselor = (CounselorDTO) session.getAttribute("counselor");
        if (counselor == null) {
            return "redirect:/counselor/login";
        }

        Optional<CounselorDTO> fullCounselorOpt = counselorService.showFileById(counselor.getId());

        if (fullCounselorOpt.isEmpty()) {
            model.addAttribute("counselor", counselor);
            model.addAttribute("file", null);
            return "member/counselor-mypage";
        }
        CounselorDTO fullCounselor = fullCounselorOpt.get();
        if (fullCounselorOpt.isPresent()) {
            model.addAttribute("counselor", fullCounselor);
            model.addAttribute("file", fullCounselor.getFile());
        } else {
            model.addAttribute("file", null);
        }
        return "member/counselor-mypage";
    }
    @GetMapping("mypage/info")
    public String goToMyPageCouselorInfo(){
        return "member/mypage-counselor-info";
    }
//    @GetMapping("mypage/post")
//    public String goToMyPageCounselorPost(){
//        return "member/mypage-counselor-post";
//    }
//    @GetMapping("mypage/reply")
//    public String goToMyPageCounselorReply(){
//        return "member/mypage-counselor-reply";
//    }
    @GetMapping("mypage/set")
    public String goToMyPageCounselorSet(HttpSession session, Model model) {
        CounselorDTO counselor = (CounselorDTO) session.getAttribute("counselor");
        Optional<CounselorDTO> fullCounselorOpt = counselorService.showFileById(counselor.getId());

        if (fullCounselorOpt.isEmpty()) {
            model.addAttribute("counselor", counselor);
            model.addAttribute("file", null);
            return "member/mypage-counselor-set";
        }
        CounselorDTO fullCounselor = fullCounselorOpt.get();
        model.addAttribute("counselor", fullCounselor);
        model.addAttribute("file", fullCounselor.getFile());
        return "member/mypage-counselor-set";
    }
//    @GetMapping("mypage/consultation/history")
//    public String goToMyPageCounselorConsultationHistory(){
//        return "member/mypage-counselor-consultation-history";
//    }

//    상담사 이름 변경
    @PostMapping("update/name")
    public RedirectView RenameCounselor(CounselorDTO counselorDTO, HttpSession session) {
        CounselorDTO counselor = (CounselorDTO) session.getAttribute("counselor");
        counselorDTO.setId(counselor.getId());
        counselorService.counselorRename(counselorDTO);
        counselor.setCounselorName(counselorDTO.getCounselorName());
        session.setAttribute("counselor", counselor);
        return new RedirectView("/counselor/mypage/set");
    }

//        상담사 이메일 변경
    @PostMapping("update/email")
    public RedirectView updateCounselorEmail(CounselorDTO counselorDTO, HttpSession session){
        CounselorDTO counselor=(CounselorDTO) session.getAttribute("counselor");
        counselorDTO.setId(counselor.getId());
        counselorService.updateCounselorEmail(counselorDTO);
        counselor.setCounselorEmail(counselorDTO.getCounselorEmail());
        session.setAttribute("counselor", counselor);
        return new RedirectView("/counselor/mypage/info");
    }

//    상담사 전화번호 변경
    @PostMapping("update/phoneNumber")
    public RedirectView updateCounselorPhoneNumber(CounselorDTO counselorDTO, HttpSession session){
        CounselorDTO counselor=(CounselorDTO) session.getAttribute("counselor");
        counselorDTO.setId(counselor.getId());
        counselorService.updateCounselorPhoneNumber(counselorDTO);
        counselor.setCounselorPhoneNumber(counselorDTO.getCounselorPhoneNumber());
        session.setAttribute("counselor", counselor);
        return new RedirectView("/counselor/mypage/info");
    }

    //로그아웃
    @GetMapping("logout")
    public RedirectView logout(){
        session.invalidate();
        return new RedirectView("/counselor/login");
    }

}
