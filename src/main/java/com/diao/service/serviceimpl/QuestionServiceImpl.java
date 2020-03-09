package com.diao.service.serviceimpl;

import com.diao.mapper.QuestionMapper;
import com.diao.pojo.Question;
import com.diao.pojo.User;
import com.diao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Override
    public void createQuestion(Question question) { questionMapper.createQuestion(question);}
}