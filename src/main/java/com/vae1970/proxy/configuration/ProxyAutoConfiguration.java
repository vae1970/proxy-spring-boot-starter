package com.vae1970.proxy.configuration;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.job.TimerJob;
import com.vae1970.proxy.properties.ProxyProperties;
import com.vae1970.proxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.concurrent.LinkedBlockingQueue;

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
    public LinkedBlockingQueue<Proxy> proxyQueue() {
        //  指定max数量
        LinkedBlockingQueue<Proxy> proxyQueue = new LinkedBlockingQueue<>(proxyProperties.getMaxIps());
        new TimerJob(proxyQueue, proxyProperties).run();
        return proxyQueue;
    }

}
