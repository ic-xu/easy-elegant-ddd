# easy-elegant-ddd
最容易、最优雅的DDD(领域驱动设计)实践

## 目录
- 项目背景
- 快速开始
- 前置知识
  - DDD架构模式介绍
  - 相关概念介绍
- 项目设计
  - 项目结构设计
  - 文档设计
  - 异常设计
  - 错误码设计
  - 路由设计
  - 服务设计

- 项目实现
  - 项目介绍
  - 项目结构
  - 项目实现

## 项目背景
```
```

## 快速开始
- 环境配置
  jdk17 + springBoot + H2


- 克隆代码到本地，导入 idea
```shell
git clone https://github.com/ic-xu/easy-elegant-ddd.git
```
- 使用如下命令运行项目
```shell
mvn spring-boot:run
```
- 也可以直接运行application 中的main 方法，如下：
```java
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
```
- 项目启动后，访问如下地址，可以看到swagger 接口文档

http://localhost:8080/doc.html

### 
## 前置知识
```
```
## 项目介绍
```
```
## 项目结构
```
```
