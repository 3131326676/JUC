package com.qumingbo.Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 曲铭博
 * @Date: Created in 22:47 2020/10/21
 * @Description:
 * 使用线程池创建线程
 */
public class Demo1 {
    public static void main(String[] args) {
        // 单个线程 只能有单个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 固定大小的线程池 大小由参数指定
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        // // 不固定大小的线程池 默认大小为Integer.MAX_VALUE
        ExecutorService executorService2 = Executors.newCachedThreadPool();


        // 线程池用完关闭线程池
        try {
            for (int i = 0; i < 100; i++) {
                executorService2.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
