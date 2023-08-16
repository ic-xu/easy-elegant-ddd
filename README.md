# easy-elegant-ddd
最容易、最优雅的DDD(领域驱动设计)实践

## 目录
- 项目背景
- 快速开始
- 项目设计
  - [项目结构设计](doc/markdown/项目结构设计.md)
  - [rest-full 接口规范设计](doc/markdown/api/rest-full%20接口设计.md)
  - [rest-full 接口协议设计](doc/markdown/api/restfull接口协议设计.md)
  - [Api接口文档设计](doc/markdown/文档设计.md)
  - [异常设计](doc/markdown/error-code/异常设计.md)
    - [1、HTTP错误码规范](doc/markdown/error-code/1、HTTP错误码规范.md)
    - [2、错误码设计](doc/markdown/error-code/2、错误码设计.md)
    - [3、JAVA 中的异常设计](doc/markdown/error-code/3、JAVA 中的异常设计.md)
    - [4、JAVA 中的异常码自动生成](doc/markdown/error-code/4、JAVA 中的异常码自动生成.md)
  - [路由设计](doc/markdown/全局路由信息列表.md)
 

- 项目实现
  - 项目介绍
  - 项目结构
  - 项目实现

## 项目背景
项目诞生的背景呢，主要有一下几点：
- 最近很火的DDD架构模式，热度很高，自己也看了很多书，但是没有找到一个比较好的实践项目，所以就打算按照开发经验写一个DDD的架构出来，把自己的想法和经验分享给大家
- 自己在多年的开发中，也积累了一些个人独特的开发经验如异常设计、api设计、接口文档设计等，想把这些经验整理出来，形成一个完整的系统的项目和大家一起交流
- 网上很少有整个系统完整的设计 -> 开发的文档，大家都说文档很重要，但是究竟怎么编写文档，业界没有一个统一的规范，借助这个项目，我想把自己的文档编写经验分享给大家

## 快速开始
- 环境配置
  jdk17 + springBoot 3.0 + H2

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

用户名：root

密 码：root
## 项目介绍
```
```
## 项目结构
```
```
