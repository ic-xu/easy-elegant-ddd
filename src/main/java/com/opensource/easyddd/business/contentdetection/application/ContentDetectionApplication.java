package com.opensource.easyddd.business.contentdetection.application;


import com.opensource.easyddd.business.contentdetection.domain.ContentDetectionService;
import com.opensource.easyddd.business.contentdetection.domain.ImgInfoDomain;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author chenxu
 */
@Service
public class ContentDetectionApplication {


    @Resource
    private ContentDetectionService contentDetectionService;

    /**
     * 文本鉴黄鉴暴处理
     * @param content text
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean txtContentDetection(String content) {
        return contentDetectionService.txtContentDetection(content);
    }

    /**
     * 图片内容鉴黄鉴暴处理
     * @param imageUrl 图片访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean imgContentDetection(List<ImgInfoDomain> imgInfoDomainList) {
        return contentDetectionService.imgContentDetection(imgInfoDomainList);
    }

    /**
     * 视频内容鉴黄鉴暴处理
     * @param videoUrl 视频访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean videoContentDetection(String videoUrl) {
        return contentDetectionService.videoContentDetection(videoUrl);
    }
}
