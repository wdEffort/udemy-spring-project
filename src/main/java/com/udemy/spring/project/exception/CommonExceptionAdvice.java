package com.udemy.spring.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 현재 클래스가 Controller에서 발생하는 Exception을 전문적으로 처리하는 클래스임을 알려준다.
@ControllerAdvice
public class CommonExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionAdvice.class);


    /**
     * Exception 타입 예외가 발생하면 처리하는 메소드
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView common(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("exception/exception");
        mv.addObject("exception", e);

        return mv;
    }
}
