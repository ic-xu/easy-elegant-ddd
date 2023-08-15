package com.opensource.easyddd.infrastructure.exception.base._5xx;


import com.opensource.easyddd.infrastructure.exception.ModuleCode;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;

/**
 * 包名：com.maxhub.os.exception
 * 文件名：ServerException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * <p>
 * 客户端异常的基类
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
public abstract class BaseServerException extends BaseException {


    public BaseServerException(ModuleCode serverMode, int customerErrorCode, String message) {
        super(serverMode, customerErrorCode, message);
    }

    /**
     * http状态码,直接设置到http请求状态中
     *
     * @return httpStatus
     */
    public final int getHttpCode() {
        return  500 + getHttpErrorCode();
    }


}
