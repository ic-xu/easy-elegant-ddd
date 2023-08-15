package com.opensource.easyddd.infrastructure.exception.base._5xx;

import com.opensource.easyddd.infrastructure.exception.ModuleCode;

/**
 * 包名：com.maxhub.os.exception.base
 * 文件名：ExeceptionCodeError.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-01-30 15:58
 **/
public class ExceptionCodeError extends E500ServerException {


    public ExceptionCodeError(ModuleCode serverMode, String message) {
        super(serverMode, 999,message);
    }
}
