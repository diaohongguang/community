package com.diao.service.serviceimpl;

import com.diao.excption.DiyException;
import com.diao.excption.MyException;
import com.diao.mapper.CommentMapper;
import com.diao.pojo.Comment;
import com.diao.pojo.TypeEnum;
import com.diao.pojo.dto.CommentDto;
import com.diao.pojo.dto.QuestionDto;
import com.diao.service.CommentService;
import com.diao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @Transactional
    @Override
    public int createComment(Map<String, String> map) {
        Comment comment = new Comment();
        comment.setContent(map.get("content"));
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(Integer.valueOf(map.get("type")));
        comment.setParentId(Integer.valueOf(map.get("parentId")));
        comment.setCommentator(Integer.valueOf(map.get("commentator")));
        if (map.get("parentId") == null || map.get("parentId").equals("")) {
            throw new DiyException(MyException.COMMENT_NOT_QUESTION);
        }
        System.out.println(TypeEnum.isExit(comment.getType()));
        if (comment.getType() == null || !TypeEnum.isExit(comment.getType())) {
            throw new DiyException(MyException.TYPE_ERROR);
        }
        //判断回复问题是否存在
        if (comment.getType() == TypeEnum.REPLY.getType()) {
            //回复评论
            Comment com = commentMapper.selectComment(comment.getParentId());
            commentMapper.updataReplisCount(com.getId());
            if (com == null) {
                throw new DiyException(MyException.REPLY_ERROR);
            }
            return commentMapper.createComment(comment);
        } else {
            //回复问题
            QuestionDto questionDto = questionService.selectQuestion(comment.getParentId());
            if (questionDto == null) {
                throw new DiyException(MyException.COMMENT_NOT_QUESTION);
            }
            questionService.updateQuestionCommentCountById(comment.getParentId());
            return commentMapper.createComment(comment);
        }
    }

    @Override
    public List<CommentDto> listComments(Integer id,Integer type){
        if (type != null && type.equals(2)){
            commentMapper.listComments(id,type).
                    stream().
                    sorted((c1, c2) -> { return (int) (c2.getGmtCreate() - c1.getGmtCreate()); }).
                    collect(Collectors.toList());
        }
        return commentMapper.listComments(id,type).
                stream().
                sorted((c1, c2) -> { return (int) (c2.getGmtCreate() - c1.getGmtCreate()); }).
                collect(Collectors.toList());
    }
}
