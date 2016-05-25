/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.client;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Setaria 配置工具类.
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class Configs {

    private Configs() {
        throw new AssertionError("Configs 不能创建实例");
    }

    /**
     * 判断是否有配置指定参数.
     *
     * @param key 属性名称
     * @return boolean
     */
    public static boolean containsKey(String key) {
        return getProvider().containsKey(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param key 属性名称
     * @return boolean
     */
    public static boolean getBoolean(String key) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getBoolean(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return getProvider().getBoolean(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return int
     */
    public static int getInt(String key) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getInt(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(String key, int defaultValue) {
        return getProvider().getInt(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return long
     */
    public static long getLong(String key) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getLong(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return long
     */
    public static long getLong(String key, long defaultValue) {
        return getProvider().getLong(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return float
     */
    public static float getFloat(String key) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getFloat(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return float
     */
    public static float getFloat(String key, float defaultValue) {
        return getProvider().getFloat(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return double
     */
    public static double getDouble(String key) throws ConfigNotFoundException, WrongConfigValueException {
        return getProvider().getDouble(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return double
     */
    public static double getDouble(String key, double defaultValue) {
        return getProvider().getDouble(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return String
     */
    public static String getString(String key) {
        return getProvider().getString(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(String key, String defaultValue) {
        return getProvider().getString(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String key) throws WrongConfigValueException {
        return getProvider().getBigDecimal(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String key, String defaultValue) {
        return getProvider().getBigDecimal(key, defaultValue);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String key) throws WrongConfigValueException {
        return getProvider().getBigInteger(key);
    }

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key         属性名称
     * @param defaultValue 默认值
     * @return BigInteger
     */
    public static BigInteger getBigInteger(String key, String defaultValue) {
        return getProvider().getBigInteger(key, defaultValue);
    }

    private static ConfigProvider getProvider() {
        return SetariaConfigContext.getSetariaConfig().getConfigProvider();
    }
}
