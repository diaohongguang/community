package com.diao.excption;

public enum MyException {
    SERVER_BAD("~服务器生病了呢,稍后可以访问哦~"),
    PAGE_NOT_FOUND("~you查找的不存在o~");

    private String message;
    MyException(String message){
        this.message=message;
    }
    public void setMessage(String message){
        this.message =message;
    }
    public String getMessage(){
        return this.message;
    }
}
