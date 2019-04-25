package com.example.demo.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 配置统一的返回响应类型
 */
@ControllerAdvice("com.example.demo.module")
public class RestResultWrapper implements ResponseBodyAdvice<Object> {
    //这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;

    }
    //对于返回的对象如果不是最终对象ResponseResult，则选包装一下
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.print("统一外边再套一层");
        if(!(body instanceof RestResult)) {
            RestResult result = new RestResult("请求成功", "0", body);
            return result;
        }



        return body;
    }
}
