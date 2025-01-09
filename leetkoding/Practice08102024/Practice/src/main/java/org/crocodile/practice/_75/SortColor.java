package org.crocodile.practice._75;

import java.util.Arrays;

public class SortColor {
    private static int[] sortColorElement(int[]num) {
        int slow = 0;
        int mid = 0;
        int high = num.length-1;
        while (mid <= high) {
            if (num[mid] == 0) {
                swapElement(num,slow,mid);
                mid++;
            } else if (num[mid] == 1) {
                mid++;
            } else {
                swapElement(num,mid,high);
                high--;
            }
        }
        return num;
    }

    private static void swapElement(int[] num,int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    public static void main(String[] args) {
        // int[] nums = {2,0,1};
        int[] nums = {2,0,2,1,1,0};
        System.out.println("Arr: " + Arrays.toString(sortColorElement(nums)));
    }
}
