package com.udemy.spring.project.board.repository;

import com.udemy.spring.project.board.vo.BoardPostVO;

import java.util.List;

public interface BoardPostDAO {

    void insert(BoardPostVO vo) throws Exception;

    void update(BoardPostVO vo) throws Exception;

    void delete(Integer id) throws Exception;

    BoardPostVO read(Integer id) throws Exception;

    List<BoardPostVO> list() throws Exception;
}
