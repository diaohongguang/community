package com.diao.service.serviceimpl;

import com.diao.mapper.QuestionMapper;
import com.diao.pojo.dto.PageDto;
import com.diao.service.PersonalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public PageDto listQuestionsByUserAccountId(Integer currentPage, Integer pageSize, String keyword, String accountId) {
        PageDto pageDto = new PageDto();

        int count = questionMapper.getQuestionCountByUserAccountId(accountId);

        int totlePage;
        if (count != 0){
            if (count % pageSize != 0){
                totlePage=count/pageSize+1;
            }else {
                totlePage=count/pageSize;
            }
            pageDto.setTotlePage(totlePage);
            if (currentPage<1 || currentPage>totlePage){
                pageDto.setQuestions(questionMapper.listQuestionsByUserAccountId(0,pageSize,keyword,accountId));
                pageDto.computerPage(1,pageSize,count);
                return pageDto;
            }
            pageDto.computerPage(currentPage,pageSize,count);
            currentPage=(currentPage-1)*pageSize;
            pageDto.setQuestions(questionMapper.listQuestionsByUserAccountId(currentPage,pageSize,keyword,accountId));
            return pageDto;
        }
        return null;

    }
}
