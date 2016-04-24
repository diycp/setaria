package com.weghst.setaria.client;

/**
 * Created by kevin on 16/4/21.
 */
public class ConfigNotFoundException extends RuntimeException {

    private String propertyName;

    /**
     * @param propertyName
     */
    public ConfigNotFoundException(String propertyName) {
        super(formatMessage(propertyName));
        this.propertyName = propertyName;
    }

    /**
     * @param propertyName
     * @param cause
     */
    public ConfigNotFoundException(String propertyName, Throwable cause) {
        super(formatMessage(propertyName), cause);

        this.propertyName = propertyName;
    }

    /**
     * @return
     */
    public String getPropertyName() {
        return propertyName;
    }

    private static String formatMessage(String propertyName) {
        return String.format("未找到名为[%s]的配置", propertyName);
    }
}
