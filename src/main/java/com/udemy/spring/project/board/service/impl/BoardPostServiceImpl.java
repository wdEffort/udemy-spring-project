package com.udemy.spring.project.board.service.impl;

import com.udemy.spring.project.board.repository.BoardPostDAO;
import com.udemy.spring.project.board.service.BoardPostService;
import com.udemy.spring.project.board.vo.BoardPostVO;
import com.udemy.spring.project.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardPostServiceImpl implements BoardPostService {

    @Autowired
    private BoardPostDAO boardPostDAO;

    @Override
    public void insert(BoardPostVO vo) throws Exception {
        boardPostDAO.insert(vo);
    }

    @Override
    public void update(BoardPostVO vo) throws Exception {
        boardPostDAO.update(vo);
    }

    @Override
    public void delete(Integer id) throws Exception {
        boardPostDAO.delete(id);
    }

    @Override
    public BoardPostVO read(Integer id) throws Exception {
        return boardPostDAO.read(id);
    }

    @Override
    public List<BoardPostVO> listCriteria(SearchCriteria searchCriteria) throws Exception {
        return boardPostDAO.listCriteria(searchCriteria);
    }

    @Override
    public int count(SearchCriteria searchCriteria) throws Exception {
        return boardPostDAO.count(searchCriteria);
    }
}
