package com.qumingbo.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:20 2020/10/18
 * @Description:
 * 同时调用同步方法和普通方法 因为同步方法延时4s 所以先执行普通方法
 * 两个对象分别调用两个同步方法，因为锁定的是对象，且方法1延时4s  所以先执行方法2
 */
public class demo2 {
    public static void main(String[] args) {
        phone2 phone1 = new phone2();
        phone2 phone2 = new phone2();

        new Thread(() -> {
            phone1.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.callPhone();
        },"B").start();
    }
}
class phone2 {
    /**
     * synchronized锁的是方法的调用者，通过phone对象调用就锁住phone对象
     */
    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send Message");
    }

    public synchronized void callPhone() {
        System.out.println("call other phone");
    }

    public void hello() {
        System.out.println("hello");
    }
}
