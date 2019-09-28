package com.vae1970.proxy.configuration;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.job.TimerJob;
import com.vae1970.proxy.properties.ProxyProperties;
import com.vae1970.proxy.util.ConcurrentQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * proxy auto configuration
 *
 * @author vae
 */
@Configuration
@EnableConfigurationProperties(ProxyProperties.class)
public class ProxyAutoConfiguration {

    @Autowired
    private ProxyProperties proxyProperties;

    @Bean
    public ConcurrentQueue<Proxy> proxyQueue() {
        //  指定max数量
        ConcurrentQueue<Proxy> proxyQueue = new ConcurrentQueue<>(proxyProperties.getMaxIps());
        new TimerJob(proxyQueue, proxyProperties).run();
        return proxyQueue;
    }

}
