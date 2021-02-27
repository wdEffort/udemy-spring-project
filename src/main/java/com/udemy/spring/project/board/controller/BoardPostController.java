package com.udemy.spring.project.board.controller;

import com.udemy.spring.project.board.service.BoardPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardPostController {

    @Autowired
    private BoardPostService boardPostService;

    @RequestMapping(value = "/post/list", method = RequestMethod.GET)
    public String getBoardPostList() {
        return null;
    }
}
