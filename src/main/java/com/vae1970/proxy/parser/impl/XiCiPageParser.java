package com.vae1970.proxy.parser.impl;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.enums.ProxyType;
import com.vae1970.proxy.parser.BasePageParser;
import com.vae1970.proxy.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.vae1970.proxy.consts.ConfigurationConst.IP_PATTERN;
import static com.vae1970.proxy.consts.ConfigurationConst.PORT_PATTERN;

/**
 * https://www.xicidaili.com/
 *
 * @author dongzhou.gu
 * @date 2019/9/23
 */
public class XiCiPageParser extends BasePageParser {

    private static final AtomicInteger PAGE = new AtomicInteger();
    private static final String URI = "https://www.xicidaili.com/nn/%d";

    public static String getUri() {
        return String.format(URI, PAGE.incrementAndGet());
    }

    public static List<Proxy> listProxy() {
        String uri = getUri();
        ResponseEntity<String> responseEntity = HttpUtil.doGet(getHeader(), null, uri);
        List<Proxy> proxyList = new ArrayList<>();
        Optional.ofNullable(responseEntity.getBody()).map(Jsoup::parse)
                .map(doc -> doc.select("body div#wrapper div#body table tbody tr"))
                .ifPresent(elements ->
                        elements.forEach(element -> {
                            String ip = Optional.ofNullable(element.selectFirst("td:eq(1)")).map(Element::html)
                                    .filter(i -> IP_PATTERN.matcher(i).matches()).orElse(null);
                            Integer port = Optional.ofNullable(element.selectFirst("td:eq(2)")).map(Element::html)
                                    .filter(i -> PORT_PATTERN.matcher(i).matches()).map(Integer::new).orElse(null);
                            ProxyType proxyType = Optional.ofNullable(element.selectFirst("td:eq(5)")).map(Element::html)
                                    .map(ProxyType::getType).orElse(null);
                            if (ip != null && port != null && proxyType != null) {
                                proxyList.add(Proxy.builder().ip(ip).port(port).type(proxyType).build());
                            }
                        })
                );
        System.out.println("xi ci ");
        return proxyList;
    }

    public static void main(String[] args) {
        listProxy();
    }

}
