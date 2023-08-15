package com.opensource.easyddd.infrastructure.exception.base._5xx;

import com.opensource.easyddd.infrastructure.exception.ModuleCode;

/**
 * 包名：com.maxhub.os.exception.base
 * 文件名：NotImplementedException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-02-02 15:24
 **/
public class E503ServiceUnavailableException extends BaseServerException{



    public E503ServiceUnavailableException(ModuleCode serverMode, int customerErrorCode, String message) throws ExceptionCodeError {
        super(serverMode, customerErrorCode,message);
    }

    @Override
    public int getHttpErrorCode() {
        return 4;
    }
}
