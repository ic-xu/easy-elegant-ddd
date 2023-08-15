package com.opensource.easyddd.infrastructure.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author chenxu
 */
@Data
@Accessors(chain = true)
public class UserBaseInfo implements Serializable {
    private String displayName;

    private String userId;

    private String avatar;
}
