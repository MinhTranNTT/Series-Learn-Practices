package org.crocodile.practice._26;

public class RemoveDuplicateElement {
    public static void main(String[] args) {
        // int[] nums = {1,1,2};
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int len = removeDuplicateElement(nums);
        System.out.println("New length: " + len);
        System.out.print("New array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private static int removeDuplicateElement(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
