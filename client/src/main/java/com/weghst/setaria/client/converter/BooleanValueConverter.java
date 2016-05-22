/**
 * Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.setaria.client.converter;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class BooleanValueConverter extends AbstractValueConverter<Boolean> {

    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";

    public static final String VALUE_ON = "on";
    public static final String VALUE_OFF = "off";

    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";

    public static final String VALUE_1 = "1";
    public static final String VALUE_0 = "0";

    @Override
    protected Boolean doConvert(String value) {
        if (VALUE_TRUE.equalsIgnoreCase(value)
                || VALUE_ON.equalsIgnoreCase(value)
                || VALUE_YES.equalsIgnoreCase(value)
                || VALUE_1.equals(value)) {
            return true;
        } else if (VALUE_FALSE.equalsIgnoreCase(value)
                || VALUE_OFF.equalsIgnoreCase(value)
                || VALUE_NO.equalsIgnoreCase(value)
                || VALUE_0.equals(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("非法的 boolean 属性值 [" + value + "]");
        }
    }
}
