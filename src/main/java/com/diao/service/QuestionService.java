package com.diao.service;

import com.diao.pojo.Question;
import com.diao.pojo.dto.PageDto;

public interface QuestionService {
    void createQuestion(Question question);
    PageDto listQuestions(Integer currentPage,
                                Integer pageSize,
                                String keyword);
}
