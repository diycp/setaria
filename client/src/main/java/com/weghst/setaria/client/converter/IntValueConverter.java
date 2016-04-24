package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class IntValueConverter extends AbstractValueConverter<Integer> {

    @Override
    protected Integer doConvert(String value) {
        return Integer.parseInt(value);
    }
}
