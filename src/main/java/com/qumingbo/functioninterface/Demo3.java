package com.qumingbo.functioninterface;

import java.util.function.Consumer;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/22 4:48 下午
 */
public class Demo3 {
    public static void main(String[] args) {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        consumer.accept(1);

        Consumer<Integer> consumer1 = (i)->{
            System.out.println(i);
        };

        consumer1.accept(2);
    }
}
