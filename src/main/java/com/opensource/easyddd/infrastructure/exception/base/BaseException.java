package com.opensource.easyddd.infrastructure.exception.base;


import com.opensource.easyddd.infrastructure.exception.ModuleCode;

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
public abstract class BaseException extends Exception {


    /**
     * http状态码,直接设置到http请求状态中
     *
     * @return httpStatus
     */
    public abstract  int getHttpCode();

    /**
     * 2位系统码，同一个服务中可能涉及到多个系统、模块；为这些系统、模块设计不同的状态吗，有助于提高系统bug的定位能力
     */
    private final int serverCode;

    /**
     * 自定义异常码，同一个模块或者系统中，同一种 http status 的错误原因可能有多个，这时候通过一个自定义的3位异常码标识不同类型的返回码，能够精确的定义不同的错误信息
     */
    private final int customerErrorCode;

    private final ModuleCode serverMode;


    private Object data;




    public BaseException(ModuleCode serverMode, int customerErrorCode, String message) {
        super(message);
        this.serverMode = serverMode;
        this.serverCode = serverMode.getModuleCodeValue();
        if (customerErrorCode > CustomerExceptionCode.MAX_CUSTOMER) {
            throw new RuntimeException("自定义的异常码只能是3位有效数字");
        }
        this.customerErrorCode = customerErrorCode;
    }


    /**
     * 获取HTTP 状态码的后两位
     *
     * @return 状态码的后两位
     */
    protected abstract int getHttpErrorCode();



    public ModuleCode getServerMode(){
        return serverMode;
    }

    /**
     * 返回这个组合之后的错误码
     *
     * @return 整个组合之后的8位错误吗
     */
    public final int getErrorCode() {
        return (getHttpCode() * 100000) + (serverCode * 1000) + customerErrorCode;
    }


    public Object getData() {
        return data;
    }


    public void setReturnData(Object data) {
        this.data = data;
    }
}
