package com.opensource.easyddd.infrastructure.auth;

import com.opensource.easyddd.infrastructure.constant.HeaderConstant;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 包名：vip.maxhub.web.md.infrastructure.auth
 * 文件名：OpenPlatform.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2023-04-07 19:46
 **/

@Component
public class OpenPlatform {

    @Resource
    private ZeroAuthRemoteProxy zeroAuthRemoteProxy;

    @Value("${app.remote.api.proxy.zero-auth.client-id}")
    private String clientId;

    @Value("${app.remote.api.proxy.zero-auth.client-secret}")
    private String secret;




    private String getClientBasic() {
        return HeaderConstant.BASIC + Base64.getEncoder().encodeToString((clientId + ":" + secret).getBytes(StandardCharsets.UTF_8));
    }



    /**
     * 获取客户端token信息
     * @return 返回token字段
     */
    public String getClientToken(){
        ZeroBaseResponse accessToken = zeroAuthRemoteProxy.getAccessToken(getClientBasic());
        return "Bearer " + accessToken.getData().getAccessToken();

    }

}
