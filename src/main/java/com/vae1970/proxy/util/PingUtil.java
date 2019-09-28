package com.vae1970.proxy.util;

import com.vae1970.proxy.entity.Proxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author dongzhou.gu
 * @date 2019/9/28
 */
public class PingUtil {

    private static final ConcurrentQueue<String> URL_QUEUE = new ConcurrentQueue<>();

    static {
        URL_QUEUE.offer("https://www.baidu.com/");
        URL_QUEUE.offer("https://www.oschina.net/");
        URL_QUEUE.offer("https://www.jianshu.com/");
        URL_QUEUE.offer("https://www.csdn.net/");
        URL_QUEUE.offer("https://www.douban.com/");
        URL_QUEUE.offer("https://www.zhihu.com/");
        URL_QUEUE.offer("https://music.163.com/");
        URL_QUEUE.offer("https://y.qq.com/");
        URL_QUEUE.offer("https://cn.bing.com/");
        URL_QUEUE.offer("http://www.dgtle.com/");
        URL_QUEUE.offer("http://www.runoob.com/");
        URL_QUEUE.offer("http://www.imooc.com/");
    }

    public static boolean ping(Proxy proxy) {
        ResponseEntity<String> responseEntity = HttpUtil.doGet(URL_QUEUE.pollAndOffer(), proxy);
        return responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

}
