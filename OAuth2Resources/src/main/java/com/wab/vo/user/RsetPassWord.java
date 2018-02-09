package com.wab.vo.user;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hcq
 * @create 2018-02-01 下午 4:01
 **/

public class RsetPassWord {
    @NotEmpty(message = "token 不能为空")
    private String token;
    @NotEmpty(message = "老密码不能为空")
    private String newPassWord;
    @NotEmpty(message = "新密码不能为空")

    private String oldPassWord;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }
}
