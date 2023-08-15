package com.opensource.easyddd.business.contentdetection.south.port.client;


import com.opensource.easyddd.business.contentdetection.domain.ImgInfoDomain;

import java.util.List;

public interface ContentDetectionClient {


    /**
     * 文本鉴黄鉴暴处理
     * @param content 文本内容
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    Boolean txtContentDetection(String content);



    /**
     * 图片内容鉴黄鉴暴处理
     * @param infoInfoList 图片访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    Boolean imgContentDetection(List<ImgInfoDomain> infoInfoList);



    /**
     * 视频内容鉴黄鉴暴处理
     * @param videoUrl 视频访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    Boolean videoContentDetection(String videoUrl);


}
