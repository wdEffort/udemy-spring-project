package com.udemy.spring.project.member.repository.impl;

import com.udemy.spring.project.member.repository.MemberDAO;
import com.udemy.spring.project.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    private static final String SQL_MAPPER_NAMESPACE = "MemberMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public String getTime() {
        //return sqlSession.selectOne("getTime");
        return sqlSession.selectOne(SQL_MAPPER_NAMESPACE + ".getTime");
    }

    @Override
    public void insertMember(MemberVO vo) {
        //sqlSession.insert("insertMember", vo);
        sqlSession.insert(SQL_MAPPER_NAMESPACE + ".insertMember", vo);
    }
}
