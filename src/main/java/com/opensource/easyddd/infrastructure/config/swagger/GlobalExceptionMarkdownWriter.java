package com.opensource.easyddd.infrastructure.config.swagger;


import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 全局异常文档生成
 */
@Component
public class GlobalExceptionMarkdownWriter implements ApplicationRunner {
    @Resource
    RequestMappingHandlerMapping requestMappingHandlerMapping;



    private TreeMap<Integer, BaseException>  errorCodeMsg= new TreeMap<>();

    @Override
    public void run(ApplicationArguments args) {
        new Thread(()-> {
            try {
                System.out.println("开始生成全局文档");
                executeDoc();
                System.out.println("全局文档生成完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    private void executeDoc() throws IOException {
        FileWriter globalExceptionFW = generateGlobalExceptionFileWriter();
        FileWriter globalRouterFW = generateGlobalRouterFileWriter();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> requestMappingInfos = handlerMethods.keySet();
        for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
            Method method = handlerMethods.get(requestMappingInfo).getMethod();
            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            if(ObjectUtils.isEmpty(patternsCondition)){
                continue;
            }
            Set<String> patterns = patternsCondition.getPatterns();
            for (String patten : patterns) {
                String summary=" 暂时没有 Operation 注解";
                String methodName = method.getName();
                try {
                     summary = method.getAnnotation(Operation.class).summary();
                }catch (Exception ignored){
                }
                if(patten.startsWith("/api")){
                    globalRouterFW.write("| "+patten.toLowerCase()+" |"+summary+" |"+method.getDeclaringClass().getName()+"#"+method.getName()+"|\n");
                }
            }
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (Class<?> exceptionType : exceptionTypes) {
                try {
                    BaseException baseException = exceptionType.asSubclass(BaseException.class).newInstance();
                    errorCodeMsg.put(baseException.getErrorCode(),baseException);
                } catch (Exception ignored) {
                }
            }
        }

        for (Integer errorCode : errorCodeMsg.keySet()) {
            globalExceptionFW.write("| " + errorCode + " |" + errorCodeMsg.get(errorCode).getMessage() + " | "+errorCodeMsg.get(errorCode).getServerMode().getModuleDesc()+" |\n");
        }
        errorCodeMsg.clear();
        errorCodeMsg= null;
        globalExceptionFW.close();
        globalRouterFW.close();
    }

    /**
     * 创建全局的异常书写类
     * @return FileWriter
     * @throws IOException IOException
     */
    private FileWriter generateGlobalExceptionFileWriter() throws IOException {
        File file = new File("doc/markdown/全局异常码列表.md");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter globalExceptionFW = new FileWriter(file);
        globalExceptionFW.write("# 全局异常码列表\n");
        globalExceptionFW.write("| 状态码 | 状态码说明 |所属领域 |\n");
        globalExceptionFW.write("| :-- | :-- | :-- |\n");
        return globalExceptionFW;
    }


    /**
     * 创建全局的路由书写类
     * @return FileWriter
     * @throws IOException IOException
     */
    private FileWriter generateGlobalRouterFileWriter() throws IOException {
        File file = new File("doc/markdown/全局路由信息列表.md");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter globalExceptionFW = new FileWriter(file);
        globalExceptionFW.write("# 全局路由信息列表\n");
        globalExceptionFW.write("| 路由地址 | 接口说明 | 接口所在方法|\n");
        globalExceptionFW.write("| :-- | :-- | :-- |\n");
        return globalExceptionFW;
    }


}
