package com.diao.pojo.dto;

import com.diao.excption.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private Integer code;
    private String message;
    public ResultDto(MyException e){
        this.message=e.getMessage();
        this.code=e.getCode();
    }

}
