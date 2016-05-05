package com.weghst.setaria.client.converter;

import com.weghst.setaria.client.WrongConfigValueException;

/**
 * 字符器类型转换器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ValueConverter<T> {

    /**
     * 将属性值转换为目标数据类型.
     *
     * @param key   键
     * @param value 值
     * @return 值
     * @throws WrongConfigValueException 如果值类型与目标类型不匹配
     */
    T convert(String key, String value) throws WrongConfigValueException;

    /**
     * 将属性值转换为目标数据类型. 如果值类型不匹配或者未设置则返回默认值.
     *
     * @param key          键
     * @param value        值
     * @param defaultValue 默认值
     * @return 值
     */
    T convert(String key, String value, T defaultValue);

}
