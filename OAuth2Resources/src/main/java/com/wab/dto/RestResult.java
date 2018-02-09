package com.wab.dto;

import com.wab.model.base.BaseResult;
import com.wab.model.constant.RestConstant;

/**
 * @author hcq
 * @create 2018-02-08 上午 8:47
 **/

public class RestResult extends BaseResult{

    public RestResult(RestConstant constant) {
        this(constant, null);
    }
    public RestResult(RestConstant constant, Object data) {
        this(constant.getCode(), constant.getDescription(), data);
    }
    public RestResult(int code, String description, Object data) {
        super(code, description, data);
    }


}
