package com.example.rebound.service;

import com.example.rebound.domain.TodayMessageVO;
import com.example.rebound.dto.TodayMessageDTO;
import com.example.rebound.repository.TodayMessageDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodayMessageServiceImpl implements TodayMessageService {
    private final TodayMessageDAO todayMessageDAO;
    @Override
    public void saveTodayMessage(TodayMessageDTO todayMessageDTO) {
        TodayMessageVO todayMessageVO = toVO(todayMessageDTO);
        todayMessageDAO.saveTodayMessage(todayMessageVO);
    }
    @Override
    public List<TodayMessageDTO> findTodayMessageAll(){
        return todayMessageDAO.findTodayMessage();
    }


}