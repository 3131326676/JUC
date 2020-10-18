package com.qumingbo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:35 2020/10/18
 * @Description:
 */
public class demo4 {
    public static void main(String[] args) {
        phone4 phone = new phone4();
        phone4 phone1 = new phone4();

        new Thread(() -> {
            phone4.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone1.callPhone();
        },"B").start();
    }
}
class phone4 {
    /**
     * static 静态同步方法 锁定的是类
     */
    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send Message");
    }

    /**
     * 锁的是对象
     */
    public synchronized void callPhone() {
        System.out.println("call other phone");
    }

    public void hello() {
        System.out.println("hello");
    }
}