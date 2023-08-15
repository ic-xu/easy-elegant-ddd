package com.opensource.easyddd.infrastructure.exception.base._2xx;

import com.opensource.easyddd.infrastructure.exception.ModuleCode;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import com.opensource.easyddd.infrastructure.exception.base._5xx.ExceptionCodeError;
import org.springframework.http.HttpStatus;

/**
 * 包名：com.maxhub.os.exception.base
 * 文件名：SuccessException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * <p>
 * 成功返回码的基类
 * <p>
 * <p>
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-01-30 15:20
 **/
public class SuccessException extends BaseException {


    public final int getHttpCode() {
       return HttpStatus.OK.value();
    }



    public SuccessException(ModuleCode serverMode,int customerErrorCode, String message) throws ExceptionCodeError {
        super(serverMode,customerErrorCode,message);
    }

    @Override
    protected int getHttpErrorCode() {
        return 0;
    }

}
