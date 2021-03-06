package com.udemy.spring.project.board.repository.impl;

import com.udemy.spring.project.board.repository.BoardCommentDAO;
import com.udemy.spring.project.board.vo.BoardCommentVO;
import com.udemy.spring.project.utils.PageCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Map<String, Object> 객체를 파라미터로 전달하여 페이징 처리
     *
     * @param postId
     * @param pageCriteria
     * @return
     * @throws Exception
     */
    @Override
    public List<BoardCommentVO> listPage(Integer postId, PageCriteria pageCriteria) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("pageCriteria", pageCriteria);

        return sqlSession.selectList(SQL_MAPPER_NAMESPACE + ".listPage", map);
    }

    @Override
    public int count(Integer postId) throws Exception {
        return sqlSession.selectOne(SQL_MAPPER_NAMESPACE + ".count", postId);
    }
}
