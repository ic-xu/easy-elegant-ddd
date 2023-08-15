package com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author chenxu
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ClientGreenTxtRequestBody {


    @JsonProperty("contents")
    private List<ContentsDTO> contents;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("userUid")
    private String userUid;
    @JsonProperty("callback")
    private String callback;

    @NoArgsConstructor
    @Data
    public static class ContentsDTO {
        @JsonProperty("dataId")
        private String dataId;
        @JsonProperty("content")
        private String content;
    }
}
