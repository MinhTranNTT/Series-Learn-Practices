package com.problems.originstring.leet._14;

import java.util.Arrays;

public class _14_Longest_Common_Prefix {
    public static void main(String[] args) {
//        String[] arr = new String[] { "flower","flow","flight" };
        String[] arr = new String[] { "Minh","Aki","Min" };
        System.out.println(longestCommonPrefix(arr));
    }

    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            } else {
                break;
            }
        }
        return s1.substring(0, idx);
    }
}
