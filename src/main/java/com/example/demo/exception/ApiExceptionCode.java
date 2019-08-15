package com.example.demo.exception;

public enum ApiExceptionCode {

    RESOURCE_NOT_FOUND(101, "Resource not found"),
    DATA_IS_NULL(102, "数据异常"),
    NULL_EXCEPTION(103, "空指针异常"),
    PARAMS_EXCEPTION(104, "参数异常"),
    USER_IS_IN_OTHER_COMPANY(105, "此用户已加入其他公司,请先解绑"),
    PROJECT_IS_EXIST(106, "项目已存在"),
    NEED_LOGIN(107, "用户未登录"),
    USER_INFO_ERROR(108, "用户信息异常"),
    COMPANY_IS_EXIST(109, "公司已存在"),
    CODE_ERROR(110,"状态码异常"),
    SMS_CODE_IS_EXPIRE(111,"短信验证码已经失效"),
    SMS_ERROR(112,"验证码错误"),
    HAS_NO_PER(113,"无权限"),
    APPLY_ERROR(114,"申请失败"),
    HAS_NO_COMPANY(115,"未查到公司信息"),
    IS_EXIST(116,"名称已存在")
    ;

    private Integer value;

    private String desc;

    ApiExceptionCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
