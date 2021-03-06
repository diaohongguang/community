package com.diao.controller;

import com.diao.mapper.NoticeMapper;
import com.diao.pojo.Question;
import com.diao.pojo.User;
import com.diao.pojo.dto.QuestionDto;
import com.diao.service.serviceimpl.QuestionServiceImpl;
import com.diao.utils.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    QuestionServiceImpl questionService;
    @Autowired
    private NoticeMapper noticeMapper;
    @GetMapping("/publish")
    public String goPublish(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("tagedata", Tags.getListTag());
        model.addAttribute("noticeCount", noticeMapper.getNewNoticeCount(Integer.valueOf(user.getAccountId())));
        if (request.getSession().getAttribute("user") != null) {
            return "publish";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/publish")
    public String addQuestion(Question question, HttpServletRequest request, Model model) {
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        //对前端数据进行校验
        if (question.getTitle() == null || "".equals(question.getTitle())) {
            model.addAttribute("error", "~标题不能为空");
            return "publish";
        }
        if (question.getDescription() == null || "".equals(question.getDescription())) {
            model.addAttribute("error", "~问题补充不能为空");
            return "publish";
        }
        if (question.getTag() == null || "".equals(question.getTag())) {
            model.addAttribute("error", "~标签不能为空");
            return "publish";
        }
        if (!Tags.isValid(question.getTag())){
            model.addAttribute("error", "~标签格式非法");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("noticeCount", noticeMapper.getNewNoticeCount(Integer.valueOf(user.getAccountId())));
        question.setCreator(user.getAccountId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createQuestion(question);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateQuestion(@PathVariable("id") Integer id,
                                 Model model) {
        QuestionDto question = questionService.selectQuestion(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "/publish";
    }
}
