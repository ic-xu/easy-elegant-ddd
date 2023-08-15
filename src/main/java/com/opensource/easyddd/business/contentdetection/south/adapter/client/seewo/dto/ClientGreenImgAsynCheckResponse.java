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
public class ClientGreenImgAsynCheckResponse {


    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("taskId")
        private String taskId;
        @JsonProperty("dataId")
        private String dataId;
    }
}
