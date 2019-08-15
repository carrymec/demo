package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = 7228708275471562893L;
    
    private String status;

    private String msg;

    private T data;

    private ServerResponse (String status){
        this.status = status;
    }

    private ServerResponse (String status,String msg){
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse (String status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse (String status,String msg,T data){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    @JsonIgnore //使之不再序列化
    public boolean isSuccess(){
        return this.status == ServerCode.SUCCESS.getCode();
    }

    public String getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg() {
        return msg;
    }

    /*
    创建成功响应
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ServerCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMsg(String msg){
        return new ServerResponse<T>(ServerCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ServerCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ServerCode.SUCCESS.getCode(),msg,data);
    }

    /*
    创建失败响应
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ServerCode.ERROR.getCode(), ServerCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMsg(String errorMsg){
        return new ServerResponse<T>(ServerCode.ERROR.getCode(),errorMsg);
    }

    public static <T> ServerResponse<T> createErrorCodeMsg(String errCode, String errMsg){
        return new ServerResponse<T>(errCode,errMsg);
    }
}
