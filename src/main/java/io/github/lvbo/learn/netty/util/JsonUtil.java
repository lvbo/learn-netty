package io.github.lvbo.learn.netty.util;

import com.google.gson.Gson;

/**
 * @author lvbo
 * @version V1.0
 * @date 2020/3/25 07:11
 */
public class JsonUtil {

    private static final Gson GSON = new Gson();


    public static <T> T fromJson(String jsonString , Class<T> clazz) {
        return GSON.fromJson(jsonString, clazz);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

}
