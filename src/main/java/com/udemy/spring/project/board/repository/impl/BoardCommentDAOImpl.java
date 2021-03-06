package com.udemy.spring.project.board.repository.impl;

import com.udemy.spring.project.board.repository.BoardCommentDAO;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {

    private static final String SQL_MAPPER_NAMESPACE = "BoardCommentMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insert(BoardCommentVO vo) throws Exception {
        sqlSession.insert(SQL_MAPPER_NAMESPACE + ".insert", vo);
    }

    @Override
    public void update(BoardCommentVO vo) throws Exception {
        sqlSession.update(SQL_MAPPER_NAMESPACE + ".update", vo);

    }

    @Override
    public void delete(Integer id) throws Exception {
        sqlSession.delete(SQL_MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public void deleteByPostId(Integer id) throws Exception {
        sqlSession.delete(SQL_MAPPER_NAMESPACE + ".deleteByPostId", id);
    }

    @Override
    public List<BoardCommentVO> list(Integer postId) throws Exception {
        return sqlSession.selectList(SQL_MAPPER_NAMESPACE + ".list", postId);
    }
}
