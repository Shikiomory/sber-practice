package com.sbertex;

public class ArrCom {

    public void printArray(int[] arr, int len) {
        for (int i = 0; i < len; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void fillArray(int[] arr, int len) {
        for(int i = 0; i < len; ++i) {
            arr[i] = (int)(Math.random() * 100) + 1;
        }
    }
}