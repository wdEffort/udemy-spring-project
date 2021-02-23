package com.udemy.spring.project.db;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/udemy";
    private static final String USER = "udemy";
    private static final String PASSWD = "1234";

    /**
     * MySQL 연결 테스트
     *
     * @throws Exception
     */
    @Test
    public void testConnection() throws Exception {
        Class.forName(DRIVER);

        // try ~ resource 문법(JDK 1.7 이상에서 지원하는 문법)
        // finally를 사용하지 않아도 자동으로 자원(Connection, PrepareStatement 등)을 반납한다.
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD)) {
            System.out.println("MySQL Connection => " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
