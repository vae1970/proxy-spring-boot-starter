package com.vae1970.proxy.service;

import com.vae1970.proxy.entity.Proxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * proxy service
 *
 * @author vae
 */
public class ProxyService {

    @Autowired
    private LinkedBlockingQueue<Proxy> proxyQueue;

    public Proxy getProxy() {
        return proxyQueue.poll();
    }

}
