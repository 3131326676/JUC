package com.qumingbo.functioninterface;

import java.util.function.Predicate;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/22 4:41 下午
 * 断言型接口
 */
public class Demo2 {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        System.out.println(predicate.test(""));

        Predicate<Integer> predicate1 = (String) -> {
            return String == 0;
        };
    }
}
