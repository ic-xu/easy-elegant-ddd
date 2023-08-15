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
public class ClientGreenImgSynCheckResponse {


    @JsonProperty("code")
    private Double code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("taskId")
        private String taskId;
        @JsonProperty("sceneResultList")
        private List<SceneResultListDTO> sceneResultList;

        @NoArgsConstructor
        @Data
        public static class SceneResultListDTO {
            @JsonProperty("scene")
            private Double scene;
            @JsonProperty("label")
            private String label;
            @JsonProperty("suggestion")
            private String suggestion;
            @JsonProperty("score")
            private Double score;
        }
    }
}
