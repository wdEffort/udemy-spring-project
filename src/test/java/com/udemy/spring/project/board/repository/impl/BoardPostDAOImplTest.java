package com.udemy.spring.project.board.repository.impl;


import com.udemy.spring.project.board.repository.BoardCommentDAO;
import com.udemy.spring.project.board.repository.BoardPostDAO;
import com.udemy.spring.project.board.vo.BoardPostVO;
import com.udemy.spring.project.utils.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardPostDAOImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardPostDAOImplTest.class);

    @Autowired
    private BoardPostDAO boardPostDAO;

    @Autowired
    private BoardCommentDAO boardCommentDAO;

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
        LOGGER.info("게시글에 등록된 댓글 모두 삭제 후 게시글 삭제 ...");

        boardCommentDAO.deleteByPostId(1);
        boardPostDAO.delete(1);
    }

    @Test
    public void listCriteriaTest() throws Exception {
        LOGGER.info("SearchCriteria 이용한 게시글 목록 검색 조회 및 페이징 테스트 ...");

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPage(1);
        searchCriteria.setNumPerPage(10);
        searchCriteria.setSearchType("W");
        searchCriteria.setSearchKeyword("관리자");

        LOGGER.info("총 데이터 개수 : " + boardPostDAO.count(searchCriteria));

        List<BoardPostVO> boardPostList = boardPostDAO.listCriteria(searchCriteria);

        for (BoardPostVO boardPost : boardPostList) {
            LOGGER.info(boardPost.toString());
        }
    }

    @Test
    public void uriComponentsBuilderTest() throws Exception {
        LOGGER.info("UriComponentsBuilder 테스트 - 1 ...");

        String uri = "/board/view?postId=100&numPerPage=20&stx=" + URLEncoder.encode("가나다", "UTF-8");
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/view")
                .queryParam("postId", 100)
                .queryParam("numPerPage", 20)
                .queryParam("stx", "가나다")
                .encode()
                .build();

        LOGGER.info("\'" + uri + "\' eqauls uriComponents.toString() => " + uri.equals(uriComponents.toString()));
    }

    @Test
    public void uriComponentsBuilderTest2() throws Exception {
        LOGGER.info("UriComponentsBuilder 테스트 - 2 ...");

        String uri = "/board/view?postId=100&numPerPage=20&stx=" + URLEncoder.encode("가나다", "UTF-8");
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("postId", 100)
                .queryParam("numPerPage", 20)
                .queryParam("stx", "가나다")
                .encode()
                .build()
                .expand("board", "view");

        LOGGER.info("\'" + uri + "\' eqauls uriComponents.toString() => " + uri.equals(uriComponents.toString()));
    }
}