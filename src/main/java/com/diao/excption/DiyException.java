package com.diao.excption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiyException extends RuntimeException {
    private String message;
    private Integer code;
    public DiyException(MyException myException){
        this.code=myException.getCode();
        this.message=myException.getMessage();
    }
}
