package com.diao.controller;

import com.diao.pojo.Question;
import com.diao.pojo.User;
import com.diao.service.serviceimpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionServiceImpl questionService;
    @GetMapping("/publish")
    public String goPublish( HttpServletRequest request){
        if (request.getSession().getAttribute("user")!=null){
            return "publish";
        }
        else{
            return "redirect:/";
        }
    }
    @PostMapping("/publish")
    public String addQuestion(Question question, HttpServletRequest request, Model model){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        //对前端数据进行校验
        if (question.getTitle()==null || "".equals(question.getTitle())){
            model.addAttribute("error","~标题不能为空");
            return "publish";
        }
        if (question.getDescription()==null || "".equals(question.getDescription())){
            model.addAttribute("error","~问题补充不能为空");
            return "publish";
        }
        if (question.getTag()==null || "".equals(question.getTag())){
            model.addAttribute("error","~标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        question.setCreator(Integer.valueOf(user.getAccountId()));
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createQuestion(question);
        return "redirect:/";
    }
}
