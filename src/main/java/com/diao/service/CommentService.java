package com.diao.service;

import com.diao.pojo.dto.CommentDto;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int createComment(Map<String,String> map);

    List<CommentDto> listComments(Integer id,Integer type);

}
