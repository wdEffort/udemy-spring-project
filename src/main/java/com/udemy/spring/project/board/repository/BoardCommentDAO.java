package com.udemy.spring.project.board.repository;

import com.udemy.spring.project.board.vo.BoardCommentVO;

import java.util.List;

public interface BoardCommentDAO {

    void insert(BoardCommentVO vo) throws Exception;

    void update(BoardCommentVO vo) throws Exception;

    void delete(Integer id) throws Exception;

    void deleteByPostId(Integer id) throws Exception;

    List<BoardCommentVO> list(Integer postId) throws Exception;
}
