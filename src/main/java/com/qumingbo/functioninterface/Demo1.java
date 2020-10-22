package com.qumingbo.functioninterface;

import java.util.function.Function;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/22 2:42 下午
 * 函数型接口
 */
public class Demo1 {
    public static void main(String[] args) {
        Function function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer o) {
                return 1;
            }
        };

        System.out.println(function.apply(1));

        // lambda版
        Function function1 = Integer -> { return 2; };
        System.out.println(function1.apply(2));
    }
}
