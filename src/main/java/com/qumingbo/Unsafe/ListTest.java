package com.qumingbo.Unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/19 5:15 下午
 * ConcurrentModificationException并发编程异常
 */
public class ListTest {
    public static void main(String[] args) {
        //并发情况是不是安全的
        /**
         * 解决方案：
         * 1. new Vector<>()
         * 2. Collections.synchronizedList(new ArrayList<>())
         * 3. new CopyOnWriteArrayList<>()
         *
         * CopyOnWrite  写入时赋值简称COW  计算机程序设计领域的一种策略
         * 多个线程调用的时候，读取的时候是固定的，写入时会发生其他线程也在写入。写入的时候复制一份再插入
         * 在写入的时候避免覆盖
         * CopyOnWrite比Vector好在哪里
         * 1.只要有Synchronized的方法执行效率低
         * 2.CopyOnWrite使用的lock锁
         */
        List<String> strings = new CopyOnWriteArrayList<>();
        // List<String> strings = Collections.synchronizedList(new ArrayList<>())
        // List<String> strings = new Vector<>()

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
    }
}
