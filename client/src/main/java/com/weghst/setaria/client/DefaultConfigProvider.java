package com.weghst.setaria.client;

import com.weghst.setaria.client.converter.*;
import org.springframework.util.PropertyPlaceholderHelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DefaultConfigProvider implements ConfigProvider {

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
    public boolean containsKey(String name) {
        return getProperties().containsKey(name);
    }

    @Override
    public boolean getBoolean(String name) throws ConfigNotFoundException {
        return BOOLEAN_VALUE_CONVERTER.convert(name, getRequiredProperty(name));
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        return BOOLEAN_VALUE_CONVERTER.convert(name, getProperty(name), defaultValue);
    }

    @Override
    public int getInt(String name) throws ConfigNotFoundException {
        return INT_VALUE_CONVERTER.convert(name, getRequiredProperty(name));
    }

    @Override
    public int getInt(String name, int defaultValue) {
        return INT_VALUE_CONVERTER.convert(name, getProperty(name), defaultValue);
    }

    @Override
    public long getLong(String name) throws ConfigNotFoundException {
        return LONG_VALUE_CONVERTER.convert(name, getRequiredProperty(name));
    }

    @Override
    public long getLong(String name, long defaultValue) {
        return LONG_VALUE_CONVERTER.convert(name, getProperty(name), defaultValue);
    }

    @Override
    public float getFloat(String name) throws ConfigNotFoundException {
        return FLOAT_VALUE_CONVERTER.convert(name, getRequiredProperty(name));
    }

    @Override
    public float getFloat(String name, float defaultValue) {
        return FLOAT_VALUE_CONVERTER.convert(name, getProperty(name), defaultValue);
    }

    @Override
    public double getDouble(String name) throws ConfigNotFoundException {
        return DOUBLE_VALUE_CONVERTER.convert(name, getRequiredProperty(name));
    }

    @Override
    public double getDouble(String name, double defaultValue) {
        return DOUBLE_VALUE_CONVERTER.convert(name, getProperty(name), defaultValue);
    }

    @Override
    public String getString(String name) {
        return getString(name, null);
    }

    @Override
    public String getString(String name, String defaultValue) {
        String v = getProperty(name);
        if (v == null) {
            v = defaultValue;
        }
        return v;
    }

    @Override
    public BigDecimal getBigDecimal(String name) {
        return getBigDecimal(name, null);
    }

    @Override
    public BigDecimal getBigDecimal(String name, String defaultValue) {
        try {
            String value = getString(name, defaultValue);
            if (value == null) {
                return null;
            }

            return new BigDecimal(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(name, defaultValue, e);
        }
    }

    @Override
    public BigInteger getBigInteger(String name) throws ConfigNotFoundException {
        return getBigInteger(name, null);
    }

    @Override
    public BigInteger getBigInteger(String name, String defaultValue) {
        try {
            String value = getString(name, defaultValue);
            if (value == null) {
                return null;
            }
            return new BigInteger(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(name, defaultValue, e);
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
     * @param name
     * @return
     */
    protected String getRequiredProperty(String name) throws ConfigNotFoundException {
        String v = getProperty(name);
        if (v == null) {
            throw new ConfigNotFoundException(name);
        }
        return PLACEHOLDER_HELPER.replacePlaceholders(v, getProperties());
    }

    /**
     * @param name
     * @return
     */
    protected String getProperty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("属性名称 [propertyName] 不能为 null 或者空字符");
        }

        String v = getProperties().getProperty(name);
        if (v == null) {
            return v;
        }
        return PLACEHOLDER_HELPER.replacePlaceholders(v, getProperties());
    }
}
