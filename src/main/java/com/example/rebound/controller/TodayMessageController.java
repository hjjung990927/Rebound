package com.example.rebound.controller;

import com.example.rebound.dto.TodayMessageDTO;
import com.example.rebound.service.TodayMessageService;
import com.example.rebound.service.TodayMessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/counselor-list/**")
@RequiredArgsConstructor
public class TodayMessageController {
    private final TodayMessageServiceImpl todayMessageServiceImpl;
    @GetMapping("good-words")
    public String goToGoodWords() {
        return "counselor-list/good-words";
    }
    @GetMapping("good-words-write")
    public  String goToGoodWordsWrite() {
        return "counselor-list/good-words-write";
    }
    @PostMapping("good-words-write")
    public RedirectView write(TodayMessageDTO todayMessageDTO) {
        System.out.println(todayMessageDTO);
        todayMessageServiceImpl.saveTodayMessage(todayMessageDTO);
        return new RedirectView("/counselor-list/good-words-member");
    }
}