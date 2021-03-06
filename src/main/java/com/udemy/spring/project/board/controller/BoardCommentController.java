package com.udemy.spring.project.board.controller;

import com.udemy.spring.project.board.service.BoardCommentService;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import com.udemy.spring.project.utils.PageCriteria;
import com.udemy.spring.project.utils.PagingMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board/comments")
public class BoardCommentController {

    @Autowired
    private BoardCommentService boardCommentService;

    /**
     * 댓글 등록
     * 요청 JSON 문자열(예-1) {"postId" : 1, "cmtContent" : "댓글 테스트입니다.", "writer" : "관리자"}
     * 요청 JSON 문자열(예-2) {"postId" : "1", "cmtContent" : "댓글 테스트입니다.", "writer" : "관리자"}
     * JSON 문자열 예-2에서 "postId" Key의 값이 문자열로 주어진 경우 내부적으로 형변환이 이루어 진다.
     *
     * @param vo JSON 문자열을 BoardCommentVO 타입의 객체로 변환하여 처리한다.
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody BoardCommentVO vo) throws Exception {
        ResponseEntity<String> entity = null;

        try {
            boardCommentService.insert(vo);

            entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();

            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400
        }

        return entity;
    }

    /**
     * 댓글 수정
     * 요청 JSON 문자열(예) {"cmtContent" : "댓글 수정-1 테스트입니다."}
     *
     * @param cmtId 댓글 고유번호
     * @param vo    JSON 문자열을 BoardCommentVO 타입의 객체로 변환하여 처리한다.
     * @return
     */
    @RequestMapping(value = "/{cmtId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("cmtId") Integer cmtId, @RequestBody BoardCommentVO vo) throws Exception {
        ResponseEntity<String> entity = null;

        try {
            vo.setCmtId(cmtId);

            boardCommentService.update(vo);

            entity = new ResponseEntity<>("success", HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();

            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400
        }

        return entity;
    }

    /**
     * 댓글 삭제
     *
     * @param cmtId 댓글 고유번호
     * @return
     */
    @RequestMapping(value = "/{cmtId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("cmtId") Integer cmtId) throws Exception {
        ResponseEntity<String> entity = null;

        try {
            boardCommentService.delete(cmtId);

            entity = new ResponseEntity<>("success", HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();

            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400
        }

        return entity;
    }

    /**
     * 댓글 목록 조회
     *
     * @param postId 게시글 고유번호
     * @return
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ResponseEntity<List<BoardCommentVO>> list(@PathVariable("postId") Integer postId) throws Exception {
        ResponseEntity<List<BoardCommentVO>> entity = null;

        try {
            entity = new ResponseEntity<>(boardCommentService.list(postId), HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();

            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }

        return entity;
    }

    /**
     * 댓글 목록 조회 + 페이징
     *
     * @param postId 게시글 고유번호
     * @param page   현재 페이지 번호
     * @return
     */
    @RequestMapping(value = "/{postId}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("postId") Integer postId, @PathVariable("page") Integer page) throws Exception {
        ResponseEntity<Map<String, Object>> entity = null;

        try {
            PageCriteria pageCriteria = new PageCriteria();
            pageCriteria.setPage(page);

            int totalCount = boardCommentService.count(postId);

            List<BoardCommentVO> list = boardCommentService.listPage(postId, pageCriteria);

            PagingMaker pagingMaker = new PagingMaker();
            pagingMaker.setPageCriteria(pageCriteria);
            pagingMaker.setTotalCount(totalCount);

            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("pagingMaker", pagingMaker);

            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
