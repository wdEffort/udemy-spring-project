package com.udemy.spring.project.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

// SpringJUnit4ClassRunner를 사용해서 테스트를 하겠다라고 설정한다.
@RunWith(SpringJUnit4ClassRunner.class)
// 지정한 경로에 있는 XML 설정 파일을 가지고 스프링 설정을 로드 시킨다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("DataSource 설정 테스트 ...");
            System.out.println("MySQL DataSource => " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
