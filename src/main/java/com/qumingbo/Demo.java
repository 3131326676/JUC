package com.qumingbo;

import java.util.*;

/**
 * @author: 曲铭博
 * @Date: Created in 20:45 2020/10/23
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        String s = "dddr";
        System.out.println(ifReverse(s));

        String s1 = "this is lucy";
        String s2 = "ben this is";
        String[] strings = noRepect(s1, s2);
        int[] a = new int[2];
        Arrays.stream(strings).forEach(System.out::println);
    }

    public static String[] noRepect(String s1, String s2) {
        String[] s3 = s1.split(" ");
        String[] s4 = s2.split(" ");

        List<String> strings1 = Arrays.asList(s3);
        List<String> strings2 = Arrays.asList(s4);

        Collection collection = new ArrayList<String>(Arrays.asList(s3));
        Collection collection1 = new ArrayList<String>(Arrays.asList(s4));

        collection.retainAll(collection1);

        HashSet<String> set = new HashSet<>();
        set.addAll(strings1);
        set.addAll(strings2);

        Collection cross = new ArrayList(collection);
        Collection and = new ArrayList(set);

        and.removeAll(cross);
        return (String[]) and.stream().toArray(String[]::new);
    }

    public static Boolean ifReverse(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (!Character.isLetterOrDigit(s.charAt(i))) {
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
}
