package org.crocodile.practice.sort;

import java.util.HashMap;
import java.util.Map;

public class SelectionSort {
    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr,i,minIdx);
        }
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        SelectionSort ss = new SelectionSort();
        int num[] = {5,3,8,6,1};
        ss.selectionSort(num);
        ss.printArray(num);
    }


}
