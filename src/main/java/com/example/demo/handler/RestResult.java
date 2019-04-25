package com.example.demo.handler;

public class RestResult {

    /**
     * 请求是否成功信息
     */
    private String message;
    /**
     * 成功或者失败的code错误码
     */
    private String code;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    private Object data;

    public RestResult() {
    }

    public RestResult(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
