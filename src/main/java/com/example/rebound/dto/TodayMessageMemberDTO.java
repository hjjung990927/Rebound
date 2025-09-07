package com.example.rebound.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class TodayMessageMemberDTO {
    private Long id;
    private List<TodayMessageDTO> todayMessages;
    private String mentor;
}
