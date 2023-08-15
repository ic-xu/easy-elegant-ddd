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
public class ClientGreenTxtResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("code")
    private Integer code;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("dataId")
        private String dataId;
        @JsonProperty("suggestion")
        private String suggestion;
        @JsonProperty("message")
        private String message;
        @JsonProperty("taskId")
        private String taskId;
        @JsonProperty("illegalType")
        private Integer illegalType;
        @JsonProperty("illegalWords")
        private List<IllegalWordsDTO> illegalWords;
        @JsonProperty("score")
        private Double score;

        @NoArgsConstructor
        @Data
        public static class IllegalWordsDTO {
            @JsonProperty("word")
            private String word;
            @JsonProperty("startIndex")
            private Integer startIndex;
            @JsonProperty("endIndex")
            private Integer endIndex;
        }
    }
}
