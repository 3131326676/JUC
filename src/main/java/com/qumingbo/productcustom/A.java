package com.qumingbo.productcustom;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/16 5:17 下午
 */

/**
 * 线程通信问题  ：生产者和消费者
 * 线程交替执行 操作同一个变量
 */
public class A {
    public static void main(String[] args) {
        Date date = new Date();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A=").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B=").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C=").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D=").start();
    }
}

/**
 * 判断等待   业务   通知
 */
class Date {
    //资源类

    private int num = 0;

    /**
     * 加
     */
    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            // 等待
            this.wait();
        }
        // 业务
        num++;
        System.out.println(Thread.currentThread().getName() + num);
        //通知其他线程 +完成
        this.notifyAll();
    }

    /**
     * 减
     */
    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            //等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + num);
        //通知其他线程 -完成
        this.notifyAll();
    }
}
