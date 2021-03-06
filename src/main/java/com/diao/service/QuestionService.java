package com.diao.service;

import com.diao.pojo.Question;
import com.diao.pojo.dto.PageDto;
import com.diao.pojo.dto.QuestionDto;
import com.diao.pojo.dto.RelatedQuestionsDto;

import java.util.List;

public interface QuestionService {
    void createQuestion(Question question);
    PageDto listQuestions(Integer currentPage,
                                Integer pageSize,
                                String keyword);
    QuestionDto selectQuestion(Integer id);
    void updateQuestionCommentCountById(Integer id);
    List<RelatedQuestionsDto> listRelatedQuestions(String tages,Integer id);

    Integer getMyselfQuestionCount(Integer creator);
}
