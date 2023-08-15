package com.opensource.easyddd.infrastructure.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 领域服务层注解，用于标记领域服务
 *
 * @author chenxu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DomainService {
}
