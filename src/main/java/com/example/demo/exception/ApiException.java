package com.example.demo.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer status;

    public ApiException(Integer status, String msg) {
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void getStatus(Integer status) {
        this.status = status;
    }
}
