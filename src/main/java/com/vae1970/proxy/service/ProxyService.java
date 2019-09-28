package com.vae1970.proxy.service;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.util.ConcurrentQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * proxy service
 *
 * @author vae
 */
@DependsOn("proxyQueue")
@Service
public class ProxyService {

    @Autowired
    private ConcurrentQueue<Proxy> proxyQueue;

    public Proxy getProxy() {
        System.out.println("size: " + proxyQueue.size());
        return proxyQueue.poll();
    }

}
