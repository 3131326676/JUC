package com.qumingbo;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/16 4:25 下午
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程是一个单独的资源类   没有任何附属操作
 * 1。属性 2。方法
 */
public class juc2 {
    public static void main(String[] args) {
        //并发   多个线程操作同一个资源类，  把资源类放入线程
        thread2 thread2 = new thread2();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread2.sale();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread2.sale();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread2.sale();
            }
        },"C").start();
    }
}

/**
 * 资源类 OOP
 */
class thread2 {
    /**属性，方法*/
    private int num = 50;

    /**默认非公平锁*/
    Lock lock = new ReentrantLock();

    public void sale() {
        //加锁
        lock.lock();

        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + (num--) + "票，剩余：" + num);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁  解锁一定要放在finally里面
            lock.unlock();
        }
    }
}
