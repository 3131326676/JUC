package com.qumingbo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author: 曲铭博
 * @Date: Created in 21:07 2020/10/25
 * @Description:
 */
public class LeetCode1 {
    public static void main(String[] args) {
        int i = 1534236469;
        System.out.println(reverse(i));
    }

    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int temp = x % 10 + res * 10;
            if ((temp - x % 10) / 10 != res) {
                return 0;
            }
            res = temp;
            x /= 10;
        }
        return res;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }
}
