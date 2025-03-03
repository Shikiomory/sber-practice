package com.sbertex;

public class QuickSort {
    private void quickSortCore(int[] arr, int low, int high) {
        int i, j;
        int pivot;
        while (low < high) {
            i = low;
            j = high;
            pivot = arr[(low + high) / 2];
            while (i <= j) {
                while (arr[i] < pivot) ++i;
                while (arr[j] > pivot) --j;
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    ++i;
                    --j;
                }
            }
            if (i - low < high - j) {
                quickSortCore(arr, low, j);
                low = i;
            }
            else{
                quickSortCore(arr, i, high);
                high = j;
            }
        }
    }

    public void QuickSort(int[] arr, int len) {
        int low = 0;
        int high = len - 1;
        quickSortCore(arr, low, high);
    }
}