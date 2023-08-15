package com.opensource.easyddd.infrastructure.exception.base;

/**
 * @author mose
 * @description
 * @create 2023/1/10 10:45
 */
public interface GeneralErrorCode {

    /**
     * 错误码定义：
     * 通用错误码（3位）+ 模块码（2位）+ 定制错误码（3位，每个定制错误码由模块从001开始按1递增）
     *
     * @return
     */
    int getErrorCode();

    /**
     * 通用错误码（3位）
     *
     * @return
     */
    GeneralStatusCode getGeneralStatusCode();

    /**
     * 模块码（2位）+ 定制错误码（3位，每个定制错误码由模块从001开始按1递增）
     *
     * @return
     */
    int getReasonErrorCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMessage();
}
