package com.vae1970.proxy.job;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.properties.ProxyProperties;
import com.vae1970.proxy.util.ConcurrentQueue;
import com.vae1970.proxy.util.PingUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vae
 */
@AllArgsConstructor
public class TimerJob {

    private final ConcurrentQueue<Proxy> proxyQueue;
    private final ProxyProperties proxyProperties;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public void start() {
        LOCK.lock();
        try {
            run();
        } finally {
            LOCK.unlock();
        }
    }

    private void run() {
        //  create scheduled thread pool
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1
                , new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());
        //  create thread pool
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("proxy-scan-thread-%d").build();
        final ExecutorService poolExecutor = new ThreadPoolExecutor(0, PageParserJob.SUPPLIER_QUEUE.size(),
                60L, TimeUnit.SECONDS, new ConcurrentQueue<>(1024), threadFactory
                , new ThreadPoolExecutor.AbortPolicy());
        //  period range: [period / 2, period)
        //  为了对抗反.爬.虫的频率监测(不定时get)
        int period = proxyProperties.getPeriod();
        period = new Random().nextInt(period) / 2 + period / 2;
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int im = Math.min(proxyQueue.remainingCapacity(), PageParserJob.SUPPLIER_QUEUE.size());
            for (int i = 0; i < im; i++) {
                Future<List<Proxy>> future = poolExecutor.submit(new PageParserJob());
                try {
                    Optional.ofNullable(future.get()).ifPresent(proxyList -> proxyList.forEach(proxyQueue::distinctOffer));
                } catch (InterruptedException | ExecutionException ignored) {
                }
            }
        }, 0, period, proxyProperties.getTimeUnit());
        //  check ip
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Proxy proxy = proxyQueue.poll();
            if (PingUtil.ping(proxy)) {
                proxyQueue.distinctOffer(proxy);
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

}