package com.example.demo.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*配置API统一错误管理*/
@ControllerAdvice(basePackages = {"com.example.demo.module.area","com.example.demo.module.user"})
public class GlobalAPIExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalAPIExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody/*这里一定要配置这个ResponseBody返回json*/
    private Object exceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {

        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult(ErrorMessage.SYSTEM_EXCEPTION, "-1", null);
        if (e instanceof LogicException) {
            LogicException logicException = (LogicException) e;
            result.setCode(logicException.getCode());
            result.setMessage(logicException.getErrorMsg());
            System.out.println("================");
        } else {
            logger.error("系统异常:" + e.getMessage(), e);
        }

        return JSONObject.toJSON(result);

    }


}
