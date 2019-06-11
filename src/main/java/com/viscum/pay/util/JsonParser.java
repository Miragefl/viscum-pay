package com.viscum.pay.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * json实体类转换工具类
 *
 * @author fenglei
 */
public class JsonParser {
    private static ObjectMapper mapper = new ObjectMapper();

    public JsonParser() {
    }

    public static String modelToJSON(Object obj) throws IOException {
        if (obj instanceof InputStream) {
            return "It is InputStream";
        } else if (obj instanceof OutputStream) {
            return "It is OutputStream";
        } else if (obj instanceof ServletRequest) {
            return "It is HttpServletRequest";
        } else {
            return obj instanceof ServletResponse ? "It is HttpServletResponse" : mapper.writeValueAsString(obj);
        }
    }

    public static <T> T jsonToModel(String jsonStr, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonStr, clazz);
    }

    public static <T> List<T> jsonToList(String jsonStr, TypeReference<List<T>> type) throws IOException {
        return (List) mapper.readValue(jsonStr, type);
    }

    public static <K, T> Map<K, T> jsonToMap(String jsonStr, TypeReference<Map<K, T>> type) throws IOException {
        return (Map) mapper.readValue(jsonStr, type);
    }

    public static <K, T> List<Map<K, T>> jsonToListMap(String jsonStr, TypeReference<List<Map<K, T>>> type) throws IOException {
        return (List) mapper.readValue(jsonStr, type);
    }

    static {
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setSerializationInclusion(Include.NON_NULL);
    }
}