package com.diao.mapper;

import com.diao.pojo.Question;
import com.diao.pojo.dto.QuestionDto;
import com.diao.pojo.dto.RelatedQuestionsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    void createQuestion(Question question);

    List<QuestionDto> listQuestions(@Param("currentPage") Integer currentPage,
                                    @Param("pageSize") Integer pageSize,
                                    @Param("keyword") String keyword,
                                    @Param("accountId") String accountId);

    int getQuestionCount();

    int getQuestionCountByUserAccountId(@Param("accountId") String accountId);

    QuestionDto selectQuestion(@Param("id") Integer id);

    void updateQuestion(Question question);

    void updateQuestionViewCountById(@Param("id") Integer id);

    void updateQuestionCommentCountById(@Param("id") Integer id);

    List<RelatedQuestionsDto> listRelatedQuestions(@Param("tags")String tags,@Param("id")Integer id);

    Integer getMyselfQuestionCount(@Param("creator") Integer creator);
}
