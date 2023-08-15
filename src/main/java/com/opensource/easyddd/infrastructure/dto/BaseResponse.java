package com.opensource.easyddd.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wang tengkun
 * @date 2022/5/13
 */
@Data
@AllArgsConstructor
@Schema(description = "REST API 统一返回类型")
public class BaseResponse<T> {
    @Schema(description = "业务状态码", example = "200", required = true)
    private int code;

    @Schema(description = "响应描述", example = "ok", required = true)
    private String message;

    /**
     * 使用泛型便于rpc调用本服务时读取响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    public static BaseResponse<Void> ok() {
        return ok(null);
    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(200, "ok", data);
    }


    public static BaseResponse<Void> error(int code, String errorMsg) {
        return new BaseResponse<>(code,  errorMsg,null);
    }



    public static <T> BaseResponse<T> error(int code, String errorMsg,T data) {
        return new BaseResponse(code,  errorMsg,data);
    }

}
