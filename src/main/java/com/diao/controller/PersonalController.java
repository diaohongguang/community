package com.diao.controller;

import com.diao.pojo.User;
import com.diao.pojo.dto.PageDto;
import com.diao.service.serviceimpl.PersonalServiceImpl;
import com.diao.service.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonalController {

    @Autowired
    PersonalServiceImpl personalService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/personal/{active}")
    public String goPersonal(HttpServletRequest request, Model model,
                             @PathVariable("active") String active,
                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "5") Integer size,
                             @RequestParam(name = "keyword", defaultValue = "null") String keyword) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    User user = userService.selectUserByToken(cookie.getValue());
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if ("question".equals(active)) {
                model.addAttribute("select", "question");
                model.addAttribute("selectName", "我的问题");
                if (keyword == null || "null".equals(keyword)) {
                    keyword = null;
                }
                model.addAttribute("list",personalService.listQuestionsByUserAccountId(page, size, keyword, user.getAccountId()));
                return "personal";
            } else if ("replies".equals(active)) {
                model.addAttribute("select", "replies");
                model.addAttribute("selectName", "最新回复");
                return "personal";
            }
        }
        return "redirect:/";
    }
}
