package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LongValueConverter extends AbstractValueConverter<Long> {

    @Override
    protected Long doConvert(String value) {
        return Long.parseLong(value);
    }
}
