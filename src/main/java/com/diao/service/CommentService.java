package com.diao.service;

import com.diao.pojo.Comment;

import java.util.Map;

public interface CommentService {
    int createComment(Map<String,String> map);
}
