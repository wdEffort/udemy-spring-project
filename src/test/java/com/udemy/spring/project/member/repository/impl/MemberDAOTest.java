package com.udemy.spring.project.member.repository.impl;

import com.udemy.spring.project.member.repository.MemberDAO;
import com.udemy.spring.project.member.vo.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * SqlSessionTemplate 테스트
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void testGetTime() throws Exception {
        System.out.println(memberDAO.getTime());
    }

    @Test
    public void testInsertMember() throws Exception {
        MemberVO memberVO = new MemberVO();
        memberVO.setId("test");
        memberVO.setPassword("1234");
        memberVO.setUsername("홍길동");
        memberVO.setEmail("test@example.com");

        memberDAO.insertMember(memberVO);
    }
}