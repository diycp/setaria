package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class DoubleValueConverter extends AbstractValueConverter<Double> {

    @Override
    protected Double doConvert(String value) {
        return Double.parseDouble(value);
    }
}
