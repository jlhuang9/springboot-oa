package com.wab.utils;

/**
 * @author hcq
 * @create 2018-02-06 下午 5:24
 **/

public class MessageUtils {
    public static String deleteAllHTMLTag(String source) {
        if(source == null) {
            return "";
        }
        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }
}
