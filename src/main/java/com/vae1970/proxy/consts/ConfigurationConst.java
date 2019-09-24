package com.vae1970.proxy.consts;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author vae
 */
@SuppressWarnings("WeakerAccess")
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
    public static final Integer DEFAULT_PERIOD = 15;
    /**
     * default time unit
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    public static final String ITEM_IP_REGULAR = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    public static final String PORT_REGULAR = "([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])";

    public static final String IP_REGULAR = ITEM_IP_REGULAR + "\\." + ITEM_IP_REGULAR + "\\." + ITEM_IP_REGULAR + "\\." + ITEM_IP_REGULAR;

    public static final Pattern IP_PATTERN = Pattern.compile(IP_REGULAR);

    public static final Pattern PORT_PATTERN = Pattern.compile(PORT_REGULAR);

}
