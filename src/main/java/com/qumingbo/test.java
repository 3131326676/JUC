package com.qumingbo;

import java.util.*;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/23 3:55 下午
 */
public class test {
    public static void main(String[] args) {
        // String s1 = "mary this is";
        // String s2 = "this is lucy";
        // String[] strings = noRespect(s1, s2);
        //
        // // Arrays.stream(strings).collect(Collectors.toList()).forEach(System.out::println);
        //
        // String s3 = "asddsa";
        // Boolean aBoolean = ifReverse(s3);
        // System.out.println(aBoolean);

        int arr[] = new int[]{6,2,7,4,9,5,1};
        int len = arr.length - 1;
        quickSort(arr, 0, len);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    /**
     * 求两个数组的差集
     *
     * @param s1 数组1
     * @param s2 数组2
     * @return 差集
     */
    public static String[] noRespect(String s1, String s2) {
        String[] strings = s1.split(" ");
        String[] strings1 = s2.split(" ");

        Collection list = new ArrayList<String>(Arrays.asList(strings));
        Collection list1 = new ArrayList<String>(Arrays.asList(strings1));

        // 求交集
        list.retainAll(list1);

        // 求并集
        Set result = new HashSet<>();
        result.addAll(Arrays.asList(strings));
        result.addAll(list1);

        Collection a = new ArrayList(list);
        Collection b = new ArrayList(result);

        // 并集-交集=差集
        b.removeAll(a);

        return (String[]) b.stream().toArray(String[]::new);
    }

    /**
     * 回文数
     *
     * @param s 字符串
     * @return 是否为回文数
     */
    public static Boolean ifReverse(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (!Character.isLetter(s.charAt(i))) {
                i++;
            }
            while (!Character.isLetter(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }


    public static void quickSort(int[] a, int low, int height) {
        int i, j, t, temp;
        if (low > height) {
            return;
        }
        //传过来的参数进行赋值
        temp = a[low];
        // 左侧指针
        i = low;
        // 右侧指针
        j = height;
        while (i != j) {
            //顺序很重要，先从右边开始找
            while (a[j] >= temp && i < j) {
                j--;
            }
            //再从左边找：小于基准数的数
            while (a[i] <= temp && i < j) {
                i++;
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //a[i]给low的位置也就是0，就是基准数
        a[low] = a[i];
        //基准数给a[i]
        a[i] = temp;

        //递归基准值左侧数组
        quickSort(a, low, i - 1);
        //递归基准值右侧数组
        quickSort(a, i + 1, height);
    }
}
