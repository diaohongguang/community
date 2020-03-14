package com.diao.pojo.dto;

import com.diao.excption.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T t;
    public ResultDto(MyException e){
        this.message=e.getMessage();
        this.code=e.getCode();
    }
    public ResultDto(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
