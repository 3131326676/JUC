package com.qumingbo;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(diagonalSum(a));

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(1);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        System.out.println(getDecimalValue(listNode));

        System.out.println(":::::::::::::::::::::::::");
        int[] start = {1, 2, 3};
        int[] end = {3, 2, 7};
        int queryTime = 4;
        System.out.println(busyStudent(start, end, queryTime));
        System.out.println("--------------------------");

        int[][] b = {{1,1}};
        System.out.println(islandPerimeter(b));
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

    /**
     * 快速排序
     *
     * @param a      排序的数组
     * @param low    起始位置
     * @param height 结束位置
     */
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

    /**
     * 二分查找
     *
     * @param array 数组
     * @param start 起始位置
     * @param end   结束为止
     * @param key   需要查找的值
     * @return -1或者查找到的值的下标
     */
    public static int twoPoints(int[] array, int start, int end, int key) {
        // 获取当前数组的中间数
        int medium = (start + end) / 2;
        if (start > end || key < array[start] || key > array[end]) {
            return -1;
        }

        if (key > array[medium]) {
            // 如果key比中间的数大  说明在中间数的右侧数组  递归右侧数组
            return twoPoints(array, medium + 1, end, key);
        } else if (key < array[medium]) {
            // 如果key比中间的数小  说明在中间数的左侧数组  递归左侧数组
            return twoPoints(array, start, medium - 1, key);
        } else if (key == array[medium]) {
            return medium;
        }
        return -1;

    }

    /**
     * leetcode 27
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        return (int) Arrays.stream(nums).filter(e -> e != val).count();
    }

    /**
     * leetcode 1295
     *
     * @param nums 数组
     * @return 偶数位个数
     */
    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            if (String.valueOf(nums[i]).length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode 1572
     *
     * @param mat 二维数组
     * @return 和
     */
    public static int diagonalSum(int[][] mat) {
        int sum = 0;
        int length = mat.length;
        for (int i = 0, j = length - 1; i < length; i++, j--) {
            sum += (mat[i][i] + mat[i][j]);
        }
        if (length % 2 == 0) {
            return sum;
        } else {
            return sum - mat[(length - 1) / 2][(length - 1) / 2];
        }
    }

    /**
     * 从链表中获取二进制数  并转为10进制
     * leetcode 1290
     *
     * @param head
     * @return
     */
    public static int getDecimalValue(ListNode head) {
        int result = 0;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            result += list.get(i) * Math.pow(2, count);
            count++;
        }
        return result;
    }

    /**
     * leetcode 1450
     *
     * @param startTime 开始时间数组
     * @param endTime   结束时间数组
     * @param queryTime 完成时间
     * @return 学生个数
     */
    public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0, j = 0; i < startTime.length && j < endTime.length; i++, j++) {
            if (startTime[i] <= queryTime && endTime[j] >= queryTime) {
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode LCP 17.速算机器人
     *
     * @param s
     * @return
     */
    public static int calculate(String s) {
        int x = 1, y = 0;
        String[] split = s.split("");
        for (String s1 : split) {
            if (s1.equals("A")) {
                x = 2 * x + y;
            } else if (s1.equals("B")) {
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    /**
     * leetcode 463.岛屿的周长
     *
     * @param grid 二维数组代表一块地方
     * @return 岛屿的周长
     */
    public static int islandPerimeter(int[][] grid) {
        int count = 0;
        int nearby = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count += 1;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        nearby += 1;
                    }
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        nearby += 1;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        nearby += 1;
                    }
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
                        nearby += 1;
                    }
                }
            }
        }

        return count * 4 - nearby;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}