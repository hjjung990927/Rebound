package com.example.rebound.service;

import com.example.rebound.domain.TodayMessageVO;
import com.example.rebound.dto.TodayMessageDTO;

import java.util.List;
import java.util.Locale;

public interface TodayMessageService {
    public void saveTodayMessage(TodayMessageDTO todayMessageDTO);
    public List<TodayMessageDTO> findTodayMessageAll();

    default TodayMessageVO toVO(TodayMessageDTO todayMessageDTO) {
        TodayMessageVO todayMessageVO = TodayMessageVO.builder()
                .todayMessageTitle(todayMessageDTO.getTodayMessageTitle())
                .todayMessageContent(todayMessageDTO.getTodayMessageContent())
                .counselorId(todayMessageDTO.getCounselorId())
                .counselorName(todayMessageDTO.getCounselorName())
                .build();
        return todayMessageVO;
    }
}