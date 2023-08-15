package com.opensource.easyddd.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wang tengkun
 */
@Configuration
@EnableAsync
@EnableTransactionManagement
public class AppConfig {
    public final int localPort;

    public AppConfig(@Value("${server.port:8080}") Integer localPort) {
        this.localPort = localPort;
    }
}
