package com.example.rebound.controller;


import com.example.rebound.dto.InquiryDTO;
import com.example.rebound.service.AdminService;
import com.example.rebound.service.InquiryService;
import com.example.rebound.util.MemberCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/customer-center/**")
@RequiredArgsConstructor
public class CustomerCenterController {
    private final InquiryService inquiryService;
    private final AdminService adminService;

    //    공지사항 목록
    @GetMapping("notice-list/{page}")
    public String goTONoticeList(){
        return "customer-center/notice-list";
    }
//    공지사항 상세
    @GetMapping("notice-detail")
    public String goToNoticeDetails(int id, Model model) {

        System.out.println(adminService.noticeDetail(id));
        model.addAttribute("notice",adminService.noticeDetail(id));
        return "customer-center/notice-detail";
    }
//    문의
    @GetMapping("inquiry")
    public String goToInquiry() {
        return "customer-center/inquiry";
    }
//    문의글 작성
    @PostMapping("inquiry")
    public RedirectView writeInquiry(InquiryDTO inquiryDTO, List<MultipartFile> files) {
        System.out.println(inquiryDTO);
        inquiryService.writeInquiry(inquiryDTO,files);
        return new RedirectView("/") ;
    }
    @GetMapping("questions-list")
    public String goToQuestionsList() {
        return "customer-center/questions-list";
    }
    @GetMapping("questions-detail")
    public String goToQuestionsDetail() {
        return "customer-center/questions-detail";
    }
    @GetMapping("search")
    public String goToSearch() {
        return "customer-center/search";
    }
}
