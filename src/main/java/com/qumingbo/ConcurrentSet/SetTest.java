package com.qumingbo.ConcurrentSet;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author: 曲铭博
 * @Date: Created in 21:20 2020/10/19
 * @Description:
 * ConcurrentModificationException并发修改异常
 * 解决方案：
 *      synchronizedSet方法
 *      copyOnWriteSet方法
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        // CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
