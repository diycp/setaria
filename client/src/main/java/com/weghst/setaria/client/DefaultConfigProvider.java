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

import org.springframework.util.PropertyPlaceholderHelper;

import com.weghst.setaria.client.converter.*;

/**
 * 默认的配置提供者实现.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
class DefaultConfigProvider implements ConfigProvider {

    private final static PropertyPlaceholderHelper PLACEHOLDER_HELPER = new PropertyPlaceholderHelper("${", "}");

    private final static BooleanValueConverter BOOLEAN_VALUE_CONVERTER = new BooleanValueConverter();
    private final static IntValueConverter INT_VALUE_CONVERTER = new IntValueConverter();
    private final static LongValueConverter LONG_VALUE_CONVERTER = new LongValueConverter();
    private final static FloatValueConverter FLOAT_VALUE_CONVERTER = new FloatValueConverter();
    private final static DoubleValueConverter DOUBLE_VALUE_CONVERTER = new DoubleValueConverter();

    //
    private final Properties properties;

    public DefaultConfigProvider(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("properties 不能为null");
        }
        this.properties = new Properties(properties);
    }

    @Override
    public boolean containsKey(String key) {
        return getProperties().containsKey(key);
    }

    @Override
    public boolean getBoolean(String key) throws ConfigNotFoundException {
        return BOOLEAN_VALUE_CONVERTER.convert(key, getRequiredProperty(key));
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return BOOLEAN_VALUE_CONVERTER.convert(key, getProperty(key), defaultValue);
    }

    @Override
    public int getInt(String key) throws ConfigNotFoundException {
        return INT_VALUE_CONVERTER.convert(key, getRequiredProperty(key));
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return INT_VALUE_CONVERTER.convert(key, getProperty(key), defaultValue);
    }

    @Override
    public long getLong(String key) throws ConfigNotFoundException {
        return LONG_VALUE_CONVERTER.convert(key, getRequiredProperty(key));
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return LONG_VALUE_CONVERTER.convert(key, getProperty(key), defaultValue);
    }

    @Override
    public float getFloat(String key) throws ConfigNotFoundException {
        return FLOAT_VALUE_CONVERTER.convert(key, getRequiredProperty(key));
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return FLOAT_VALUE_CONVERTER.convert(key, getProperty(key), defaultValue);
    }

    @Override
    public double getDouble(String key) throws ConfigNotFoundException {
        return DOUBLE_VALUE_CONVERTER.convert(key, getRequiredProperty(key));
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return DOUBLE_VALUE_CONVERTER.convert(key, getProperty(key), defaultValue);
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String defaultValue) {
        String v = getProperty(key);
        if (v == null) {
            v = defaultValue;
        }
        return v;
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key, null);
    }

    @Override
    public BigDecimal getBigDecimal(String key, String defaultValue) {
        try {
            String value = getString(key, defaultValue);
            if (value == null) {
                return null;
            }

            return new BigDecimal(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(key, defaultValue, e);
        }
    }

    @Override
    public BigInteger getBigInteger(String key) throws ConfigNotFoundException {
        return getBigInteger(key, null);
    }

    @Override
    public BigInteger getBigInteger(String key, String defaultValue) {
        try {
            String value = getString(key, defaultValue);
            if (value == null) {
                return null;
            }
            return new BigInteger(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(key, defaultValue, e);
        }
    }

    @Override
    public Properties getProperties() {
        if (properties == null) {
            return new Properties();
        }
        return new Properties(properties);
    }

    /**
     * @param key
     * @return
     */
    protected String getRequiredProperty(String key) throws ConfigNotFoundException {
        String v = getProperty(key);
        if (v == null) {
            throw new ConfigNotFoundException(key);
        }
        return PLACEHOLDER_HELPER.replacePlaceholders(v, getProperties());
    }

    /**
     * @param key
     * @return
     */
    protected String getProperty(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("属性名称 [propertyName] 不能为 null 或者空字符");
        }

        String v = getProperties().getProperty(key);
        if (v == null) {
            return v;
        }
        return PLACEHOLDER_HELPER.replacePlaceholders(v, getProperties());
    }
}
