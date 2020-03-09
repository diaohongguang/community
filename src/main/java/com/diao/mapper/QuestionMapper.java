package com.diao.mapper;

import com.diao.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {
    void createQuestion(Question question);
}
