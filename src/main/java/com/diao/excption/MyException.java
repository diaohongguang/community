package com.diao.excption;

public enum MyException {
    SERVER_BAD(5000, "~服务器生病了呢,稍后可以访问哦~"),
    PAGE_NOT_FOUND(4000, "~you查找的不存在o~"),
    COMMENT_NOT_QUESTION(4001, "~评论的问题走丢了！！！"),
    NO_LOGIN(4002, "~请登录，否则无法正常使用！！！"),
    OK_MESSAGE(2000,"~操作成功！！！"),
    NOT_COTENT(4004,"~评论不能为空呀！！！"),
    TYPE_ERROR(4003,"~评论类型错误！！！"),
    REPLY_ERROR(4004,"~回复的评论不存在！！！"),
    SYS_ERROR(9999,"~服务器自燃了，请稍在试试~~");
    private String message;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    MyException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}


