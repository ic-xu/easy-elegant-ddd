package com.opensource.easyddd.business.contentdetection.south.adapter.client;

import com.opensource.easyddd.business.contentdetection.domain.ImgInfoDomain;
import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.*;
import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto.ClientGreenImgSynCheckResponse;
import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto.ClientGreenImgSyncRequestBody;
import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto.ClientGreenTxtRequestBody;
import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto.ClientGreenTxtResponse;
import com.opensource.easyddd.business.contentdetection.south.port.client.ContentDetectionClient;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContentDetectionClientImp implements ContentDetectionClient {


    @Resource
    private GreenProxy greenProxy;


    private String appKey = "os-business-line";

    /**
     * 文本鉴黄鉴暴处理
     *
     * @param content text
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    @Override
    public Boolean txtContentDetection(String content) {
        List<ClientGreenTxtRequestBody.ContentsDTO> contentsDTOList = new ArrayList<>();

        contentsDTOList.add(new ClientGreenTxtRequestBody.ContentsDTO().setContent(content).setDataId(UUID.randomUUID().toString()));

        ClientGreenTxtRequestBody clientGreenTxtRequestBody = new ClientGreenTxtRequestBody()
//                .setMode("http://www.baidu.com")
                .setCallback("http://www.baidu.com")
                .setUserUid(UUID.randomUUID().toString())
                .setContents(contentsDTOList);


        ClientGreenTxtResponse clientGreenTxtResponse = greenProxy.greenTxtDetectionDetectionSync(appKey, clientGreenTxtRequestBody);
        return clientGreenTxtResponse.getCode() != 0;
    }


    /**
     * 图片内容鉴黄鉴暴处理
     *
     * @param infoInfoList 图片访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    @Override
    public Boolean imgContentDetection(List<ImgInfoDomain> imgInfoDomainList) {

//        GreenImgAsyncRequestBody greenImgAsyncRequestBody = new GreenImgAsyncRequestBody();
//        List<GreenImgAsyncRequestBody.ContentsDTO> contentsDTOList =new ArrayList<>();


        List<ClientGreenImgSynCheckResponse> result = new ArrayList<>();
        for (ImgInfoDomain imgInfoDomain : imgInfoDomainList) {
//            GreenImgAsyncRequestBody.ContentsDTO contentsDTO = new GreenImgAsyncRequestBody.ContentsDTO();
//            contentsDTO.setCstoreAppId(imgInfoDomain.getCstoreAppId());
//            contentsDTO.setImageUrl(imgInfoDomain.getImageUrl());
//            contentsDTO.setDataId(imgInfoDomain.getCstoreFileKey());
//            contentsDTO.setCstoreFileKey(imgInfoDomain.getCstoreFileKey());
//            contentsDTOList.add(contentsDTO);

            ClientGreenImgSyncRequestBody clientGreenImgSyncRequestBody = new ClientGreenImgSyncRequestBody()
//                    .setCstoreAppId(imgInfoDomain.getCstoreAppId())
                    .setImageUrl(imgInfoDomain.getImageUrl())
//                    .setCstoreFileKey(imgInfoDomain.getCstoreFileKey())
                    ;


            ClientGreenImgSynCheckResponse clientGreenImgSynCheckResponse = greenProxy.greenImgDetectionDetectionSync(appKey, clientGreenImgSyncRequestBody);
            result.add(clientGreenImgSynCheckResponse);

        }
//        greenImgAsyncRequestBody.setContents(contentsDTOList);
//        greenImgAsyncRequestBody.setCallback("http://www.baidu.com");
//        GreenImgResponse greenImgResponse = greenProxy.greenImgDetectionDetectionAsync(appKey, greenImgAsyncRequestBody);


//        if(greenImgResponse.getCode()!=0){
//            throw new
//        }
//        System.out.println(greenImgResponse);

        for (ClientGreenImgSynCheckResponse clientGreenImgSynCheckResponse : result) {
            if (clientGreenImgSynCheckResponse.getCode() != 0) {
                return true;
            }


            List<ClientGreenImgSynCheckResponse.DataDTO.SceneResultListDTO> sceneResultList = clientGreenImgSynCheckResponse.getData().getSceneResultList();
            for (ClientGreenImgSynCheckResponse.DataDTO.SceneResultListDTO sceneResultListDTO : sceneResultList) {
                if (!"pass".equals(sceneResultListDTO.getSuggestion())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 视频内容鉴黄鉴暴处理
     *
     * @param videoUrl 视频访问地址
     * @return Boolean true标识有敏感内容，false标识没有敏感内容
     */
    @Override
    public Boolean videoContentDetection(String videoUrl) {
        return false;
    }
}
