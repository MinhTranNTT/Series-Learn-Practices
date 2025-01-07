package org.crocodile.practice._88;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        // int[] nums1 = {1,2,3,0,0,0};
        int[] nums1 = {0};
        int m = 0;

        // int[] nums2 = {2,5,6};
        int[] nums2 = {1};
        int n = 1;

        mergeSolution(nums1,m,nums2,n);

        for (int num : nums1) {
            System.out.print(num + " ");
        }
        // Arrays.stream(nums1).forEach(System.out::println);
    }

    private static void mergeSolution(int[] nums1, int m, int[] nums2, int n) {
        // 3 pointer
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }
}
