package com.example.rebound.controller;

import com.example.rebound.common.exception.LoginFailException;
import com.example.rebound.dto.*;
import com.example.rebound.repository.FileDAO;
import com.example.rebound.repository.MemberDAO;
import com.example.rebound.service.CounselorService;
import com.example.rebound.service.FileService;
import com.example.rebound.service.KakaoService;
import com.example.rebound.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final CounselorService counselorService;
    private final MemberDTO memberDTO;
    private final HttpSession session;
    private final FileDTO fileDTO;
    private final FileDAO fileDAO;
    private final FileService fileService;
    private final KakaoService kakaoService;
    private final MemberDAO memberDAO;

//    회원가입 페이지로 이동
    @GetMapping("join")
    public String goToMemberJoin(MemberDTO memberDTO, Model model) {
        model.addAttribute("memberDTO", memberDTO);
        return "member/join-user";
    }

//    회원가입 완료
    @PostMapping("join")
    public RedirectView MemberJoin(MemberDTO memberDTO) {
        memberService.joinMember(memberDTO);
        return new RedirectView("/member/login");
    }

//    이메일 중복 검사
    @PostMapping("check-email")
    @ResponseBody
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> member){
        String memberEmail = member.get("memberEmail");

        boolean isExist = memberService.isExistMemberEmail(memberEmail);
        Map<String, Object> result = new HashMap<>();
        result.put("memberEmail", memberEmail);
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
    public ResponseEntity<?> checkPhoneNumber(@RequestBody Map<String, String> member){
        String memberPhoneNumber = member.get("memberPhoneNumber");

        boolean isExist = memberService.isExistMemberPhoneNumber(memberPhoneNumber);
        Map<String, Object> result = new HashMap<>();
        result.put("memberPhoneNumber", memberPhoneNumber);
        result.put("isExist", isExist);

//        System.out.println("전화번호 체크 실행");
        if(isExist){
//            System.out.println("이미 존재하는 전화번호");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return ResponseEntity.ok().body(result);
    }

//    상담사 회원가입 페이지로 이동
    @GetMapping("join-counselor")
    public String goToCounselorJoin(CounselorDTO counselorDTO, CounselorQualificationsFileDTO counselorQualificationsFileDTO, Model model) {
        model.addAttribute("counselorDTO", counselorDTO);
        model.addAttribute("counselorQualificationsFileDTO", counselorQualificationsFileDTO);
        return "member/join-counselor";
    }

//    로그인 페이지로 이동
    @GetMapping("login")
    public String goToLogin(MemberDTO memberDTO, Model model) {
        model.addAttribute("memberDTO", memberDTO);
        return "member/login-user";
    }

//    로그인 완료
    @PostMapping("login")
    public String Login(MemberDTO memberDTO, Model model) {
        MemberDTO member = memberService.login(memberDTO).orElseThrow(LoginFailException::new);
        memberDAO.setLatelyDate(memberDTO.getMemberEmail());
        session.setAttribute("member", member);
        model.addAttribute("member", member);
        model.addAttribute("file", fileService.findFileByMemberId(member.getId()));
        return "redirect:/";
    }

//    이메일 찾기
    @GetMapping("find-email")
    public String goToFindEmail(){
        return "member/find-email";
    }

    //    인증번호 성공
    @PostMapping("/find-email-ok")
    public String findEmailOk(@RequestParam("memberPhoneNumber") String phoneNumber, Model model) {
        MemberDTO member = memberService.findEmailByPhone(phoneNumber);
        CounselorDTO counselor = counselorService.findEmailByPhone(phoneNumber);

        if (member != null) {
            model.addAttribute("memberEmail", member.getMemberEmail());
            String[] word1 = member.getCreatedDate().split(" ");
            model.addAttribute("createdDate", word1[0]);
        }

        if (counselor != null) {
            model.addAttribute("counselorEmail", counselor.getCounselorEmail());
            String[] word2 = counselor.getCreatedDate().split(" ");
            model.addAttribute("createdDate", word2[0]);
        }

        return "member/find-email-ok";
    }

//    비밀번호 찾기
    @GetMapping("find-password")
    public String goToFindPassword() {
        return "member/find-password";
    }

//    이메일 확인 안내
    @GetMapping("find-confirm")
    public String goToConfirm() {
        return "member/find-confirm";
    }

//    새 비밀번호 변경
    @GetMapping("new-password")
    public String goToNewPassword(){
        return "member/new-password";
    }



    @GetMapping("mypage")
    public String goToMyPage(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/member/login";
        }

        Optional<MemberDTO> fullMemberOpt = memberService.showFileById(member.getId());

        if (fullMemberOpt.isEmpty()) {
            model.addAttribute("member", member);
            model.addAttribute("file", null);
            return "member/mypage";
        }
        MemberDTO fullMember = fullMemberOpt.get();
        if (fullMemberOpt.isPresent()) {
            model.addAttribute("member", fullMember);
            model.addAttribute("file", fullMember.getFile());
        } else {
            model.addAttribute("file", null);
        }

        return "member/mypage";
    }

    @PostMapping("update/name")
    public RedirectView updateMemberName(MemberDTO memberDTO, HttpSession session) {
//        System.out.println(memberDTO.getMemberName());
        MemberDTO member=(MemberDTO) session.getAttribute("member");
        memberDTO.setId(member.getId());
        memberService.memberRename(memberDTO);
        member.setMemberName(memberDTO.getMemberName());
        session.setAttribute("member", member);
        return new RedirectView("/member/mypage/set");
    }

    //    전화번호 수정
    @PostMapping("update/phoneNumber")
    public RedirectView updatePhone(MemberDTO memberDTO, HttpSession session){
//        System.out.println("메소드 들어옴");
        MemberDTO member=(MemberDTO) session.getAttribute("member");
        memberDTO.setId(member.getId());
        memberService.updateMemberPhoneNumber(memberDTO);
        member.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
        session.setAttribute("member", member);
        return new RedirectView("/member/mypage/info");
    }
//    이메일 수정
    @PostMapping("update/email")
    public RedirectView updateEmail(MemberDTO memberDTO, HttpSession session){
        MemberDTO member=(MemberDTO) session.getAttribute("member");
        memberDTO.setId(member.getId());
        memberService.updateMemberEmail(memberDTO);
        member.setMemberEmail(memberDTO.getMemberEmail());
        session.setAttribute("member", member);
        return new RedirectView("/member/mypage/info");
    }





    @GetMapping("mypage/info")
    public String goToMyPageInfo(){
        return "member/mypage-info";
    }
    @GetMapping("mypage/post")
    public String goToMyPagePost(){
        return "member/mypage-post";
    }
    @GetMapping("mypage/reply")
    public String goToMyPageReply(){
        return "member/mypage-reply";
    }
    @GetMapping("mypage/set")
    public String goToMyPageSet(HttpSession session, Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        Optional<MemberDTO> fullMemberOpt = memberService.showFileById(member.getId());

        if (fullMemberOpt.isEmpty()) {
            model.addAttribute("member", member);
            model.addAttribute("file", null);
            return "member/mypage-set";
        }
        MemberDTO fullMember = fullMemberOpt.get();
        model.addAttribute("member", fullMember);
        model.addAttribute("file", fullMember.getFile());
        return "member/mypage-set";
    }
    @GetMapping("mypage/consultation/history")
    public String goToAyPageConsultationHistory(){
        return "member/mypage-consultation-history";
    }
    @GetMapping("mypage-consultation/review")
    public String goToMyPageConsultationReview(){
        return "member/mypage-consultation-review";
    }

    //로그아웃
    @GetMapping("logout")
    public RedirectView logout(@CookieValue(name="access_token", required = false) String token, HttpServletResponse response){
        if(token == null){
            session.invalidate();
        }else{
            kakaoService.logout(token);
            Cookie accessTokenCookie = new Cookie("access_token", token);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(0);

            response.addCookie(accessTokenCookie);
        }
        return new RedirectView("/member/login");
    }
}
