package com.example.rebound.mapper;

import com.example.rebound.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
//    첫 결제
    public void insertPayment(PaymentDTO paymentDTO);

//    n번째 결제
    public int updatePayment(Long memberId);

//    결제 찾기
    public List<PaymentDTO> selectPayment(int memberId);
}
