package com.qumingbo.commonclass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/20 5:40 下午
 * 信号量机制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程数量  限流使用!
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                // semaphore.acquire(); 得到
                // semaphore.release(); 释放
                try {
                    semaphore.acquire();
                    System.out.println("获得线程");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("释放线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
