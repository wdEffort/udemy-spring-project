package com.udemy.spring.project.member.repository;

import com.udemy.spring.project.member.vo.MemberVO;

public interface MemberDAO {

    String getTime();

    void insertMember(MemberVO vo);
}
