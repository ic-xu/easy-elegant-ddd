package com.opensource.easyddd.infrastructure.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wang tengkun
 * @date 2022/7/5
 */
@Data
public class BaseRemoteDTO implements Serializable {

    protected Integer code;

    protected String message;

    /**
     * 默认的请求成功接口响应码
     */
    private static final int DEFAULT_SUCCESS_CODE = 200;

    public boolean isSuccess() {
        return code == null || code == DEFAULT_SUCCESS_CODE;
    }
}
