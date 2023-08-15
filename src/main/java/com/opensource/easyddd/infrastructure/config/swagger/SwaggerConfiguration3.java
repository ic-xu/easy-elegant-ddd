package com.opensource.easyddd.infrastructure.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Swagger3配置
 */
@Configuration
@Slf4j
public class SwaggerConfiguration3 {


    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("XXX 后台接口文档")
                        .version("1.0")
                        .description("XXXX 后台接口文档")
                        .termsOfService("xxxxxxxx")
                        .contact(new Contact().name("陈旭").email("chenxu9741@cvte.com").url(""))
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com"))
                ).addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(
                        new Components()
                                .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                        new SecurityScheme().name(HttpHeaders.AUTHORIZATION)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")))
                ;
    }
}
