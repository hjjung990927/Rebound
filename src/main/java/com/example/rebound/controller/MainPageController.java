package com.example.rebound.controller;

import com.example.rebound.dto.PostCriteriaDTO;
import com.example.rebound.service.MainPageService;
import com.example.rebound.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService mainpageService;
    private final PostCriteriaDTO postCriteriaDTO;

    @GetMapping("/")
    public String goToMainpage(Model model) {
        model.addAttribute("posts", mainpageService.postPreview());
        model.addAttribute("todayMessages", mainpageService.todayMessagePreview());
        model.addAttribute("notices", mainpageService.noticePreview());
        return "main-page/page";
    }


    @GetMapping("today-message")
    public String goToTodayMessage() {
        return "counselor-list/good-words";
    }

    @GetMapping("/community-list/community-contents-member")
    public String goToCommunityContentsMember() {
        return "community-list/community-contents-member";
    }
    @GetMapping("/counselor-list/good-words-member")
    public String goToGoodWordsMember() {
        return "counselor-list/good-words-member";
    }
    @GetMapping("service/service-intro")
    public String goToServiceIntro() {
        return "service/service-intro";
    }

}
