/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class RadixSort {

    public static void radixsort(int[] input) {
        final int RADIX = 10;
        // declare and initialize bucket[]
        List<Integer>[] buckets = new ArrayList[RADIX];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, checkDigit = 1; // 1s, 10s, 100s, ...
        while (!maxLength) {
            maxLength = true;
            // split input between lists
            for (Integer i : input) {
                tmp = i / checkDigit;
                buckets[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : buckets[b]) {
                    input[a++] = i;
                }
                buckets[b].clear();
            }
            // move to next digit
            checkDigit *= RADIX;
        }
    }

    public static void main(String[] args) {
        int data[] = {7, 55, 22, 100, 77};
        radixsort(data);
        for (int x : data){
            System.out.print(x + ", ");
        }
    }

}
