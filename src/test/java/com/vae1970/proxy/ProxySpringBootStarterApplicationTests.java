package com.vae1970.proxy;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.service.ProxyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxySpringBootStarterApplicationTests {

    private static final ReentrantLock LOCK = new ReentrantLock();

    @Autowired
    private ProxyService proxyService;

    @Test
    public void contextLoads() {
        Timer timer = new Timer();
        long delay = 2000;
        long interval = 1000;

        // 从现在开始 2 秒钟之后启动，每隔 1 秒钟执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String x = Optional.ofNullable(proxyService.getProxy()).map(Proxy::toString).orElse(null);
                System.out.println(x);
            }
        }, delay, interval);

        try {
            LOCK.lock();
            LOCK.newCondition().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
