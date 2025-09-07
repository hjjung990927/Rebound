package com.example.rebound.mapper;

import com.example.rebound.domain.TodayMessageVO;
import com.example.rebound.dto.TodayMessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodayMessageMapper {

    public void insertTodayMessage(TodayMessageVO todayMessageVO);
    public List<TodayMessageDTO> selectTodayMessage();
}