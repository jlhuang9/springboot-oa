package com.wab.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wab.vo.support.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author hcq
 * @create 2018-01-29 下午 3:18
 **/

public class CompanyVO extends BaseVO {
    @NotEmpty(message = "companyName 不能为空")
    private String companyName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
