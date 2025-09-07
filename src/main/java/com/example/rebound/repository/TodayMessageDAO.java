package com.example.rebound.repository;

import com.example.rebound.domain.TodayMessageVO;
import com.example.rebound.dto.TodayMessageDTO;
import com.example.rebound.mapper.TodayMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodayMessageDAO {
    private final TodayMessageMapper todayMessageMapper;

    public void saveTodayMessage(TodayMessageVO todayMessageVO) {
        todayMessageMapper.insertTodayMessage(todayMessageVO);
    }

    public List<TodayMessageDTO> findTodayMessage() {
        return todayMessageMapper.selectTodayMessage();

    }
}