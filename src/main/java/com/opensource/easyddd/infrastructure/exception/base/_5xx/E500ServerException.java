package com.opensource.easyddd.infrastructure.exception.base._5xx;


import com.opensource.easyddd.infrastructure.exception.ModuleCode;

/**
 * 包名：com.maxhub.os.exception
 * 文件名：ServerException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * <p>
 * 服务器内部异常的基类
 * <p>
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-01-30 15:19
 **/
public class E500ServerException extends BaseServerException {


    public E500ServerException(ModuleCode serverMode, int customerErrorCode, String message) {
        super(serverMode, customerErrorCode,message);
    }

    @Override
    public int getHttpErrorCode() {
        return 0;
    }
}
