package com.wy.idempotent.exception;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 14:44
 */
public class IdempotentException extends Exception{
    public IdempotentException(String message) {
        super(message);
    }
}
