package com.wab.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class LeaveMessageVO {

    @NotEmpty(message = "tel 不能为空！")
    @Pattern(regexp = "^1[3-9][0-9]{9}$",message = "手机号格式不正确！")
    private String tel;


    @NotEmpty(message = "content 不能为空！")
    @Length(max = 2000, message = "留言长度不能超过2000字！")
    private String content;
    @NotEmpty(message = "fromUid 不能为空！")
    private String fromUid;
    @NotEmpty(message = "company 不能为空！")
    private String company;
    private Date createTime;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
