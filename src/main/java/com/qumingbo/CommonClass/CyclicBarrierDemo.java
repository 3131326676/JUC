package com.qumingbo.CommonClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/20 5:30 下午
 * 加法计数器
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /* 两个重载
            (1) CyclicBarrier(int) 只计数
            (2) CyclicBarrier(int, Runnable) 计数完成执行一个线程
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("完成计数");
        });

        for (int i = 0; i < 7; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
