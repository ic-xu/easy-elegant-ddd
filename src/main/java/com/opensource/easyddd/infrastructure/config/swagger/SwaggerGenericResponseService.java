package com.opensource.easyddd.infrastructure.config.swagger;

import com.opensource.easyddd.infrastructure.dto.BaseResponse;
import com.opensource.easyddd.infrastructure.exception.base.BaseException;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.models.MethodAttributes;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.service.GenericResponseService;
import org.springdoc.core.service.OperationService;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

/**
 * 全局异常文档生成
 */
@Component
public class SwaggerGenericResponseService extends GenericResponseService {


    /**
     * Instantiates a new Generic response builder.
     *
     * @param operationService          the operation builder
     * @param returnTypeParsers         the return type parsers
     * @param springDocConfigProperties the spring doc config properties
     * @param propertyResolverUtils     the property resolver utils
     */
    public SwaggerGenericResponseService(OperationService operationService, List<ReturnTypeParser> returnTypeParsers, SpringDocConfigProperties springDocConfigProperties, PropertyResolverUtils propertyResolverUtils) {
        super(operationService, returnTypeParsers, springDocConfigProperties, propertyResolverUtils);
    }


    @Override
    public ApiResponses build(Components components, HandlerMethod handlerMethod, Operation operation, MethodAttributes methodAttributes) {
        ApiResponses apiResponses = super.build(components, handlerMethod, operation, methodAttributes);
        buildExceptionApiResponses(components, handlerMethod, operation, methodAttributes, apiResponses);
        wrapperResponse(apiResponses);
        return apiResponses;
    }

    private void wrapperResponse(ApiResponses apiResponses) {
    }


    private void buildExceptionApiResponses(Components components,
                                            HandlerMethod handlerMethod,
                                            Operation operation,
                                            MethodAttributes methodAttributes,
                                            ApiResponses apiResponses) {
        Class<?>[] exceptionTypes = handlerMethod.getMethod().getExceptionTypes();
        for (Class<?> exceptionType : exceptionTypes) {
            try {
                BaseException baseException = exceptionType.asSubclass(BaseException.class).newInstance();

                Schema schema = new JsonSchema()
                        ._default(BaseResponse.error(50011302, "系统异常"))
                        .addPatternProperty("code", new StringSchema()._default(String.valueOf(baseException.getErrorCode())))
                        .addProperty("code", new StringSchema()._default(String.valueOf(baseException.getErrorCode())))
                        .addProperty("message", new StringSchema()._default(baseException.getMessage()))
                        .addProperty("data", new ObjectSchema()._default(null))
                        .addPatternProperty("data", new ObjectSchema()._default(null))
                        .addPatternProperty("message", new StringSchema()._default(baseException.getMessage()))
                        .$id(String.valueOf(baseException.getErrorCode()))
                        .name(String.valueOf(baseException.getErrorCode()))
                        .specVersion(SpecVersion.V31)
                        .title("全局异常信息接口")
                        .description("全局异常信息接口");
                components.addSchemas(String.valueOf(baseException.getErrorCode()), schema);
                ApiResponse apiResponse = new ApiResponse()
                        .description(baseException.getMessage())
                        .$ref(baseException.getMessage())
                        .content(new Content().addMediaType("application/json", new MediaType()
                                .schema(new ObjectSchema().$ref(String.valueOf(baseException.getErrorCode())))
                                .example(BaseResponse.error(baseException.getErrorCode(), baseException.getMessage()))));
                apiResponses.addApiResponse(String.valueOf(baseException.getErrorCode()), apiResponse);
            } catch (Exception ignored) {
            }
        }
    }

}
