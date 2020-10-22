package com.qumingbo.readandwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/20 5:51 下午
 * 读写锁 ReadWriteLock
 * 独占锁(写锁) 一次只能一个线程进行写操作
 * 共享锁(读锁) 多个线程可以同时持有
 */
public class readAndWriteLockDemo {
    public static void main(String[] args) {
        CacheLock cache = new CacheLock();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.write(String.valueOf(temp), temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.read(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * Lock锁 ReentrantLock锁 ReentrantReadWriteLock锁版
 */
class CacheLock {
    private volatile Map<String, Object> map = new HashMap<String, Object>();
    // 读写锁   比ReentrantLock更细颗粒度的锁
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 写
     * 写的时候只希望一个线程进行读或者写
     */
    public void write(String key, Object value) {
        // 写锁
        readWriteLock.writeLock().lock();
        try {
            map.put(key, value);
            System.out.println("插入key:" + key + "value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读
     * 读的时候任何线程都可以读取
     */
    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            Object s = map.get(key);
            System.out.println("读取key为:" + key + "对应的value为:" + s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * synchronized版
 */
class CacheSynchronized {
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 读
     */
    public synchronized void write(String key, Object value) {
        map.put(key, value);
        System.out.println("插入key:" + key + "value:" + value);
    }

    /**
     * 写
     */
    public synchronized void read(String key) {
        Object s = map.get(key);
        System.out.println("读取key为:" + key + "对应的value为:" + s);
    }
}

/**
 * 无锁版
 */
class Cache {
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 读
     */
    public void write(String key, Object value) {
        map.put(key, value);
        System.out.println("插入key:" + key + "value:" + value);
    }

    /**
     * 写
     */
    public void read(String key) {
        Object s = map.get(key);
        System.out.println("读取key为:" + key + "对应的value为:" + s);
    }
}
