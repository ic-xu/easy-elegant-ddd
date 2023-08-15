package com.opensource.easyddd.business.contentdetection.south.adapter.client.seewo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author chenxu
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ClientGreenImgSyncRequestBody {


    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("cstoreFileKey")
    private String cstoreFileKey;
    @JsonProperty("cstoreAppId")
    private String cstoreAppId;
}
