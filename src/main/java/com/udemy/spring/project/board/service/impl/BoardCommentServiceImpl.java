package com.udemy.spring.project.board.service.impl;

import com.udemy.spring.project.board.repository.BoardCommentDAO;
import com.udemy.spring.project.board.service.BoardCommentService;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import com.udemy.spring.project.utils.PageCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    private BoardCommentDAO boardCommentDAO;

    @Override
    public void insert(BoardCommentVO vo) throws Exception {
        boardCommentDAO.insert(vo);
    }

    @Override
    public void update(BoardCommentVO vo) throws Exception {
        boardCommentDAO.update(vo);
    }

    @Override
    public void delete(Integer id) throws Exception {
        boardCommentDAO.delete(id);
    }

    @Override
    public void deleteByPostId(Integer id) throws Exception {
        boardCommentDAO.deleteByPostId(id);
    }

    @Override
    public List<BoardCommentVO> list(Integer postId) throws Exception {
        return boardCommentDAO.list(postId);
    }

    @Override
    public List<BoardCommentVO> listPage(Integer postId, PageCriteria pageCriteria) throws Exception {
        return boardCommentDAO.listPage(postId, pageCriteria);
    }

    @Override
    public int count(Integer postId) throws Exception {
        return boardCommentDAO.count(postId);
    }
}
