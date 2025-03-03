package com.sbertex;

public class BubbleSort {
    public void BubbleSort(int[] arr, int len) {
        for (int j = 0; j < len - 1; ++j) {
            for (int i = 0; i < len - 1 - j; ++i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
}