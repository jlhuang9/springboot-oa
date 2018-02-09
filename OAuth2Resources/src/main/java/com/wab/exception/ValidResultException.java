package com.wab.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author hcq
 * @create 2018-02-08 下午 4:29
 **/

public class ValidResultException extends RuntimeException {
    public ValidResultException(String message) {
        super(message);
    }
}
