package com.udemy.spring.project.member.repository.impl;

import com.udemy.spring.project.board.repository.impl.BoardPostDAOImplTest;
import com.udemy.spring.project.member.repository.MemberDAO;
import com.udemy.spring.project.member.vo.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * SqlSessionTemplate 테스트
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberDAOTest.class);

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void testGetTime() throws Exception {
        LOGGER.info("sqlSession을 이용한 SQL문법으로 날짜 조회 테스트 ...");
        LOGGER.info(memberDAO.getTime());
    }

    @Test
    @Transactional // 트랜잭션 처리를 하기 위한 어노테이션 설정
    @Rollback(value = true) // 테스트가 진행되고 나서 Rollback 처리를 하기 위한 어노테이션 설정
    public void testInsertMember() throws Exception {
        LOGGER.info("sqlSession을 이용한 회원 정보 등록 테스트 ...");

        MemberVO memberVO = new MemberVO();
        memberVO.setId("test");
        memberVO.setPassword("1234");
        memberVO.setUsername("홍길동");
        memberVO.setEmail("test@example.com");

        memberDAO.insertMember(memberVO);
    }
}