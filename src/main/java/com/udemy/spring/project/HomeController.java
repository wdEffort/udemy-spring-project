package com.udemy.spring.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    // SLF4J Logger 인터페이스를 사용한다.
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home() {
        LOGGER.info("HomeController#home() called ...");

        return "index";
    }

    @RequestMapping(value = "/ajax/test", method = RequestMethod.GET)
    public String getAjaxTestPage() {
        return "ajax/test";
    }
}
