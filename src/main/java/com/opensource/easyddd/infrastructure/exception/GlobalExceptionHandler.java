package com.opensource.easyddd.infrastructure.exception;

import com.alibaba.fastjson.JSON;
import com.opensource.easyddd.infrastructure.dto.BaseResponse;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import com.opensource.easyddd.infrastructure.exception.base._4xx.BaseClientException;
import com.opensource.easyddd.infrastructure.exception.base._4xx.E400BadRequestException;
import com.opensource.easyddd.infrastructure.exception.base._5xx.BaseServerException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * @author chenxu
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {


    /**
     * 抓Feign 客户端远程调用出现的异常信息
     *
     * @param ex      异常
     * @param request Web请求
     * @return 响应实体
     */
    @ExceptionHandler(value = FeignException.class)
    public Object handleFeignException(FeignException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(JSON.parseObject(ex.contentUTF8()), HttpStatus.valueOf(ex.status()));
    }



    /**
     * 400开头的全部错误类型
     *
     * @param ex      异常
     * @param request Web请求
     * @return 响应实体
     */
    @ExceptionHandler(value = BaseClientException.class)
    public ResponseEntity<BaseResponse<Object>> handleClientException(BaseClientException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(BaseResponse.error(ex.getErrorCode(), ex.getMessage(),ex.getData()), HttpStatus.valueOf(ex.getHttpCode()));
    }



    /**
     * 拦截400开头的错误异常
     *
     * @param ex      异常
     * @param request Web请求
     * @return 响应实体
     */
    @ExceptionHandler(value = E400BadRequestException.class)
    public ResponseEntity<BaseResponse<Object>> handleClientException(E400BadRequestException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(BaseResponse.error(ex.getErrorCode(), ex.getMessage(),ex.getData()), HttpStatus.valueOf(ex.getHttpCode()));
    }
    /**
     * 500开头的所有错误类型
     *
     * @param ex      异常
     * @param request Web请求
     * @return 响应实体
     */
    @ExceptionHandler(value = BaseServerException.class)
    public ResponseEntity<BaseResponse<Object>> handleServerException(BaseServerException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(BaseResponse.error(ex.getErrorCode(), ex.getMessage(),ex.getData()), HttpStatus.valueOf(ex.getHttpCode()));
    }

    /**
     * 拦截所有定义的异常
     *
     * @param ex      异常
     * @param request Web请求
     * @return 响应实体
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<BaseResponse<Object>> handleBaseException(BaseException ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(BaseResponse.error(ex.getErrorCode(), ex.getMessage(),ex.getData()), HttpStatus.valueOf(ex.getHttpCode()));
    }
}
