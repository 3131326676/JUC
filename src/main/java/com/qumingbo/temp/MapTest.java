package com.qumingbo.temp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 曲铭博
 * @Date: Created in 21:45 2020/10/19
 * @Description:
 */
public class MapTest {
    public static void main(String[] args) {
        // HashMap<String, Object> map = new HashMap<>();
        Map<String, Object> map = new ConcurrentHashMap<>();
        // 加载因子  初始化容量

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
