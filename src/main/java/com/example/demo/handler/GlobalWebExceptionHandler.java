package com.example.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*配置web错误*/
@ControllerAdvice(basePackages = {"com.example.demo.module.test"})
public class GlobalWebExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private Object exceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        view.addObject("exception",e);
        view.setViewName("error/error");
        return view;

    }


}
