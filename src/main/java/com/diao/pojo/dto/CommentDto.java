package com.diao.pojo.dto;

import com.diao.pojo.Comment;
import com.diao.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer replies;
    private String content;
    private User user;
}
