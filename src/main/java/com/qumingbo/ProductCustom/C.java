package com.qumingbo.ProductCustom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 曲铭博
 * @Date: Created in 21:48 2020/10/18
 * @Description:
 */
public class C {
    public static void main(String[] args) {
        Date3 date3 = new Date3();
        new Thread(() -> {
            date3.printA();
        }, "A").start();

        new Thread(() -> {
            date3.printB();
        }, "B").start();

        new Thread(() -> {
            date3.printC();
        }, "C").start();
    }
}

class Date3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;

    public void printA() {
        lock.lock();
        try {
            while (num != 1) {
                //1等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName());
            num = 2;
            //唤醒2
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (num != 2) {
                //2等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName());
            num = 3;
            //3唤醒
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (num != 3) {
                //3等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName());
            num = 1;
            //唤醒1
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}