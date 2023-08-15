package com.opensource.easyddd.infrastructure.exception.base._3xx;

import com.opensource.easyddd.infrastructure.exception.ModuleCode;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import com.opensource.easyddd.infrastructure.exception.base._5xx.ExceptionCodeError;
import org.springframework.http.HttpStatus;

/**
 * 包名：com.maxhub.os.exception.base
 * 文件名：RedirectException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * <p>
 * 重定向异常的基类
 * <p>
 * <p>
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-01-30 15:21
 **/
public class RedirectException extends BaseException {

    public RedirectException(ModuleCode serverMode, int customerErrorCode, String message) throws ExceptionCodeError {
        super(serverMode, customerErrorCode, message);
    }

    @Override
    protected int getHttpErrorCode() {
        return 0;
    }

    public final int getHttpCode() {
        return HttpStatus.MULTIPLE_CHOICES.value();
    }
}

