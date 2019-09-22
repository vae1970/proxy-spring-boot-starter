package com.vae1970.proxy.consts;

import java.util.concurrent.TimeUnit;

/**
 * @author vae
 */
public class ConfigurationConst {

    /**
     * default max thread number
     */
    public static final Integer DEFAULT_MAX_THREADS = Runtime.getRuntime().availableProcessors();
    /**
     * default max ip number
     */
    public static final Integer DEFAULT_MAX_IPS = 1000;
    /**
     * default period
     */
    public static final Integer DEFAULT_PERIOD = 1000;
    /**
     * default time unit
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

}
