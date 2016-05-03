package com.weghst.setaria.core;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class ObjectMapperUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        OBJECT_MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(byte[] content, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * @param value
     * @return
     */
    public static byte[] writeValueAsBytes(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
