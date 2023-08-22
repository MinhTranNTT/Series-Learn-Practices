package com.problems.originstring.leet._14;

public class _14_Longest_Common_Prefix_Tree {
    public static void main(String[] args) {
        //String[] arr = new String[] { "flower","flow","flight", "flo","flowee","flighttiant" };
        String[] arr = new String[] { "Minh","Mi","Miu", "Miuu","Mia","Mioe" };
        System.out.println(longestCommonPrefix(arr));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        } else {
            int mid = (left + right) / 2;
            String leftPrefix = longestCommonPrefix(strs, left, mid);
            String rightPrefix = longestCommonPrefix(strs, mid + 1, right);
            return commonPrefix(leftPrefix, rightPrefix);
        }
    }

    private static String commonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minLength);
    }
}
