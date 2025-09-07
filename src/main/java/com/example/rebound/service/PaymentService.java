package com.example.rebound.service;

import com.example.rebound.dto.PaymentDTO;
import com.example.rebound.dto.SubscribeDTO;

import java.util.List;

public interface PaymentService {

    //    첫 결제
    public void insertPayment(SubscribeDTO subscribeDTO);

    //    결제 조회
    public List<PaymentDTO> selectPayment(int memberId);
}
