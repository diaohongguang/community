package com.diao.controller;

import com.diao.mapper.NoticeMapper;
import com.diao.pojo.User;
import com.diao.service.QuestionService;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NoticeMapper noticeMapper;

    @GetMapping({"/", "index"})
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "keyword", defaultValue = "null") String keyword,
                        HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (keyword == null||"null".equals(keyword)) {
            keyword = null;
        }else {
            model.addAttribute("keyword",keyword);
        }
        model.addAttribute("list", questionService.listQuestions(page, size, keyword));
        if (user != null) {
            model.addAttribute("noticeCount", noticeMapper.getNewNoticeCount(Integer.valueOf(user.getAccountId())));
        }

        return "index";
    }

}
