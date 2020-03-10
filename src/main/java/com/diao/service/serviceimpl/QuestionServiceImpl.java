package com.diao.service.serviceimpl;

import com.diao.mapper.QuestionMapper;
import com.diao.pojo.Question;
import com.diao.pojo.dto.PageDto;
import com.diao.pojo.dto.QuestionDto;
import com.diao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void createQuestion(Question question) {
        questionMapper.createQuestion(question);
    }
    @Override
    public PageDto listQuestions(Integer currentPage, Integer pageSize, String keyword){
        PageDto pageDto = new PageDto();
        //设置当前页
        pageDto.setCurrentPage(currentPage);
        currentPage=(currentPage-1)*pageSize;
        int count = questionMapper.getQuestionCount();
        //计算总页数
        if (count % pageSize != 0){
            pageDto.setTotlePage(count/pageSize+1);
        }else {
            pageDto.setTotlePage(count/pageSize);
        }

        pageDto.setQuestions(questionMapper.listQuestions(currentPage,pageSize,keyword));
        pageDto.computerPage();
        return pageDto;
    }
}
