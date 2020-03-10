package com.diao.mapper;

import com.diao.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    void createQuestion(Question question);
    List<Question> listQuestions(@Param("currentPage") Integer currentPage,
                                 @Param("pageSize")Integer pageSize,
                                 @Param("keyword") String keyword);
}
