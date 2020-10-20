package com.qumingbo.CommonClass;

import java.util.concurrent.CountDownLatch;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/20 5:22 下午
 *
 * 计数器 必须执行任务的时候使用!
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        // 等待计数器归零  再向下执行
        countDownLatch.await();

        System.out.println("计数器完成");
    }
}
