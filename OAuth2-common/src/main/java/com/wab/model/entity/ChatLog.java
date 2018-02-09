package com.wab.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Document(collection = "ChatLog")
public class ChatLog implements CredentialsContainer {

    @Field
    private String client_user_id;//公司 — 用户id
    @Field
    private String user_user_id;//客服 - 用户id
    @Field
    private String from_uid;//发送人唯一值
    @Field
    private String to_uid;//接收人唯一值
    @Field
    private String client_id; //公司域
    @Field
    private String user_nick;//发送用户昵称
    @Field
    private String avatar;//发送用户头像
    @Field
    private String type;//消息类型（img、chat、welcome）
    @Field
    private String content;//消息内容
    @Field
    private long time; //时间戳（服务器时间）
//    @Field
    private Date date;
    @Field
    private long c_time; //时间戳（客户时间）
    @Field
    private String msg_id;//消息唯一标识
    @Field
    private String from_type; // 1:游客 2：客服


    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public ChatLog() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClient_user_id() {
        return client_user_id;
    }

    public void setClient_user_id(String client_user_id) {
        this.client_user_id = client_user_id;
    }

    public String getUser_user_id() {
        return user_user_id;
    }

    public void setUser_user_id(String user_user_id) {
        this.user_user_id = user_user_id;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getC_time() {
        return c_time;
    }

    public void setC_time(long c_time) {
        this.c_time = c_time;
    }

    @Override
    public void eraseCredentials() {

    }
}
