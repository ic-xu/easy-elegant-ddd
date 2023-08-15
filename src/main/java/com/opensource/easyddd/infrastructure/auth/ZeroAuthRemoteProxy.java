package com.opensource.easyddd.infrastructure.auth;

import com.opensource.easyddd.infrastructure.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 包名：com.maxhub.os.infrastructure.remote.service
 * 文件名：Airdisk<br>
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author chenxu
 * @date 2022-11-29 15:32
 **/
@FeignClient(value = "ZeroAuthProxy",
        url = "${app.remote.api.proxy.zero-auth.url}",
        configuration = FeignConfig.class)
public interface ZeroAuthRemoteProxy {


    /**
     * 获取下载地址
     * @param jwtToken jwtToken
     * @return ZeroBaseResponse
     */
    @PostMapping(value = "/api/auth/token?grant_type=client_credentials")
    ZeroBaseResponse getAccessToken(@RequestHeader("Authorization") String jwtToken);


}
