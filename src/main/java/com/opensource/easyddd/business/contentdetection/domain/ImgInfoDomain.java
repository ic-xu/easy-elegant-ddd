package com.opensource.easyddd.business.contentdetection.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Entity
@Accessors(chain = true)
public class ImgInfoDomain {


    private String imageUrl;


    private String cstoreAppId;

    private String cstoreFileKey;


    private boolean isSensitive;

    private String resoult;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
