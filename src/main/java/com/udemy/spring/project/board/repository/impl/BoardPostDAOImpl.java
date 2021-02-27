package com.udemy.spring.project.board.repository.impl;

import com.udemy.spring.project.board.repository.BoardPostDAO;
import com.udemy.spring.project.board.vo.BoardPostVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardPostDAOImpl implements BoardPostDAO {

    private static final String SQL_MAPPER_NAMESPACE = "BoardPostMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insert(BoardPostVO vo) throws Exception {
        sqlSession.insert(SQL_MAPPER_NAMESPACE + ".insert", vo);
    }

    @Override
    public void update(BoardPostVO vo) throws Exception {
        sqlSession.update(SQL_MAPPER_NAMESPACE + ".update", vo);
    }

    @Override
    public void delete(Integer id) throws Exception {
        sqlSession.delete(SQL_MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public BoardPostVO read(Integer id) throws Exception {
        return sqlSession.selectOne(SQL_MAPPER_NAMESPACE + ".read", id);
    }

    @Override
    public List<BoardPostVO> list() throws Exception {
        return sqlSession.selectList(SQL_MAPPER_NAMESPACE + ".list");
    }
}
