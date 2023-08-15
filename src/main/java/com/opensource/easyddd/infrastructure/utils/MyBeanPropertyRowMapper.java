package com.opensource.easyddd.infrastructure.utils;//package com.maxhub.os.infrastructure.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.lang.Nullable;
//
//import java.beans.PropertyDescriptor;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Map;
//import java.util.UUID;
//
//import static java.lang.String.format;
//
///**
// * jdbcTemplate 处理json, uuid等特殊字段的结果集处理器
// */
//public class MyBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {
//
//    private ObjectMapper objectMapper;
//
//    public MyBeanPropertyRowMapper(Class<T> mappedClass) {
//        super(mappedClass);
//    }
//
//    public MyBeanPropertyRowMapper(Class<T> mappedClass, ObjectMapper objectMapper) {
//        super(mappedClass);
//        this.objectMapper = objectMapper;
//    }
//
//    @Nullable
//    @Override
//    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
//        if (UUID.class == pd.getPropertyType()) {
//            return UUIDTypeDescriptor.ToBytesTransformer.INSTANCE.parse(rs.getBytes(index));
//        } else if (Collection.class == pd.getPropertyType() || Map.class == pd.getPropertyType()) {
//            return parseJsonColumn(rs, index, pd.getPropertyType());
//        } else {
//            return super.getColumnValue(rs, index, pd);
//        }
//    }
//
//    public static <T> MyBeanPropertyRowMapper<T> newInstance(Class<T> mappedClass) {
//        return new MyBeanPropertyRowMapper<>(mappedClass);
//    }
//
//    public static <T> MyBeanPropertyRowMapper<T> newInstance(Class<T> mappedClass, ObjectMapper objectMapper) {
//        return new MyBeanPropertyRowMapper<>(mappedClass, objectMapper);
//    }
//
//    private ObjectMapper existingObjectMapper() throws SQLException {
//        if (objectMapper == null) {
//            throw new SQLException("objectMapper must be not null for Parse JSON Column");
//        }
//        return objectMapper;
//    }
//
//    private Object parseJsonColumn(ResultSet rs, int index, Class<?> requiredType) throws SQLException {
//        try {
//            return existingObjectMapper().readValue(rs.getString(index), requiredType);
//        } catch (JsonProcessingException e) {
//            throw new SQLException(format("Parse column [%s] to [%s] error: %s", index,requiredType.getName(), e.getMessage()));
//        }
//    }
//}
