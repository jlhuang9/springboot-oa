package com.wab.entry;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_leave_message")
public class LeaveMessage {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String tel;
    @Column
    private String content;
    @Column
    private String fromUid;
    @Column
    private String company;
    @Column
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
