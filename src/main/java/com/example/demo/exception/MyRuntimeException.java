package com.example.demo.exception;

public class MyRuntimeException extends ApiException {

    public MyRuntimeException(Integer code, String msg) {
        super(code,msg);
    }

}
