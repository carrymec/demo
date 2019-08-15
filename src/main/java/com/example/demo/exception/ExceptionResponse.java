package com.example.demo.exception;

public class ExceptionResponse {

    private String msg;
    private Integer status;

    public ExceptionResponse(Integer status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    public static ExceptionResponse create(Integer status, String msg) {
        return new ExceptionResponse(status, msg);
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
