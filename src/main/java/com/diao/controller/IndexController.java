package com.diao.controller;

import com.diao.pojo.User;
import com.diao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @GetMapping({"/", "index"})
    public String index(HttpServletRequest request) {
        if (request.getCookies()!=null){
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
        return "index";
    }
}
