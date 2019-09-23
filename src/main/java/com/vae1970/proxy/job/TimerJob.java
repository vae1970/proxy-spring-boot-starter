package com.vae1970.proxy.job;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.properties.ProxyProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vae
 */
@AllArgsConstructor
public class TimerJob {

    private final LinkedBlockingQueue<Proxy> proxyQueue;
    private final ProxyProperties proxyProperties;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public void run() {
        //  create scheduled thread pool
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1
                , new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());
        //  create thread pool
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("proxy-scan-thread-%d").build();
        final ExecutorService poolExecutor = new ThreadPoolExecutor(0, proxyProperties.getMaxThreads(),
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), threadFactory
                , new ThreadPoolExecutor.AbortPolicy());
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            LOCK.lock();
            try {
                for (int i = 0; i < proxyQueue.remainingCapacity(); i++) {
//                    poolExecutor.submit();
                }
            } finally {
                LOCK.unlock();
            }
        }, 0, proxyProperties.getPeriod(), proxyProperties.getTimeUnit());
    }

}