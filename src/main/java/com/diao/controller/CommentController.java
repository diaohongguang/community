package com.diao.controller;

import com.diao.excption.MyException;
import com.diao.pojo.TypeEnum;
import com.diao.pojo.User;
import com.diao.pojo.dto.CommentDto;
import com.diao.pojo.dto.ResultDto;
import com.diao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public ResultDto getComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new ResultDto(MyException.NO_LOGIN);
        }
        if (map.get("content") == null || "".equals(map.get("content").trim())) {
            return new ResultDto(MyException.NOT_COTENT);
        }
        commentService.createComment(map,user);
        return new ResultDto(MyException.OK_MESSAGE);
    }
    @ResponseBody
    @GetMapping("/replies/{id}")
    public ResultDto getComment(@PathVariable("id") Integer id) {
        ResultDto<List<CommentDto>> dto = new ResultDto(MyException.OK_MESSAGE);
        dto.setT(commentService.listComments(id, TypeEnum.REPLY.getType()));
        return dto;
    }

    @ResponseBody
    @PostMapping("/reply")
    public ResultDto insertReply(@RequestBody Map<String,String> map, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new ResultDto(MyException.NO_LOGIN);
        }
        commentService.createComment(map,user);
        return  new ResultDto(MyException.OK_MESSAGE);
    }


}
