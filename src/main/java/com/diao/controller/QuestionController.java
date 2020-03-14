package com.diao.controller;

import com.diao.pojo.TypeEnum;
import com.diao.pojo.User;
import com.diao.pojo.dto.QuestionDto;
import com.diao.service.CommentService;
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
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")Integer id,
                             Model model,
                             HttpServletRequest request){
        QuestionDto questionDto = questionService.selectQuestion(id);

        model.addAttribute("question",questionDto);
        model.addAttribute("comments",commentService.listComments(questionDto.getId(), TypeEnum.COMMENT.getType()));
        return "question";
    }
}
