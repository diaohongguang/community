package com.diao.service;

import com.diao.pojo.dto.PageDto;


public interface PersonalService {
    PageDto listQuestionsByUserAccountId(Integer currentPage,
                                         Integer pageSize,
                                         String keyword,
                                         String accountId);
}
