package com.opensource.easyddd.infrastructure.utils;


import com.opensource.easyddd.infrastructure.config.AppConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wang tengkun
 * @date 2022/4/20
 */
public class IPUtil {
    public static final String LOCAL_IP;
    public static final int LOCAL_PORT;
    static {
        try {
            LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
            LOCAL_PORT = SpringUtil.getBean(AppConfig.class).localPort;
        } catch (UnknownHostException e) {
            throw new RuntimeException("无法获取本地IP地址");
        }
    }
}
