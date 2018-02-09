package com.wab.model.base;

/**
 * @author hcq
 * @create 2018-02-08 上午 9:00
 **/

public class BaseResult {
    /**
     * 状态码：1成功，其他为失败
     */
    public int code;

    /**
     * 成功为success，其他为失败原因
     */
    public String description;

    /**
     * 数据结果集
     */
    public Object data;

    public BaseResult(int code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
