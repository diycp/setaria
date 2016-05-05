package com.weghst.setaria.client.converter;

import com.weghst.setaria.client.WrongConfigValueException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractValueConverter<T> implements ValueConverter<T> {

    @Override
    public T convert(String key, String value) {
        try {
            return doConvert(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(key, value, e);
        }
    }

    @Override
    public T convert(String key, String value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            doConvert(value);
        } catch (Exception e) {
            return defaultValue;
        }
        return null;
    }

    /**
     * 将属性值转换为目标类型.
     *
     * @param value 值
     * @return 值
     */
    protected abstract T doConvert(String value);
}
