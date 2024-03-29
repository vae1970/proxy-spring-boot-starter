package com.vae1970.proxy.job;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.parser.Ip66PageParser;
import com.vae1970.proxy.parser.XiCiPageParser;
import com.vae1970.proxy.util.ConcurrentQueue;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author dongzhou.gu
 * @date 2019/9/23
 */
public class PageParserJob implements Callable<List<Proxy>> {

    static final ConcurrentQueue<Supplier<List<Proxy>>> SUPPLIER_QUEUE = new ConcurrentQueue<>();

    static {
        SUPPLIER_QUEUE.offer(XiCiPageParser::listProxy);
        SUPPLIER_QUEUE.offer(Ip66PageParser::listProxy);
    }

    @Override
    public List<Proxy> call() throws Exception {
        Supplier<List<Proxy>> run = SUPPLIER_QUEUE.poll();
        if (run != null) {
            SUPPLIER_QUEUE.offer(run);
            List<Proxy> proxyList = run.get();
            System.out.println("size: " + proxyList.size());
            return proxyList;
        } else {
            return null;
        }
    }
}
