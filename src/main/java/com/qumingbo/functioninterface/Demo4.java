package com.qumingbo.functioninterface;

import java.util.function.Supplier;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/22 4:51 下午
 */
public class Demo4 {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "111";
            }
        };


        Supplier<String> supplier1 = () -> {
            return "1024";
        };

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }
}
