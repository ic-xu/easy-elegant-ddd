package com.opensource.easyddd.infrastructure.config.feign;

import com.alibaba.fastjson.JSON;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import feign.Response;
import feign.Util;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 包名：com.maxhub.os.infrastructure.config.feign
 * 文件名：FeignResponseParseUtils.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-04-07 20:15
 **/
public class FeignResponseUtils {



    /**
     * 检查返回值是否成功
     *
     * @param response  响应值
     * @param exception 异常信息
     * @param <I>       异常类型
     * @throws I 异常信息
     */
    public static <I extends BaseException> void checkResponseSuccess(Response response, I exception) throws I {
        if (response.status() != HttpStatus.OK.value()) {
            throw exception;
        }
    }



    /**
     * 解析返回内容
     *
     * @param response 响应值
     * @param clazz    类型
     * @param <T>      转化的最终格式
     * @return 返回消息
     * @throws IOException 异常信息
     */
    public static <T, I extends BaseException> T parseResponse(Response response, Class<T> clazz, I exception) throws I {
        Charset charset = response.charset();
        try {
            String string = Util.toString(response.body().asReader(charset));
            return JSON.parseObject(string, clazz);
        } catch (Exception e) {
            throw exception;
        }
    }

}
