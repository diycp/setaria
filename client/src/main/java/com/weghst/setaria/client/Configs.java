package com.weghst.setaria.client;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class Configs {

    private Configs() {
        throw new AssertionError("Configs 不能创建实例");
    }

    /**
     * 判断是否有配置指定参数.
     *
     * @param name 属性名称
     * @return boolean
     */
    public static boolean containsKey(String name) {
        return getProvider().containsKey(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param name 属性名称
     * @return boolean
     */
    public static boolean getBoolean(String name) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getBoolean(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return boolean
     */
    public static boolean getBoolean(String name, boolean defaultValue) {
        return getProvider().getBoolean(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return int
     */
    public static int getInt(String name) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getInt(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(String name, int defaultValue) {
        return getProvider().getInt(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return long
     */
    public static long getLong(String name) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getLong(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return long
     */
    public static long getLong(String name, long defaultValue) {
        return getProvider().getLong(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return float
     */
    public static float getFloat(String name) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getFloat(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return float
     */
    public static float getFloat(String name, float defaultValue) {
        return getProvider().getFloat(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param name 属性名称
     * @return double
     */
    public static double getDouble(String name) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getDouble(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return double
     */
    public static double getDouble(String name, double defaultValue) {
        return getProvider().getDouble(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return String
     */
    public static String getString(String name) {
        return getProvider().getString(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(String name, String defaultValue) {
        return getProvider().getString(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name) throws WrongConfigValueException {
        return getProvider().getBigDecimal(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name, String defaultValue) {
        return getProvider().getBigDecimal(name, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param name 属性名称
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String name) throws WrongConfigValueException {
        return getProvider().getBigInteger(name);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param name         属性名称
     * @param defaultValue 默认值
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String name, String defaultValue) {
        return getProvider().getBigInteger(name, defaultValue);
    }

    private static ConfigProvider getProvider() {
        return SetariaConfigContext.getSetariaConfig().getConfigProvider();
    }
}
