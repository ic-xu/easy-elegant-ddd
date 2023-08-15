package com.opensource.easyddd.business.contentdetection.domain;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ImgInfoDomain {


    private String imageUrl;


    private String cstoreAppId;

    private String cstoreFileKey;


}
