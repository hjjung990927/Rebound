package com.example.rebound.mapper;

import com.example.rebound.domain.MemberVO;
import com.example.rebound.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertMemberTest(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName("단위테스트");
        memberDTO.setMemberEmail("test1@gmail.com");
        memberDTO.setMemberPassword("123456");
        memberDTO.setMemberPhoneNumber("01098765432");

        memberMapper.insertMember(memberDTO);
    }
}