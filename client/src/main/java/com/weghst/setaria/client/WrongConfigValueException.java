package com.weghst.setaria.client;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class WrongConfigValueException extends RuntimeException {

    private String propertyName;
    private String propertyValue;

    /**
     * @param propertyName
     * @param propertyValue
     */
    public WrongConfigValueException(String propertyName, String propertyValue) {
        super(formatMessage(propertyName, propertyValue));
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    /**
     * @param propertyName
     * @param propertyValue
     * @param cause
     */
    public WrongConfigValueException(String propertyName, String propertyValue, Throwable cause) {
        super(formatMessage(propertyName, propertyValue), cause);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    /**
     * @return
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @return
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    private static String formatMessage(String propertyName, String propertyValue) {
        return String.format("错误的属性配置[%s: %s]", propertyName, propertyValue);
    }
}
