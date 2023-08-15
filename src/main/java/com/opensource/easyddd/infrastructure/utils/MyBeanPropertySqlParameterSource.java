package com.opensource.easyddd.infrastructure.utils;//package com.maxhub.os.infrastructure.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * jdbcTemplate 处理json, uuid等特殊字段的参数处理器
// */
//public class MyBeanPropertySqlParameterSource extends BeanPropertySqlParameterSource {
//
//    private final ObjectMapper objectMapper;
//
//    /**
//     * Create a new BeanPropertySqlParameterSource for the given bean.
//     *
//     * @param object the bean instance to wrap
//     * @param objectMapper
//     */
//    public MyBeanPropertySqlParameterSource(Object object, ObjectMapper objectMapper) {
//        super(object);
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public Object getValue(String paramName) throws IllegalArgumentException {
//        Object o = super.getValue(paramName);
//        if (o instanceof UUID) {
//            return UUIDTypeDescriptor.ToBytesTransformer.INSTANCE.transform((UUID) o);
//        } else if (o instanceof Collection || o instanceof Map) {
//            try {
//                return objectMapper.writeValueAsString(o);
//            } catch (JsonProcessingException e) {
//                throw new IllegalArgumentException(String.format("Parse Param[%s] Value[%s] to JSON error: %s", paramName, o, e.getMessage()));
//            }
//        } else {
//            return o;
//        }
//    }
//}
