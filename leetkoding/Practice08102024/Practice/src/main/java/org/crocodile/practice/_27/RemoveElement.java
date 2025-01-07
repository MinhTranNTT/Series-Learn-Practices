package org.crocodile.practice._27;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int len = removeElement(nums, val);

        System.out.println("Length new Array: " + len);
        System.out.print("New Array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        // Con trỏ chậm `i` trỏ đến vị trí hiện tại cần giữ lại
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // Nếu nums[j] không bằng val, giữ lại phần tử
            if (nums[j] != val) {
                // Sao chép nums[j] vào nums[i]
                nums[i] = nums[j];
                // Tăng con trỏ chậm `i`
                i++;
            }
        }
        // Trả về số lượng phần tử cần giữ lại
        return i;
    }
}
