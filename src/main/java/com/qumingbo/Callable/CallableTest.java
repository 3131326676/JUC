package com.qumingbo.Callable;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: 曲铭博
 * @Date: Created in 22:04 2020/10/19
 * @Description:
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //传统创建线程方式 Runnable无返回值
        // new Thread(new MyThread()).start();
        MyThread myThread = new MyThread();
        // 适配类
        FutureTask<String> futureTask = new FutureTask<>(myThread);

        new Thread(futureTask,"A").start();

        // 获取futureTask的返回结果
        String s = futureTask.get();
        System.out.println(s);
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}