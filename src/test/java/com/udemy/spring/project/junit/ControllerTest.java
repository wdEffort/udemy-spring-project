package com.udemy.spring.project.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
// 스프링 MVC를 테스트 하는데 있어서 필요한 어노테이션 설정, 기존의 스프링 테스트와 다른 점이다.
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerTest.class);

    @Autowired
    private WebApplicationContext ctx; // 스프링 컨텍스트 설정

    private MockMvc mockMvc; // 브라우저의 요청과 응답을 하는 객체

    /**
     * 테스트 실행 전 MockMvc 객체를 생성하도록 설정한다.
     */
    @Before
    public void setup() {
        LOGGER.info("setup() called ...");

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")); // GET 방식으로 "/" URL을 요청 테스트
    }

}
