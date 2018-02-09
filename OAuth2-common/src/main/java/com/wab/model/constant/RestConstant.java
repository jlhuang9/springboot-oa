package com.wab.model.constant;

public enum RestConstant {

    SUCCESS(1000, "成功!"),
    ERROR(1001, "失败!"),
    ADD_ERROR(1001, "添加失败!"),
    Valid_ERROR(1100, "验证失败！"),
    OLD_PASSWORD_ERROR(1102, "原始密码错误！"),
    USER_ALREADY_EXISTS_ERROR(1201, "用户已经存在！"),
    NULL_POINTER_ERROR(1501, "系统内部错误"),
    PARSE_JSON_ERROR(1502, "数据解析错误"),
    IP_READ_ERROR(1503, "ip识别错误"),
    ;
    public int code;

    public String description;


    RestConstant(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
