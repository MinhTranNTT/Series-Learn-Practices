package org.crocodile.practice._80;

public class RemoveDuplicate2 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int len = removeDuplicate2Element(nums);
        System.out.println("New length: " + len);
        System.out.print("New array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private static int removeDuplicate2Element(int[] nums) {
        // Nếu mảng có độ dài nhỏ hơn hoặc bằng 2, không cần xử lý, trả về độ dài mảng
        if (nums.length <= 2) return nums.length;
        // Khởi tạo con trỏ chậm, bắt đầu từ phần tử thứ hai
        int slow = 2;
        // Con trỏ nhanh bắt đầu từ phần tử thứ ba
        for (int fast = 2; fast < nums.length; fast++) {
            // Nếu phần tử hiện tại lớn hơn phần tử tại vị trí slow - 2, nó có thể được giữ lại
            if (nums[fast] > nums[slow-2]) {
                // Sao chép giá trị tại con trỏ nhanh vào vị trí con trỏ chậm
                nums[slow] = nums[fast];
                // Di chuyển con trỏ chậm lên trước
                slow++;
            }
        }
        // Giá trị của slow chính là độ dài mới của mảng
        return slow;
    }
}
