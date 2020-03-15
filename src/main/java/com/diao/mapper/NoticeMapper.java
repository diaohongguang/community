package com.diao.mapper;

import com.diao.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {
    int insertNotice(Notice notice);

    List<Notice> listNoticeByReceiver(@Param("receiver")Integer receiver,@Param("currentPage") Integer currentPage,
                                      @Param("pageSize") Integer pageSize);

    int getNoticeCount(@Param("receiver")Integer receiver);

    int getNewNoticeCount(@Param("receiver")Integer receiver);

    int updataStatus(@Param("id")Integer id);
}
