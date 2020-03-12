package com.diao.controller;

import com.diao.pojo.User;
import com.diao.service.QuestionService;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;

    @GetMapping({"/", "index"})
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "keyword", defaultValue = "null") String keyword) {
        if ("null".equals(keyword)) {
            keyword = null;
        }
        model.addAttribute("list", questionService.listQuestions(page, size, keyword));
        return "index";
    }
}
