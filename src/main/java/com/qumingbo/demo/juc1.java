package com.qumingbo.demo;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/16 4:25 下午
 */

/**
 * 线程是一个单独的资源类   没有任何附属操作
 * 1。属性 2。方法
 */
public class juc1 {
    public static void main(String[] args) {
        //并发   多个线程操作同一个资源类，  把资源类放入线程
        thread1 thread1 = new thread1();

        //函数式接口  lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread1.sale();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread1.sale();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                thread1.sale();
            }
        }, "c").start();
    }
}

/**
 * 资源类 OOP
 */
class thread1 {
    //属性，方法
    private int num = 50;

    //synchronized本质就是：队列，锁
    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出" + (num--) + "票，剩余：" + num);
        }
    }
}
