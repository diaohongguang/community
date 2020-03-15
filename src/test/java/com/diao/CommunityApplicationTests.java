package com.diao;

import com.diao.mapper.CommentMapper;
import com.diao.mapper.QuestionMapper;
import com.diao.mapper.UserMapper;
import com.diao.pojo.dto.CommentDto;
import com.diao.service.CommentService;
import com.diao.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Test
    void contextLoads() {
        System.out.println(questionMapper.getQuestionCount());
    }

}
