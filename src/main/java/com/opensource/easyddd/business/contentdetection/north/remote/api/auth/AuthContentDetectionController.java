package com.opensource.easyddd.business.contentdetection.north.remote.api.auth;


import com.opensource.easyddd.business.contentdetection.application.businessservices.ContentDetectionBusinessServer;
import com.opensource.easyddd.infrastructure.config.apilog.aop.MethodLog;
import com.opensource.easyddd.infrastructure.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxu
 */
@Tag(name = "内容鉴黄鉴暴 接口组", description = "内容鉴黄鉴暴 接口")
@RestController
@RequestMapping("/api/auth/content-detection")
public class AuthContentDetectionController {



    @Resource
    private ContentDetectionBusinessServer contentDetectionBusinessServer;

    /**
     * 文本鉴黄鉴暴处理
     * @param content text
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    @Operation(summary = "文本鉴黄鉴暴处理,客户端调用，需要鉴权",description = "文本鉴黄鉴暴处理,data 内容是false标识没有敏感内容，true标识有敏感内容")
    @PostMapping("/txt")
    @MethodLog(remark = "文本鉴黄鉴暴处理")
    public BaseResponse<Boolean> txtContentDetection(String content)  {
        Boolean result = contentDetectionBusinessServer.txtContentDetection(content);
        return BaseResponse.ok(result);
    }
}
