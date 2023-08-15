package com.opensource.easyddd;

import com.opensource.easyddd.infrastructure.config.feign.FeignConfig;
import com.opensource.easyddd.infrastructure.exception.ExceptionGenerate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

/**
 * @author chenxu
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfig.class)
public class EasyDddApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyDddApplication.class, args);
    }


    /**
     * 创建 每一个模块的异常码信息，每次新增模块之侯需要执行一次就会自动生成异常码信息
     *
     * @throws IOException io 异常
     */
    public static void createModelBaseException() throws IOException {
        ExceptionGenerate.createModelBaseException();
    }

}
