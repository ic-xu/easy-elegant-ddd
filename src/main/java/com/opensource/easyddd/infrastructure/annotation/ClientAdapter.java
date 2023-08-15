package com.opensource.easyddd.infrastructure.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author chenxu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ClientAdapter {
}
