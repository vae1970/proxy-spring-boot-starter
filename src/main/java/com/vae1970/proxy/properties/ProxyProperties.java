package com.vae1970.proxy.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

import static com.vae1970.proxy.consts.ConfigurationConst.*;

/**
 * configuration properties
 *
 * @author vae
 */
@ConfigurationProperties(prefix = "proxy")
@Data
public class ProxyProperties {

    /**
     * max thread number
     */
    private Integer maxThreads = DEFAULT_MAX_THREADS;
    /**
     * max ip number
     */
    private Integer maxIps = DEFAULT_MAX_IPS;
    /**
     * period between scan proxy
     */
    private Integer period = DEFAULT_PERIOD;
    /**
     * period time unit
     */
    private TimeUnit timeUnit = DEFAULT_TIME_UNIT;

}
