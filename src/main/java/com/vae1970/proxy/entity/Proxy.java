package com.vae1970.proxy.entity;

import com.vae1970.proxy.enums.ProxyType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * ip
 *
 * @author vae
 */
@Data
@Builder
public class Proxy {

    /**
     * proxy ip
     */
    private String ip;
    /**
     * proxy port
     */
    private Integer port;
    /**
     * proxy type
     */
    private ProxyType type;
    /**
     * proxy available
     */
    private Boolean available;
    /**
     * proxy last successful time
     */
    private Date lastSuccessfulTime;

}
