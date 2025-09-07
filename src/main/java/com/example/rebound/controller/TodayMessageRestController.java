package com.example.rebound.controller;

import com.example.rebound.dto.TodayMessageDTO;
import com.example.rebound.service.TodayMessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/list/**")
@RequiredArgsConstructor
public class TodayMessageRestController {
    private final TodayMessageServiceImpl todayMessageServiceImpl;
    @GetMapping("today")
    public List<TodayMessageDTO> findAll(){
        return todayMessageServiceImpl.findTodayMessageAll();
    };

};