package com.vae1970.proxy.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 增加一些复合操作
 *
 * @author vae
 */
@SuppressWarnings("UnusedReturnValue")
public class ConcurrentQueue<T> extends LinkedBlockingQueue<T> {

    private final ReentrantLock lock = new ReentrantLock();

    public ConcurrentQueue() {
    }

    public ConcurrentQueue(int capacity) {
        super(capacity);
    }

    /**
     * poll then offer
     *
     * @return data
     */
    public T pollAndOffer() {
        lock.lock();
        try {
            T queue = super.poll();
            if (queue != null) {
                super.offer(queue);
            }
            return queue;
        } finally {
            lock.unlock();
        }
    }

    /**
     * distinct and offer
     *
     * @param e data
     * @return boolean
     */
    public boolean distinctOffer(T e) {
        lock.lock();
        try {
            if (this.contains(e)) {
                return false;
            } else {
                return super.offer(e);
            }
        } finally {
            lock.unlock();
        }
    }

}
