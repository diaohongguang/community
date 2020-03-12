package com.diao.controller;

import com.diao.excption.MyException;
import com.diao.pojo.User;
import com.diao.pojo.dto.ResultDto;
import com.diao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public ResultDto getComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new ResultDto(MyException.NO_LOGIN);
        }
        commentService.createComment(map);
        return new ResultDto(MyException.OK_MESSAGE);
    }
}
