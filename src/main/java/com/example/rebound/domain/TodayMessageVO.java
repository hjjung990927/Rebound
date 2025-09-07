package com.example.rebound.domain;

import com.example.rebound.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@SuperBuilder
@EqualsAndHashCode(of = "id")
public class TodayMessageVO extends Period {
    private Long id;
    private String todayMessageTitle;
    private String todayMessageContent;
    private Long counselorId;
    private String counselorName;
}
