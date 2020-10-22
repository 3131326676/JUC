package com.qumingbo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:29 2020/10/18
 * @Description:
 * 两个同步方法  一个静态  一个非静态
 */
public class demo3 {
    public static void main(String[] args) {
        phone3 phone1 = new phone3();
        phone3 phone2 = new phone3();

        new Thread(() -> {
            phone3.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone3.callPhone();
        },"B").start();
    }
}
class phone3 {
    /**
     * synchronized锁的是方法的调用者，通过phone对象调用就锁住phone对象
     * static 静态方法
     * 类加载时就加载了静态方法  锁定的是类
     */
    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send Message");
    }

    public static synchronized void callPhone() {
        System.out.println("call other phone");
    }

    public void hello() {
        System.out.println("hello");
    }
}
