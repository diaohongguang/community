package com.diao.controller;

import com.diao.pojo.User;
import com.diao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")Integer id,
                             Model model){
        model.addAttribute("question",questionService.selectQuestion(id));
        return "question";
    }
}
