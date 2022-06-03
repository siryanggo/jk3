package com.yang.jk.Exception;

/**
 * @auther yhjStart
 * @create 2022-03-29 15:54
 */
public class CommonException extends RuntimeException{
    private String message;
    public CommonException(String message) {
        super(message);
        this.message = message;
    }
}
