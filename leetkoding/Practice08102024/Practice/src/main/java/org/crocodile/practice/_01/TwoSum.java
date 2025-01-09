package org.crocodile.practice._01;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    private static int[] twoSum(int[] num, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < num.length ; i++) {
            int ele = target - num[i];
            if (numMap.containsKey(ele)) {
                return new int[] {numMap.get(ele), i};
            }

            numMap.put(num[i], i);
        }
        throw new IllegalArgumentException("Not exist array valid");
    }
    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("Kết quả 1: [" + result1[0] + ", " + result1[1] + "]");

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("Kết quả 2: [" + result2[0] + ", " + result2[1] + "]");

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = twoSum(nums3, target3);
        System.out.println("Kết quả 3: [" + result3[0] + ", " + result3[1] + "]");
    }
}
