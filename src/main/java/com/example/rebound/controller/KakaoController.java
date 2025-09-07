package com.example.rebound.controller;

import com.example.rebound.common.enumeration.Provider;
import com.example.rebound.dto.MemberDTO;
import com.example.rebound.repository.CounselorDAO;
import com.example.rebound.repository.MemberDAO;
import com.example.rebound.service.KakaoService;
import com.example.rebound.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final HttpSession session;
    private final MemberDAO memberDAO;

    @GetMapping("/kakao/login")
    public String kakaoLogin(String code, Model model) {
        String token = kakaoService.getKakaoAccessToken(code);
        Optional<MemberDTO> foundMemberOpt = kakaoService.getKakaoInfo(token);

        if (foundMemberOpt.isEmpty()) {
            return "redirect:/member/login?error=kakao";
        }

        MemberDTO kakaoMember = foundMemberOpt.get();
        Optional<MemberDTO> foundKakaoMember = memberService.findMemberByKakaoEmail(kakaoMember.getKakaoEmail());

        log.info("kakao email: {}", kakaoMember.getKakaoEmail());
        log.info("foundKakaoMember: {}", foundKakaoMember);

        if (foundKakaoMember.isEmpty()) {
            kakaoMember.setMemberProvider(Provider.KAKAO);
            kakaoMember.setMemberEmail(kakaoMember.getKakaoEmail());

            model.addAttribute("memberDTO", kakaoMember);
            return "member/login-kakao";
        }

        session.setAttribute("member", foundKakaoMember.get());
        model.addAttribute("member", foundKakaoMember);
        memberDAO.setLatelyDateKakao(foundKakaoMember.get().getKakaoEmail());
        return "redirect:/";
    }

    @PostMapping("/member/join-kakao")
    public String joinKakao(@ModelAttribute MemberDTO memberDTO, Model model) {
        memberService.joinKakaoMember(memberDTO);
        memberService.saveKakaoProfile(memberDTO);
        session.setAttribute("member", memberDTO);
        model.addAttribute("member", session.getAttribute("member"));

        return "redirect:/";
    }

}
