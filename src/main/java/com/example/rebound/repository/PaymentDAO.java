package com.example.rebound.repository;

import com.example.rebound.dto.PaymentDTO;
import com.example.rebound.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

//    첫 결제
    public void insertPayment(PaymentDTO paymentDTO) {
        paymentMapper.insertPayment(paymentDTO);
    }

//    n번째 결재
    public int updatePayment(Long memberId) {
        return paymentMapper.updatePayment(memberId);
    }

//    결제 조회
    public List<PaymentDTO> selectPaymeent(int memberId) {
        return paymentMapper.selectPayment(memberId);
    }
}
