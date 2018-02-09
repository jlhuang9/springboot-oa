package com.wab.vo;

import com.wab.model.entity.AppUser;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hcq
 * @create 2018-02-08 下午 1:48
 **/

public class UserDetails extends AppUser{
    @NotEmpty(message = "token不能为空！")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
