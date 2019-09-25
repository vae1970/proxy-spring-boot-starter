package com.vae1970.proxy.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 增加一些复合操作
 *
 * @author vae
 */
public class ConcurrentQueue<T> extends LinkedBlockingQueue<T> {
    private ReentrantLock lock;

    /**
     * poll then offer
     *
     * @return T
     */
    public T pollAndOffer() {
        T queue = super.poll();
        if (queue != null) {
            super.offer(queue);
        }
        return queue;
    }

    /**
     * distinct then offer
     *
     * @param t T
     * @return boolean
     */
    public boolean distinctAndOffer(T t) {
        if (this.contains(t)) {
            return false;
        } else {
            return super.offer(t);
        }
    }
}
