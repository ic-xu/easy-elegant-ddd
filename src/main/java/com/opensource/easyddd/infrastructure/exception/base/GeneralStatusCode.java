package com.opensource.easyddd.infrastructure.exception.base;


import org.springframework.http.HttpStatus;

/**
 * @author mose
 * @description 通用状态码枚举
 * @create 2023/1/9 15:38
 */
public enum GeneralStatusCode {

    SERVER_EX(HttpStatus.INTERNAL_SERVER_ERROR.value()),

    CLIENT_EX(HttpStatus.BAD_REQUEST.value()),
    ;


    /**
     * 通用状态码
     */
    private final int statusCode;

    GeneralStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
