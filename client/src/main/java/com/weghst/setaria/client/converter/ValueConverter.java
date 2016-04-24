package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ValueConverter<T> {

    /**
     * @param name
     * @param value
     * @return
     */
    T convert(String name, String value);

    /**
     * @param name
     * @param value
     * @param defaultValue @return
     */
    T convert(String name, String value, T defaultValue);

}
