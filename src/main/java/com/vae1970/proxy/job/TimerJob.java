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
@SuppressWarnings("WeakerAccess")
@AllArgsConstructor
public class TimerJob {

    private final LinkedBlockingQueue<Proxy> proxyQueue;
    private final ProxyProperties proxyProperties;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public void run() {
        //  创建线程池
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("proxy-scan-thread-%d").build();
        final ExecutorService service = new ThreadPoolExecutor(0, proxyProperties.getMaxThreads(),
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), threadFactory
                , new ThreadPoolExecutor.AbortPolicy());
        //  创建定时任务线程池
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1
                , new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> {
            LOCK.lock();
            try {
                for (int i = 0, im = proxyProperties.getMaxIps() - proxyQueue.size(); i < im; i++) {
                    execute();
                }
            } finally {
                LOCK.unlock();
            }
        }, 0, proxyProperties.getPeriod(), proxyProperties.getTimeUnit());
    }

    public void execute() {

    }

}