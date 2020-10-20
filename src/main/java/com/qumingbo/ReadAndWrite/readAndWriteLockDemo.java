package com.qumingbo.ReadAndWrite;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/20 5:51 下午
 * 读写锁
 */
public class readAndWriteLockDemo {
}

class Cache {
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 读
     */
    public void write(String key, Object value) {
        map.put(key, value);
    }

    /**
     * 写
     */
    public void read(String key) {
        map.get(key);
    }
}
