package com.vae1970.proxy.parser;

import com.vae1970.proxy.entity.Proxy;
import com.vae1970.proxy.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.vae1970.proxy.consts.ConfigurationConst.IP_PATTERN;
import static com.vae1970.proxy.consts.ConfigurationConst.PORT_PATTERN;

/**
 * http://www.66ip.cn/index.html
 *
 * @author dongzhou.gu
 * @date 2019/9/23
 */
@SuppressWarnings("SpellCheckingInspection")
public class Ip66PageParser extends BasePageParser {

    private static final AtomicInteger PAGE = new AtomicInteger(1);

    public static String getUri() {
        return "http://www.66ip.cn/" + (PAGE.get() == 1 ? "index" : PAGE.incrementAndGet()) + ".html";
    }

    public static List<Proxy> listProxy() {
        String uri = getUri();
        ResponseEntity<String> responseEntity = HttpUtil.doGet(getHeader(), null, uri, 6000, 6000, Charset.forName("GBK"));
        List<Proxy> proxyList = new ArrayList<>();
        Optional.ofNullable(responseEntity.getBody()).map(Jsoup::parse)
                .map(doc -> doc.select("body div#main div.containerbox.boxindex div table tbody tr"))
                .ifPresent(elements -> {
                    elements.forEach(element -> {
                        String ip = Optional.ofNullable(element.selectFirst("td:eq(0)")).map(Element::html)
                                .filter(i -> IP_PATTERN.matcher(i).matches()).orElse(null);
                        Integer port = Optional.ofNullable(element.selectFirst("td:eq(1)")).map(Element::html)
                                .filter(i -> PORT_PATTERN.matcher(i).matches()).map(Integer::new).orElse(null);
                        boolean protyIncognito = Optional.ofNullable(element.selectFirst("td:eq(3)")).map(Element::html)
                                .map("高匿代理"::equals).orElse(false);
                        if (ip != null && port != null && protyIncognito) {
                            proxyList.add(Proxy.builder().ip(ip).port(port).build());
                        }
                    });
                });
        return proxyList;
    }

    public static void main(String[] args) {
        listProxy();
    }

}
