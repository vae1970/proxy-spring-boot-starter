package com.vae1970.proxy;

import com.vae1970.proxy.parser.impl.XiCiPageParser;
import com.vae1970.proxy.service.ProxyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxySpringBootStarterApplicationTests {

    @Autowired
    private ProxyService proxyService;

    @Test
    public void contextLoads() {
        System.out.println(proxyService.getProxy());
        Thread a = new Thread(() -> System.out.println(XiCiPageParser.listProxy()));
        Thread b = new Thread(() -> System.out.println(XiCiPageParser.listProxy()));
        Thread c = new Thread(() -> System.out.println(XiCiPageParser.listProxy()));
        a.start();
        b.start();
        c.start();
    }

}
