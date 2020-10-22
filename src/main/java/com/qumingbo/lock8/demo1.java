package com.qumingbo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:04 2020/10/18
 * @Description:
 */
public class demo1 {
    public static void main(String[] args) {
        phone phone = new phone();

        new Thread(() -> {
            phone.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.callPhone();
        },"B").start();
    }
}

class phone {
    /**
     * synchronized锁的是方法的调用者，通过phone对象调用就锁住phone对象
     */
    public synchronized void sendMsg() {
        System.out.println("send Message");
    }

    public synchronized void callPhone() {
        System.out.println("call other phone");
    }
}
