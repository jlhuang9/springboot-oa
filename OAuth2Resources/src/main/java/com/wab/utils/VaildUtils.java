package com.wab.utils;

import com.wab.exception.ValidResultException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author hcq
 * @create 2018-02-08 下午 4:39
 **/

public class VaildUtils {

    /**
     * 验证与返回
     * @param vaildResult
     */
    public static void parseExction(BindingResult vaildResult) {
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : vaildResult.getAllErrors()) {
            sb.append("[");
            sb.append(error.getDefaultMessage());
            sb.append("]");
        }
        throw new ValidResultException(sb.toString());
    }
}
