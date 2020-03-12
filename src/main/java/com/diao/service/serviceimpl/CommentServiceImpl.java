package com.diao.service.serviceimpl;

import com.diao.excption.DiyException;
import com.diao.excption.MyException;
import com.diao.mapper.CommentMapper;
import com.diao.pojo.Comment;
import com.diao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int createComment(Map<String, String> map) {
        System.out.println(map.get("parentId"));
        if (map.get("parentId") == null || map.get("parentId").equals("")) {
            throw new DiyException(MyException.COMMENT_NOT_QUESTION);
        }
        Comment comment = new Comment();
        comment.setContent(map.get("content"));
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(Integer.valueOf(map.get("type")));
        comment.setParentId(Integer.valueOf(map.get("parentId")));
        comment.setCommentator(Integer.valueOf(map.get("commentator")));
        return commentMapper.createComment(comment);
    }
}
