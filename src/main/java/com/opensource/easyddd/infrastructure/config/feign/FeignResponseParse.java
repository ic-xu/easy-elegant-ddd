package com.opensource.easyddd.infrastructure.config.feign;

import com.alibaba.fastjson.JSON;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 包名：vip.maxhub.web.md.infrastructure.config.feign
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
public class FeignResponseParse<T> {


    /**
     * 解析返回内容
     * @param response 响应值
     * @param clazz 类型
     * @return 返回消息
     * @param <T> 转化的最终格式
     * @throws IOException 异常信息
     */
    public static <T> T parseResponse(Response response, Class<T> clazz) throws IOException {
        Charset charset = response.charset();
        String string = Util.toString(response.body().asReader(charset));
        return JSON.parseObject(string, clazz);
    }

}
