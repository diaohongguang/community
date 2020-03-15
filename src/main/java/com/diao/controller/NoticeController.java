package com.diao.controller;

import com.diao.excption.DiyException;
import com.diao.excption.MyException;
import com.diao.pojo.User;
import com.diao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice/{id}/{outerId}/{status}")
    public String readAdivce(@PathVariable("id") Integer id,
                             @PathVariable("outerId") Integer outerId,
                             @PathVariable("status") Integer status,
                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if (status != null && status.equals(0)) {
            noticeService.updataStatus(id);
        }
        return "redirect:/question/" + outerId;
    }
}
