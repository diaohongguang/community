package com.diao.service;

import com.diao.pojo.Question;

import java.util.List;

public interface QuestionService {
    void createQuestion(Question question);
    List<Question> listQuestions(Integer currentPage,
                                 Integer pageSize,
                                 String keyword);
}
