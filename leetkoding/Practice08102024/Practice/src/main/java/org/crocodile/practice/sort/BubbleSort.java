package org.crocodile.practice.sort;

public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BubbleSort bs = new BubbleSort();
        // int[] arr = {64,34,25,12,22,11,90};
        int[] arr = {5,3,8,6,1};
        bs.bubbleSort(arr);
        bs.printArray(arr);
    }


}
