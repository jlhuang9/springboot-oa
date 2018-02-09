package com.wab.dto;

import java.util.Set;

/**
 * @author hcq
 * @create 2018-01-31 下午 4:19
 **/

public class UserDto {
    private String username;
    private String nick;
    private String email;
    private Set<String> avatar;
    private String clientId;
    private String welcome;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getAvatar() {
        return avatar;
    }

    public void setAvatar(Set<String> avatar) {
        this.avatar = avatar;
    }
}
