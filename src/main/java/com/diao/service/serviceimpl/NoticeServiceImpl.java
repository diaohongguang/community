package com.diao.service.serviceimpl;

import com.diao.mapper.NoticeMapper;
import com.diao.pojo.Notice;
import com.diao.pojo.dto.NoticePageDto;
import com.diao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public NoticePageDto listNoticeByReceiver(Integer receiver, Integer currentPage, Integer pageSize) {
        NoticePageDto pageDto = new NoticePageDto();
        int count = noticeMapper.getNoticeCount(receiver);
        if (count == 0) {
            return null;
        }
        int totlePage;
        if (count % pageSize != 0) {
            totlePage = count / pageSize + 1;
        } else {
            totlePage = count / pageSize;
        }
        pageDto.setTotlePage(totlePage);
        if (currentPage < 1 || currentPage > totlePage) {
            pageDto.setNotices(noticeMapper.listNoticeByReceiver(receiver,0, pageSize ));
            pageDto.computerPage(1, pageSize, count);
            return pageDto;
        }
        pageDto.computerPage(currentPage, pageSize, count);
        currentPage = (currentPage - 1) * pageSize;
        pageDto.setNotices(noticeMapper.listNoticeByReceiver(receiver,currentPage, pageSize));
        return pageDto;
    }
    public int updataStatus(Integer id){
        return noticeMapper.updataStatus(id);
    }
}
