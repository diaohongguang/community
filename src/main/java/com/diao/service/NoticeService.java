package com.diao.service;

import com.diao.pojo.Notice;
import com.diao.pojo.User;
import com.diao.pojo.dto.NoticePageDto;

public interface NoticeService {
    int insertNotice(Notice notice);

  NoticePageDto listNoticeByReceiver(Integer receiver, Integer currentPage,
                                     Integer pageSize);

    int updataStatus(Integer id);
}
