package com.opensource.easyddd.business.contentdetection.domain;

import com.opensource.easyddd.business.contentdetection.south.port.client.ContentDetectionClient;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author chenxu
 */
@Service
public class ContentDetectionService {

    @Resource
    private ContentDetectionClient contentDetectionClient;



    /**
     * 文本鉴黄鉴暴处理
     * @param content text
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean txtContentDetection(String content) {
        return contentDetectionClient.txtContentDetection(content);
    }

    /**
     * 图片内容鉴黄鉴暴处理
     * @param imgInfoDomainList 图片访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean imgContentDetection(List<ImgInfoDomain> imgInfoDomainList) {
        return contentDetectionClient.imgContentDetection(imgInfoDomainList);
    }

    /**
     * 视频内容鉴黄鉴暴处理
     * @param videoUrl 视频访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean videoContentDetection(String videoUrl) {
        return contentDetectionClient.videoContentDetection(videoUrl);
    }
}
