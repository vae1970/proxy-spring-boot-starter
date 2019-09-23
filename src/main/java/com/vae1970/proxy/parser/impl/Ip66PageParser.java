package com.vae1970.proxy.parser.impl;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.parser.BasePageParser;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * http://www.66ip.cn/index.html
 *
 * @author dongzhou.gu
 * @date 2019/9/23
 */
public class Ip66PageParser extends BasePageParser {

    private static final AtomicInteger PAGE = new AtomicInteger(-1);

    public static String getUri() {
        int currentPage = PAGE.incrementAndGet();
        return null;
    }

    public static List<Proxy> listProxy() {
        return null;
    }
}
