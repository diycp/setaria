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
package com.weghst.setaria.core;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * {@link ObjectMapper} 工具类.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ObjectMapperUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * {@link ObjectMapper#readValue(byte[], Class)}
     *
     * @param content   JSON 字节数组
     * @param valueType 数据类型
     * @param <T>       数据类型
     * @return 数据对象
     */
    public static <T> T readValue(byte[] content, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(InputStream src, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(src, valueType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@link ObjectMapper#writeValueAsBytes(Object)}.
     *
     * @param value 数据对象
     * @return JSON 字节数组
     */
    public static byte[] writeValueAsBytes(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@link ObjectMapper#writeValueAsBytes(Object)}.
     *
     * @param value 数据对象
     * @return JSON 字符串
     */
    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 返回 {@link ObjectMapper} 实例.
     *
     * @return {@link ObjectMapper}
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
