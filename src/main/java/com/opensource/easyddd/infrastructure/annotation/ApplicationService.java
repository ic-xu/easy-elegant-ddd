package com.opensource.easyddd.infrastructure.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 应用层服务注解，用于标记应用层服务
 *
 * @author chenxu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ApplicationService {
}
