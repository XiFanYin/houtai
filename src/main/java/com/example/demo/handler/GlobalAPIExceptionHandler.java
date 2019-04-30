package com.example.demo.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/*配置API统一错误管理*/
//https://www.jianshu.com/p/a9bcfe733713
@ControllerAdvice()
public class GlobalAPIExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalAPIExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody/*这里一定要配置这个ResponseBody返回json*/
    private Object exceptionHandler(HttpServletRequest req, Exception e, HttpServletResponse response) {

        if (isJson(req)){//表示json请求错误
            //默认为没有捕捉到的40几异常


            RestResult result = new RestResult(ErrorMessage.SYSTEM_EXCEPTION, -1, null);
            LogicException logicException = (LogicException) e;
            result.setCode(Integer.valueOf(logicException.getCode()));
            result.setMessage(logicException.getErrorMsg());
            return JSONObject.toJSON(result);
        }else {
            //表示网页请求错误
            LogicException logicException = (LogicException) e;
            ModelAndView modelAndView = new  ModelAndView();
            if (logicException.getCode().equalsIgnoreCase("400")) {
                modelAndView.setViewName("error/error");
            }else  if (logicException.getCode().equalsIgnoreCase("800")){
                modelAndView.setViewName("error/error");
            }
            modelAndView.addObject("exception", e);
            return modelAndView;
        }


    }
    private Boolean isJson(HttpServletRequest request){
        String header = request.getHeader("Content-Type");
        System.out.println(header);
        return header != null && header.contains("json");
    }


}
