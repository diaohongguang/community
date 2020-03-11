package com.diao;

import com.diao.mapper.UserMapper;
import com.diao.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Test
    void contextLoads() {
    }

}
