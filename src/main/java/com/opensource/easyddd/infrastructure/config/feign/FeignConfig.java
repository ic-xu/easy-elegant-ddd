package com.opensource.easyddd.infrastructure.config.feign;


import feign.Logger;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;

/**
 * @author chenxu
 * @version 1
 * @date 2022/6/23 17:01
 */
@Configurable
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Encoder feignEncoder() {
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(new FormHttpMessageConverter());
        // return new SpringEncoder(objectFactory);
        return new FormEncoder(new SpringEncoder(objectFactory));
    }
}


