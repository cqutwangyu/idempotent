package com.wy.idempotent.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 15:02
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotentException.class)
    public String idempotentException(IdempotentException e){
        return e.getMessage();
    }
}
