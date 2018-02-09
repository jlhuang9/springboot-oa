package com.wab.vo;

import com.wab.vo.support.BaseVO;
import org.hibernate.validator.constraints.NotEmpty;

public class ChatLogPageVO extends BaseVO{
    @NotEmpty(message = "from_uid 不能为空！")
    private String from_uid;
    @NotEmpty(message = "to_uid 不能为空")
    private String to_uid;
    private long time;


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


}
