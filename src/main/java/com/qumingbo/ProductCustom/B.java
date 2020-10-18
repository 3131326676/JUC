package com.qumingbo.ProductCustom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 曲铭博
 * @Date: Created in 21:35 2020/10/18
 * @Description:
 */
public class B {
    public static void main(String[] args) {
        Date2 date2 = new Date2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

/**
 * 判断等待   业务   通知
 */
class Date2 {
    //资源类

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /**
     * 加
     */
    public void increment() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 业务代码
            while (num != 0) {
                // 等待
                condition.await();
            }
            // 业务
            num++;
            System.out.println(Thread.currentThread().getName() + "=" + num);
            //通知其他线程 +完成
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    /**
     * 减
     */
    public void decrement() throws InterruptedException {
       lock.lock();
       try {
           while (num == 0) {
               //等待
               condition.await();
           }
           num--;
           System.out.println(Thread.currentThread().getName() + "=" + num);
           //通知其他线程 -完成
           condition.signalAll();
       } catch (Exception e){
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
    }
}
