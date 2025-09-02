package com.example.rebound.mapper;


import com.example.rebound.dto.MemberCriteriaDTO;
import com.example.rebound.dto.MemberDTO;
import com.example.rebound.repository.MemberDAO;
import com.example.rebound.service.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
@Slf4j
public class AdminTest {
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberDTO memberDTO;
    @Autowired
    private AdminServiceImpl  adminService;

    @org.junit.jupiter.api.Test
    public void testDAO() {
        MemberDTO  memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("rebound@gmail.com");
        memberDTO.setMemberPassword("1234");

    }
    @Autowired
    private MemberMapper memberMapper;

//    @Test
//    public void insertMemberTest(){
//        for (int i=0;i<111;i++) {
//            MemberDTO memberDTO = new MemberDTO();
//            memberDTO.setMemberName("일반회원테스트");
//            memberDTO.setMemberEmail("test10000@gmail.com"+i);
//            memberDTO.setMemberPassword("123456");
//            memberDTO.setMemberPhoneNumber("010123415645"+i);
//
//            memberMapper.insertMember(memberDTO);
//        }
//    }
    @Test
    public void test(){
        LocalDateTime  now = LocalDateTime.now();
        System.out.println(now);    }


}


