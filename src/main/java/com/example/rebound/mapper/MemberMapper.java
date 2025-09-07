package com.example.rebound.mapper;

import com.example.rebound.dto.MemberCriteriaDTO;
import com.example.rebound.dto.CounselorDTO;
import com.example.rebound.util.MemberCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.rebound.dto.MemberDTO;
import com.example.rebound.util.MemberCriteria;
import org.apache.ibatis.annotations.Param;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    //    추가
    public void insertMember(MemberDTO memberDTO);
//    이메일 중복 검사
    @Select("select count(*) > 0 from tbl_member where member_email = #{memberEmail}")
    public boolean existMemberEmail(String memberEmail);
//    전화번호 중복 검사
    @Select("select count(*)>0 from tbl_member where member_phone_number=#{memberPhoneNumber}")
    public boolean existMemberPhoneNumber(String memberPhoneNumber);
//    로그인
    public Optional<MemberDTO> selectMember(MemberDTO memberDTO);
//    카카오 이메일 중복 검사
    public Optional<MemberDTO> selectMemberByKakaoEmail(String kakaoEmail);
//    카카오 추가
    public void insertKakaoMember(MemberDTO memberDTO);
    //    조회
    public Optional<MemberDTO> selectMemberById(Long id);
    //    관리자 조회
    public MemberDTO selectAdmin(MemberDTO memberDTO );
    //    일반 회원 모두 조회
    public List<MemberDTO> selectGeneralAll(@Param("memberCriteria") MemberCriteria memberCriteria,@Param("keyword") String keyword);
    //    일반 회원 전체 수 조회
    public int selectGeneralAllCount(String keyword);
    //    멘토 회원 모두 조회
    public List<MemberDTO> selectMentorAll(@Param("memberCriteria") MemberCriteria memberCriteria,@Param("keyword") String keyword);
    //    멘토 회원 전체 수 조회
    public int selectMentorAllCount(String keyword);
    //    구독 회원 모두 조회
    public List<MemberDTO> selectSubscribeAll(@Param("memberCriteria") MemberCriteria memberCriteria,@Param("keyword") String keyword);
    //    구독 회원 전체 수 조회
    public int selectSubscribeAllCount(String keyword);
    //    수정
    public void updateMemberName(MemberDTO memberDTO);
    public void updateMemberPhoneNumber(MemberDTO memberDTO);
    public void updateMemberEmail(MemberDTO memberDTO);
    //    삭제
    public void deleteMemberById(int memberId);
    //    이메일(카카오이메일 포함)로 조회
    public MemberDTO selectByMemberEmail(String memberEmail);
    //    회원 비밀번호 변경
    public void updateMemberPassword(@Param("memberPassword") String memberPassword, @Param("memberEmail") String memberEmail);
    //    회원 이메일 찾기
    public MemberDTO selectEmailByPhoneNumber(String memberPhoneNumber);
    public int selectCountGradeByYear(String year);
    public int selectCountGradeByMonth(String month);
    public int selectCountSubscribeByYear(String year);
    public int selectCountSubscribeByMonth(String month);
    public void updateLatelyDate(String memberEmail);
    public void updateLatelyDateByKakao(String kakaoEmail);
}
