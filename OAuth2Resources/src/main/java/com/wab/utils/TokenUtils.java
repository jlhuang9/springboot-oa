package com.wab.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author hcq
 * @create 2018-02-08 下午 2:12
 **/

public class TokenUtils {
    public static String getUserName(String url, String token) throws IOException {
        String s = HttpUtils.getUser(url, token);
        JSONObject jsonObject = JSONObject.parseObject(s);
        return jsonObject.getString("user_name");
    }
}
