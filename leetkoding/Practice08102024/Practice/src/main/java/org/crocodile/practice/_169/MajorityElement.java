package org.crocodile.practice._169;

public class MajorityElement {
    public static void main(String[] args) {
        // int[] nums = {3,2,3};
        int[] nums = {2,2,1,1,1,2,2};
        int len = majorityElement(nums);
        System.out.println("Value element: " + len);
        // System.out.print("New array: ");
        // for (int i = 0; i < len; i++) {
        //     System.out.print(nums[i] + " ");
        // }
    }

    private static int majorityElement(int[] nums) {
        // Boyer-Moore
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }

            if(nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
