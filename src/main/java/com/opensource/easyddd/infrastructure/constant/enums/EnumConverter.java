package com.opensource.easyddd.infrastructure.constant.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 在Controller中的将int参数转为对应的Enum
 * @author wtk
 * @date 2022-04-24
 */
@Component
public class EnumConverter implements ConverterFactory<Integer, BaseDbEnum> {
    @Override
    public <T extends BaseDbEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnumConverter<>(targetType);
    }

    public static class IntegerToEnumConverter<T extends BaseDbEnum> implements Converter<Integer, T> {
        private final Class<T> targetType;

        public IntegerToEnumConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(Integer source) {
            return BaseDbEnum.fromValue(targetType, source);
        }
    }
}
