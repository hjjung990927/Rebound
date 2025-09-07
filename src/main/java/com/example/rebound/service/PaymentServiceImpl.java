package com.example.rebound.service;

import com.example.rebound.dto.MemberDTO;
import com.example.rebound.dto.PaymentDTO;
import com.example.rebound.dto.SubscribeDTO;
import com.example.rebound.repository.PaymentDAO;
import com.example.rebound.repository.SubscribeDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    private final SubscribeDAO subscribeDAO;
    private final HttpSession session;

    //    첫 결제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPayment(SubscribeDTO subscribeDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        subscribeDAO.insertSubscribe(subscribeDTO);
        paymentDTO.setSubscribeId(subscribeDTO.getId());
        paymentDTO.setMemberId(memberDTO.getId());
        paymentDAO.insertPayment(paymentDTO);
        paymentDAO.updatePayment(paymentDTO.getMemberId());
    }


    //    결제 조회
    @Override
    public List<PaymentDTO> selectPayment(int memberId) {
        return paymentDAO.selectPaymeent(memberId);
    }

}
