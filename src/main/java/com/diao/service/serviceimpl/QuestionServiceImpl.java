package com.diao.service.serviceimpl;

import com.diao.mapper.QuestionMapper;
import com.diao.pojo.Question;
import com.diao.pojo.dto.PageDto;
import com.diao.pojo.dto.QuestionDto;
import com.diao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void createQuestion(Question question) {
        if (question.getId()==null){
            questionMapper.createQuestion(question);
        }else {
            questionMapper.updateQuestion(question);
        }

    }
    @Override
    public PageDto listQuestions(Integer currentPage, Integer pageSize, String keyword){
        PageDto pageDto = new PageDto();

        int count = questionMapper.getQuestionCount();
        if (count!=0){
            int totlePage;

            if (count % pageSize != 0){
                totlePage=count/pageSize+1;
            }else {
                totlePage=count/pageSize;
            }
            pageDto.setTotlePage(totlePage);
            if (currentPage<1 || currentPage>totlePage){
                pageDto.setQuestions(questionMapper.listQuestions(0,pageSize,keyword));
                pageDto.computerPage(1,pageSize,count);
                return pageDto;
            }
            pageDto.computerPage(currentPage,pageSize,count);
            currentPage=(currentPage-1)*pageSize;
            pageDto.setQuestions(questionMapper.listQuestions(currentPage,pageSize,keyword));
            return pageDto;
        }
        return null;

    }

    @Override
    public QuestionDto selectQuestion(Integer id) {
        return questionMapper.selectQuestion(id);
    }
}
