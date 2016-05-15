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
package com.weghst.setaria.client.converter;

import com.weghst.setaria.client.WrongConfigValueException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractValueConverter<T> implements ValueConverter<T> {

    @Override
    public T convert(String key, String value) {
        try {
            return doConvert(value);
        } catch (Exception e) {
            throw new WrongConfigValueException(key, value, e);
        }
    }

    @Override
    public T convert(String key, String value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            doConvert(value);
        } catch (Exception e) {
            return defaultValue;
        }
        return null;
    }

    /**
     * 将属性值转换为目标类型.
     *
     * @param value 值
     * @return 值
     */
    protected abstract T doConvert(String value);
}
