package com.example.rebound.controller;

import com.example.rebound.dto.MemberDTO;
import com.example.rebound.dto.PaymentDTO;
import com.example.rebound.dto.SubscribeDTO;
import com.example.rebound.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/pay/**")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentDTO paymentDTO;
    private final HttpSession session;

    //    payment 페이지 이동
    @GetMapping("")
    public String goToPayment(Model model) {
        model.addAttribute("paymentDTO", paymentDTO);
        return "member/payment";
    }

//    결제 후
    @PostMapping(value = "")
    public RedirectView insertPayment(SubscribeDTO subscribeDTO) {
        paymentService.insertPayment(subscribeDTO);
        return new RedirectView("member/mypage");
    }

}
