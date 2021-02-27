package com.udemy.spring.project.board.repository.impl;


import com.udemy.spring.project.board.repository.BoardPostDAO;
import com.udemy.spring.project.board.vo.BoardPostVO;
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
public class BoardPostDAOImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardPostDAOImplTest.class);

    @Autowired
    private BoardPostDAO boardPostDAO;

    @Test
    @Transactional
    @Rollback(value = true)
    public void insertTest() throws Exception {
        LOGGER.info("게시글 등록 테스트 ...");

        BoardPostVO boardPostVO = new BoardPostVO();
        boardPostVO.setSubject("안녕하세요.");
        boardPostVO.setContent("안녕하세요. 인사드립니다.");
        boardPostVO.setWriter("홍길동");

        boardPostDAO.insert(boardPostVO);
    }

    @Test
    public void readTest() throws Exception {
        LOGGER.info("게시글 한건 조회 테스트 ...");

        LOGGER.info(boardPostDAO.read(1).toString());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void updateTest() throws Exception {
        LOGGER.info("게시글 수정 테스트 ...");

        LOGGER.info("게시글 수정 전 ...");
        LOGGER.info(boardPostDAO.read(1).toString());

        BoardPostVO boardPostVO = new BoardPostVO();
        boardPostVO.setPostId(1);
        boardPostVO.setSubject("안녕히 계세요.");
        boardPostVO.setContent("다음에 또 봐요.");
        boardPostVO.setWriter("최길동");

        boardPostDAO.update(boardPostVO);

        LOGGER.info("게시글 수정 후 ...");
        LOGGER.info(boardPostDAO.read(1).toString());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void deleteTest() throws Exception {
        LOGGER.info("게시글 삭제 테스트 ...");

        boardPostDAO.delete(1);
    }

    @Test
    public void listTest() throws Exception {
        LOGGER.info("게시글 목록 조회 테스트 ...");

        LOGGER.info(boardPostDAO.list().toString());
    }
}