package com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo;


import com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author chenxu
 */
@FeignClient(name = "green-feign-client", url = "${app.remote.api.proxy.green.url}")
public interface GreenProxy {


    /**
     *  @see <a href="https://swqa.gz.cvte.cn/#/interfaceTest/project/SeewoGreen/interfaceView/api/431/interface/2576">图片异步审核</a>
     * @param appKey
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/image/check-async", produces = "application/json;charset=UTF-8")
    ClientGreenImgResponse greenImgDetectionDetectionAsync(@RequestHeader("app-key") String appKey, @RequestBody ClientGreenImgAsyncRequestBody requestBody);



    /**
     *  @see <a href="https://swqa.gz.cvte.cn/#/interfaceTest/project/SeewoGreen/interfaceView/api/431/interface/13637">图片同步审核</a>
     * @param appKey
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/image/check-sync", produces = "application/json;charset=UTF-8")
    ClientGreenImgSynCheckResponse greenImgDetectionDetectionSync(@RequestHeader("app-key") String appKey, @RequestBody ClientGreenImgSyncRequestBody requestBody);






    /**
     *
     * @see <a href="https://swqa.gz.cvte.cn/#/interfaceTest/project/SeewoGreen/interfaceView/api/431/interface/2460">文本内容检测</a>
     *
     * @param appKey
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/text/check", produces = "application/json;charset=UTF-8")
    ClientGreenTxtResponse greenTxtDetectionDetectionSync(@RequestHeader("app-key") String appKey, @RequestBody ClientGreenTxtRequestBody requestBody);


}
