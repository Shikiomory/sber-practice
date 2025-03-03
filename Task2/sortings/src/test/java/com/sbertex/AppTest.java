package com.sbertex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * Unit test for simple App.
 */

public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed");
    }

    @Tag("DEV")
    @Test
    void testBubbleSort() {
        int[] arr = {5, 3, 8, 1, 2};
        int[] expected = {1, 2, 3, 5, 8};
        BubbleSort bsort = new BubbleSort();
        bsort.BubbleSort(arr, arr.length);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSort() {
        int[] arr = {9, 2, 6, 1, 5};
        int[] expected = {1, 2, 5, 6, 9};
        QuickSort qsort = new QuickSort();
        qsort.QuickSort(arr, arr.length);
        assertArrayEquals(expected, arr);
    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}