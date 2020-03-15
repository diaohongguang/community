package com.diao.utils;

import lombok.Data;


public enum NoticeType {
    COMMENT(1,"评论了"),REPLY(2,"回复了");

    private Integer type;
    private String content;


    NoticeType(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
