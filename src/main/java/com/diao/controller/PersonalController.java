package com.diao.controller;

import com.diao.mapper.NoticeMapper;
import com.diao.pojo.User;
import com.diao.pojo.dto.NoticePageDto;
import com.diao.service.NoticeService;
import com.diao.service.QuestionService;
import com.diao.service.serviceimpl.PersonalServiceImpl;
import com.diao.service.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonalController {

    @Autowired
    PersonalServiceImpl personalService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NoticeMapper noticeMapper;
    @GetMapping("/personal/{active}")
    public String goPersonal(HttpServletRequest request, Model model,
                             @PathVariable("active") String active,
                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "5") Integer size,
                             @RequestParam(name = "keyword", defaultValue = "null") String keyword) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {


            model.addAttribute("noticeCount", noticeMapper.getNewNoticeCount(Integer.valueOf(user.getAccountId())));
            model.addAttribute("questionCount",questionService.getMyselfQuestionCount(Integer.valueOf(user.getAccountId())) );
            if ("question".equals(active)) {
                model.addAttribute("select", "question");
                model.addAttribute("selectName", "我的问题");
                model.addAttribute("list", questionService.listQuestions(page, size, keyword));
                if (keyword == null||"null".equals(keyword)) {
                    keyword = null;
                }else {
                    model.addAttribute("keyword",keyword);
                }
                model.addAttribute("list",personalService.listQuestionsByUserAccountId(page, size, keyword, user.getAccountId()));
                return "personal";
            } else if ("replies".equals(active)) {
                model.addAttribute("select", "replies");
                model.addAttribute("selectName", "最新回复");
                NoticePageDto noticePageDto = noticeService.listNoticeByReceiver(Integer.valueOf(user.getAccountId()), page, size);

                model.addAttribute("list",noticePageDto);
                return "personal";
            }
        }
        return "redirect:/";
    }
}
