package com.udemy.spring.project.board.repository.impl;

import com.udemy.spring.project.board.repository.BoardCommentDAO;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardCommentDAOImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardCommentDAOImplTest.class);

    @Autowired
    private BoardCommentDAO boardCommentDAO;

    /**
     * 댓글 작성 테스트
     *
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(value = true)
    public void insertTest() throws Exception {
        LOGGER.info("댓글 작성 테스트 ...");
        
        BoardCommentVO boardCommentVO = new BoardCommentVO();
        boardCommentVO.setPostId(1);
        boardCommentVO.setCmtContent("댓글 테스트입니다.");
        boardCommentVO.setWriter("관리자");

        boardCommentDAO.insert(boardCommentVO);
    }

    /**
     * 댓글 수정 테스트
     *
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(value = true)
    public void updateTest() throws Exception {

    }

    /**
     * 댓글 삭제 테스트
     *
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(value = true)
    public void deleteTest() throws Exception {

    }

    /**
     * 댓글 목록 조회 테스트
     *
     * @throws Exception
     */
    @Test
    public void listTest() throws Exception {

    }
}