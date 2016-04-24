package com.weghst.setaria.client.converter;

import com.weghst.setaria.client.WrongConfigValueException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractValueConverter<T> implements ValueConverter<T> {

    @Override
    public T convert(String name, String value) {
        try {
            return doConvert(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(name, value, e);
        }
    }

    @Override
    public T convert(String name, String value, T defaultValue) {
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
     * @param value
     * @return
     */
    protected abstract T doConvert(String value);
}
