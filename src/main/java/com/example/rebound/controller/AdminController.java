package com.example.rebound.controller;


import com.example.rebound.dto.MemberDTO;
import com.example.rebound.repository.NoticeDAO;
import com.example.rebound.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
@Slf4j
@Controller
@RequestMapping("/admin/**")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final HttpSession session;

//    관리자 로그인페이지 이동
    @GetMapping("admin-login")
    public String goToAdminLogin(@CookieValue(name="remember", required = false) boolean remember,
                                 @CookieValue(name="remember_member_email", required = false) String rememberedEmail,
                                 HttpServletRequest request,
                                 MemberDTO memberDTO,
                                 Model model){
        session.removeAttribute("member");
        memberDTO.setRemember(remember);
        memberDTO.setMemberEmail(rememberedEmail);
        model.addAttribute("memberDTO", memberDTO);
        return "admin/admin-login";
    }
//    로그인 성공시 관리자 메인 페이지 이동
    @PostMapping("admin-login")
    public RedirectView adminLogin(MemberDTO memberDTO, HttpServletResponse response, RedirectAttributes redirectAttributes){
        if(!(adminService.checkAdmin(memberDTO)==null)){
            MemberDTO member =adminService.checkAdmin(memberDTO);
            session.setAttribute("member", member);
            Cookie rememberMembmerEmailCookie = new Cookie("remember_member_email", memberDTO.getMemberEmail());
            Cookie rememberCookie = new Cookie("remember", String.valueOf(memberDTO.isRemember()));

            rememberMembmerEmailCookie.setPath("/");
            rememberCookie.setPath("/");

            if(memberDTO.isRemember()){
                rememberMembmerEmailCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
                response.addCookie(rememberMembmerEmailCookie);

                rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
                response.addCookie(rememberCookie);

            } else{
                rememberMembmerEmailCookie.setMaxAge(0); // 30일 유지
                response.addCookie(rememberMembmerEmailCookie);

                rememberCookie.setMaxAge(0); // 30일 유지
                response.addCookie(rememberCookie);
            }

            return new RedirectView("admin/admin-mainPage");
        };return new RedirectView("admin/admin-login");
    }
//    관리자 메인페이지 이동
    @GetMapping("admin-mainPage")
    public String goToAdminMainPage(){
        return "admin/admin-mainPage";
    }
//    관리자 공지사항 목록 이동
    @GetMapping("notice-list/{page}")
    public String goToNoticeList(){
        return "admin/notice-list";
    }
//    관리자 공지사항 상세 이동
    @GetMapping("notice-detail")
    public String goToNoticeDetail(int id, Model model){
        System.out.println(adminService.noticeDetail(id));
        model.addAttribute("notice",adminService.noticeDetail(id));
        System.out.println(model);
        return "/admin/notice-detail";
    }
//    상담하기 목록 이동
    @GetMapping("counseling-list/{page}")
    public String goToCounselingList(){
        return "admin/counseling-list";
    }
//    상담사 댓글 이동
    @GetMapping("counselor-apply/{page}")
    public String goToCounselingApply(){
        return "admin/counselor-apply";
    }
//    상담사 목록 이동
    @GetMapping("counselor-list/{page}")
    public String goToCounselorList(){
        return "admin/counselor-list";
    }
//    일반 멤버 목록 이동
    @GetMapping("general-memberlist/{page}")
    public String goToGeneralMemberList(){

        return "admin/general-memberlist";
    }
//    멘토 목록 이동
    @GetMapping("mentor-memberlist/{page}")
    public String goToMentorMemberList(){
        return "admin/mentor-memberlist";
    }
//    구독자 목록 이동
    @GetMapping("subscribe-memberlist/{page}")
    public String goToSubscribeMemberList(){
        return "admin/subscribe-memberlist";
    }
}
