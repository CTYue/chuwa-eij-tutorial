package com.chuwa.tutorial.t03_exception_handling_Enum;

/**
 * @author b1go
 * @date 5/12/22 11:27 PM
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}