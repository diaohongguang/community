package com.diao.controller;

import com.diao.mapper.NoticeMapper;
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
    @Autowired
    private NoticeMapper noticeMapper;
    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id")Integer id,
                             Model model,
                             HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        QuestionDto questionDto = questionService.selectQuestion(id);
        if (user != null){
            model.addAttribute("noticeCount", noticeMapper.getNewNoticeCount(Integer.valueOf(user.getAccountId())));
        }

        model.addAttribute("question",questionDto);
        model.addAttribute("comments",commentService.listComments(questionDto.getId(), TypeEnum.COMMENT.getType()));
        model.addAttribute("relatedQuestions",questionService.listRelatedQuestions(questionDto.getTag(),questionDto.getId()));
        return "question";
    }
}
