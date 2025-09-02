package com.example.rebound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/counselor-list/**")
public class TodayMessageController {
    @GetMapping("good-words")
    public String goToGoodWords() {
        return "counselor-list/good-words";
    }
}
