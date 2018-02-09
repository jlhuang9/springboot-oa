package com.wab.vo.user;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hcq
 * @create 2018-02-05 下午 2:24
 **/

public class UserBrowserTagVO {
    @NotEmpty(message = "uid 不能为空")
    private String uId;
    private String companyId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
