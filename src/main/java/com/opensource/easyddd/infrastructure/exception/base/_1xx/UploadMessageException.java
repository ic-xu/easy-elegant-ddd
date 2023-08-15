package com.opensource.easyddd.infrastructure.exception.base._1xx;

import com.opensource.easyddd.infrastructure.exception.ModuleCode;
import com.opensource.easyddd.infrastructure.exception.base._5xx.ExceptionCodeError;

/**
 * 包名：com.maxhub.os.exception.base
 * 文件名：MessageException.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @group [][] xx
 * @date 2023-01-30 15:20
 **/
public class UploadMessageException extends BaseMessageException{



    public UploadMessageException(ModuleCode serverMode,int customerErrorCode,String message) throws ExceptionCodeError {
        super(serverMode,customerErrorCode ,message );
    }

    @Override
    public int getHttpErrorCode() {
        return 1;
    }
}
