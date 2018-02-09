package com.wab.advice;

import com.alibaba.fastjson.JSONException;
import com.wab.dto.RestResult;
import com.wab.exception.UserAlreadyExistsException;
import com.wab.exception.ValidResultException;
import com.wab.model.constant.RestConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hcq
 * @create 2018-02-08 下午 2:16
 **/

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception e) {
        e.printStackTrace();
        return new RestResult(RestConstant.ERROR.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(NullPointerException.class)
    public RestResult handleException(NullPointerException e) {
        return new RestResult(RestConstant.NULL_POINTER_ERROR);
    }

    @ExceptionHandler(JSONException.class)
    public RestResult handleException(JSONException e) {
        return new RestResult(RestConstant.PARSE_JSON_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public RestResult handleException(UserAlreadyExistsException e) {
        return new RestResult(RestConstant.USER_ALREADY_EXISTS_ERROR);
    }


    @ExceptionHandler(ValidResultException.class)
    public RestResult handleException(ValidResultException e) {
        return new RestResult(RestConstant.Valid_ERROR.getCode(), e.getMessage(), null);
    }

}