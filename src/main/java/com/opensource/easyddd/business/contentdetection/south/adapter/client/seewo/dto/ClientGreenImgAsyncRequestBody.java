package com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenxu
 */
@NoArgsConstructor
@Data
public class ClientGreenImgAsyncRequestBody {


    @JsonProperty("callback")
    private String callback;
    @JsonProperty("contents")
    private List<ContentsDTO> contents;

    @NoArgsConstructor
    @Data
    public static class ContentsDTO {
        @JsonProperty("dataId")
        private String dataId;
        @JsonProperty("imageUrl")
        private String imageUrl;
        @JsonProperty("cstoreAppId")
        private String cstoreAppId;
        @JsonProperty("cstoreFileKey")
        private String cstoreFileKey;
    }
}
