package com.opensource.easyddd.infrastructure.exception.base._4xx;


import com.opensource.easyddd.infrastructure.exception.ModuleCode;
import com.opensource.easyddd.infrastructure.exception.base._5xx.ExceptionCodeError;

/**
 * 包名：com.maxhub.os.exception
 * 文件名：ServerException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * <p>
 * 客户端异常的基类
 * <p>
 * /**
 * * {@code 400 Bad Request}.
 * * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
 *
 * <p>
 * <p>
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-01-30 15:19
 **/
public class E400BadRequestException extends BaseClientException {



    public E400BadRequestException(ModuleCode serverMode, int customerErrorCode, String message) throws ExceptionCodeError {
        super(serverMode, customerErrorCode,message);
    }


    /**
     * 这个标识HTTP 状态码是400 的错误类型，过去Http状态状态码就是400
     * <p>
     * {@code 400 Bad Request}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
     */
    @Override
    protected int getHttpErrorCode() {
        return 0;
    }


}
