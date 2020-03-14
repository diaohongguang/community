package com.diao.mapper;

import com.diao.pojo.Comment;
import com.diao.pojo.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int createComment(Comment comment);

    Comment selectComment(Integer parentId);

    List<CommentDto> listComments(@Param("id") Integer id,@Param("type")Integer type);

    void updataReplisCount(@Param("id") Integer id);
}
