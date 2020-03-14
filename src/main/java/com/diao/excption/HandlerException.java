package com.diao.excption;

import com.alibaba.fastjson.JSON;
import com.diao.pojo.dto.ResultDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception e, HttpServletRequest request,HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        if ("application/json".equals(request.getContentType())){
            ResultDto dto=null;
            if (e instanceof DiyException){
                dto=new ResultDto(((DiyException) e).getCode(),((DiyException)e).getMessage());
            }else {
                dto=new ResultDto(MyException.SYS_ERROR.getCode(),MyException.SYS_ERROR.getMessage());
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            try {
                PrintWriter writer = response.getWriter();
                String s = JSON.toJSONString(dto);
                writer.write(s);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }else {
            if (e instanceof DiyException){
                modelAndView.addObject("code",((DiyException) e).getCode());
                modelAndView.addObject("message",((DiyException) e).getMessage());
            }else {
                modelAndView.addObject("message",MyException.SYS_ERROR.getCode());
                modelAndView.addObject("message",MyException.SYS_ERROR.getMessage());
            }
            return modelAndView;
        }
    }
}
