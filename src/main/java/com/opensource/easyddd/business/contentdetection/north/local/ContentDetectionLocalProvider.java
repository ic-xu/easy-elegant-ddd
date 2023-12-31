package com.opensource.easyddd.business.contentdetection.north.local;


import com.opensource.easyddd.business.contentdetection.application.businessservices.ContentDetectionBusinessServer;
import com.opensource.easyddd.business.contentdetection.domain.ImgInfoDomain;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author chenxu
 */
@Service
public class ContentDetectionLocalProvider {

    @Resource
    private ContentDetectionBusinessServer contentDetectionBusinessServer;


    /**
     * 文本鉴黄鉴暴处理
     * @param content text
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */

    public Boolean txtContentDetection(String content) {
        return contentDetectionBusinessServer.txtContentDetection(content);
    }



    /**
     * 图片内容鉴黄鉴暴处理
     * @param imgInfoDomainList 图片访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    public Boolean imgContentDetection(List<ImgInfoDomain> imgInfoDomainList) {
        return contentDetectionBusinessServer.imgContentDetection(imgInfoDomainList);
    }

}
