package com.opensource.easyddd.infrastructure.exception;

/**
 * @author mose
 * @description 模块码
 * @create 2023/1/9 15:38
 */
public enum ModuleCode {
    /**
     * 模块编码
     */
    CHAT_GPT("01","chatGPT相关领域"),
    IMAGE("02","图片相关领域"),
    CONTENT_DETECTION("03","内容检测相关领域"),
    AI_ASSISTANT("04","AI智能助手相关领域"),
    AIR_DISK("05","云盘存储"),
    ;


    /**
     * 模块码，从01开始，每增加一个模块就递增1
     */
    private final String moduleCode;

    private final String moduleDesc;

    ModuleCode(String moduleCode,String moduleDesc) {
        this.moduleCode = moduleCode;
        this.moduleDesc= moduleDesc;
    }

    public String getModuleCode() {
        return moduleCode;
    }


    public String getModuleDesc() {
        return moduleDesc;
    }

    public int getModuleCodeValue(){
        return Integer.parseInt(moduleCode);
    }
}
