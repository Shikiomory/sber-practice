package com.sbertex;

public class App {

    public static void main(String[] args) {
        BubbleSort bsort = new BubbleSort();
        QuickSort qsort = new QuickSort();
        ArrCom a = new ArrCom();

        System.out.println("It's sorting time");
        int len = 10;
        int[] arr = new int[len];

        a.fillArray(arr, len);
        a.printArray(arr, len);
        qsort.QuickSort(arr, len);
        a.printArray(arr, len);

        a.fillArray(arr, len);
        a.printArray(arr, len);
        bsort.BubbleSort(arr, len);
        a.printArray(arr, len);
    }
}