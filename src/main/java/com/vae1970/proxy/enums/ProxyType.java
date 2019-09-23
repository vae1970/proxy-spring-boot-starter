package com.vae1970.proxy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * proxy type
 *
 * @author vae
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ProxyType {

    /**
     * http
     */
    HTTP(1),
    /**
     * https
     */
    HTTPS(2);

    private Integer value;

    public static ProxyType getType(Integer value) {
        for (ProxyType proxyType : ProxyType.values()) {
            if (proxyType.getValue().equals(value)) {
                return proxyType;
            }
        }
        return null;
    }

    public static ProxyType getType(String value) {
        for (ProxyType proxyType : ProxyType.values()) {
            if (proxyType.name().equals(value)) {
                return proxyType;
            }
        }
        return null;
    }

}
