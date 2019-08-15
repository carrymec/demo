package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ServerCode {

    //成功
    SUCCESS("SUCCESS", "成功"), //失败
    ERROR("NOT_LOGIN", "失败"), //需要登陆
    NEED_LOGIN("NOT_LOGIN", "需要登陆"), //参数错误
    ;
    private final String code;

    private final String desc;

    ServerCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
