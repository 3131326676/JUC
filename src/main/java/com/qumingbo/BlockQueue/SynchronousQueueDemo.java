package com.qumingbo.BlockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:05 2020/10/21
 * @Description: 同步队列
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        for (int i = 0; i < 3; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    queue.put(String.valueOf(String.valueOf(temp)));
                    System.out.println(Thread.currentThread().getName() + "put:" + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }


        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "get:" +queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
