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
import java.util.Properties;

/**
 * 业务参数提供者接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface ConfigProvider {

    /**
     * 判断是否有配置指定参数.
     *
     * @param key 属性名称
     * @return boolean
     */
    boolean containsKey(String key);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code false}.
     *
     * @param key 属性名称
     * @return boolean
     */
    boolean getBoolean(String key) throws ConfigNotFoundException, WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return boolean
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return int
     */
    int getInt(String key) throws ConfigNotFoundException, WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return int
     */
    int getInt(String key, int defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return long
     */
    long getLong(String key) throws ConfigNotFoundException, WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return long
     */
    long getLong(String key, long defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return float
     */
    float getFloat(String key) throws ConfigNotFoundException, WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return float
     */
    float getFloat(String key, float defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code 0}.
     *
     * @param key 属性名称
     * @return double
     */
    double getDouble(String key) throws ConfigNotFoundException, WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return double
     */
    double getDouble(String key, double defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return String
     */
    String getString(String key);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return String
     */
    String getString(String key, String defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return BigDecimal
     */
    BigDecimal getBigDecimal(String key) throws WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    BigDecimal getBigDecimal(String key, String defaultValue);

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回{@code null}.
     *
     * @param key 属性名称
     * @return BigInteger
     */
    BigInteger getBigInteger(String key) throws WrongConfigValueException;

    /**
     * 返回指定属性名称的参数值, 如果不存在则返回指定的默认值.
     *
     * @param key          属性名称
     * @param defaultValue 默认值
     * @return BigInteger
     */
    BigInteger getBigInteger(String key, String defaultValue);

    /**
     * @return
     */
    Properties getProperties();
}
