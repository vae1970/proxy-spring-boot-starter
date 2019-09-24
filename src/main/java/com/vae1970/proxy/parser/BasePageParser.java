package com.vae1970.proxy.parser;

import com.vae1970.proxy.entity.Proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongzhou.gu
 * @date 2019/9/23
 */
@SuppressWarnings({"SpellCheckingInspection", "WeakerAccess"})
public class BasePageParser {

    /**
     * 获取url
     * 子类必须重写此方法！！！
     *
     * @return url
     */
    public static String getUri() {
        throw new IllegalArgumentException();
    }

    /**
     * 获取proxy
     * 子类必须重写此方法！！！
     *
     * @return proxy
     */
    public static List<Proxy> listProxy() {
        throw new IllegalArgumentException();
    }

    /**
     * get http header
     *
     * @return http header
     */
    public static Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>(5);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8,en-US;q=0.7,en;q=0.6");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        header.put("Content-Type", " text/html; charset=UTF-8");
        header.put("Accept-Encoding", "gzip,deflate");
        return header;
    }

}
