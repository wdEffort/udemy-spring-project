package com.udemy.spring.project.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MyBatisTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testSqlSessionFactory() throws Exception {
        System.out.println("MyBatis SqlSessionFactory => " + sqlSessionFactory);
    }

    @Test
    public void testSqlSession() throws Exception {
        // SqlSessionFactory에서 SqlSession(MyBatis와 Spring을 연결)을 가져올 수 있는지 테스트
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            System.out.println("MyBatis SqlSession => " + sqlSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
