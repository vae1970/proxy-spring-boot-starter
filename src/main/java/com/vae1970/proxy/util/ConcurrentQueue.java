package com.vae1970.proxy.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 增加一些复合操作
 *
 * @author vae
 */
public class ConcurrentQueue<T> extends LinkedBlockingQueue<T> {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public T poll() {
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

    @Override
    public boolean offer(T e) {
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
