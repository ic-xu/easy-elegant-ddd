package com.opensource.easyddd.infrastructure.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 基础的数据库枚举，解决JPA的枚举局限性
 * <p>
 * </p>
 */
public interface BaseDbEnum {
    /**
     * 用于显示的枚举名
     *
     * @return
     */
    String display();

    /**
     * 按枚举的value获取枚举实例
     *
     * @param enumType
     * @param value
     * @param <T>
     * @return
     */
    static <T extends BaseDbEnum> T fromValue(Class<T> enumType, int value) {
        for (T object : enumType.getEnumConstants()) {
            if (value == object.value()) {
                return object;
            }
        }
        throw new IllegalArgumentException("No enum value " + value + " of " + enumType.getCanonicalName());
    }

    /**
     * 存储到数据库的枚举值
     *
     * @return
     */
    @JsonValue
    int value();
}
