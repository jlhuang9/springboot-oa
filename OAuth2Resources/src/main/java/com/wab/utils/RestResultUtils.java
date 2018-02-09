package com.wab.utils;

import com.wab.dto.RestResult;
import com.wab.model.constant.RestConstant;

/**
 * @author hcq
 * @create 2018-02-08 下午 4:58
 **/

public class RestResultUtils {

    public static RestResult getOKInstance(String description) {
        return new RestResult(RestConstant.SUCCESS.getCode(), description, null);
    }

    public static RestResult getOKInstance(String description,Object data) {
        return new RestResult(RestConstant.SUCCESS.getCode(), description, data);
    }

    public static RestResult getErrorInstance(String description) {
        return new RestResult(RestConstant.ERROR.getCode(), description, null);
    }

}
