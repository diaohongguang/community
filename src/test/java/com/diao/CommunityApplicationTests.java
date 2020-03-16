package com.diao;


import com.diao.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;


@SpringBootTest
class CommunityApplicationTests {
   @Autowired
   private QuestionService questionService;

    @Test
    void contextLoads() throws IOException {
        System.out.println(questionService.listQuestions(1, 5, "java"));
    }

}
