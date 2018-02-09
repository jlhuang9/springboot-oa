package com.wab.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hcq
 * @create 2018-01-31 下午 4:25
 **/

public class UserVO {
    @NotEmpty(message = "token 不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
