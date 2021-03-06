package com.udemy.spring.project.board.controller;

import com.udemy.spring.project.board.service.BoardCommentService;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> insert(@RequestBody BoardCommentVO vo) {
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
}
