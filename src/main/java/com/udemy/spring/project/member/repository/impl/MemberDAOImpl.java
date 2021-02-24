package com.udemy.spring.project.member.repository.impl;

import com.udemy.spring.project.member.repository.MemberDAO;
import com.udemy.spring.project.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public String getTime() {
        return sqlSession.selectOne("getTime");
    }

    @Override
    public void insertMember(MemberVO vo) {
        sqlSession.insert("insertMember", vo);
    }
}
