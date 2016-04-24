package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class FloatValueConverter extends AbstractValueConverter<Float> {

    @Override
    protected Float doConvert(String value) {
        return Float.parseFloat(value);
    }
}
