/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

/**
 *
 * @author scott
 */
public class MergeSort {
    
    private int[] array;
    private int[] temp;
    
    public void sort(int[] values){
        this.array = values;
        this.temp = new int[values.length];
        mergesort(0, values.length - 1);
        
        for (int x : array){
            System.out.println(x);
        }
    }

    public void mergesort(int low, int high){
        if (low < high){
            int middle = (low + high) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }
    
    public void merge(int low, int middle, int high){
        temp = new int[array.length];
        for (int i = low; i <= high; i++){
            temp[i] = array[i];
        }
        int i = low, j = middle + 1, k = low;
        
        while (i < middle && j < high){
            if (temp[i] <= temp[j]){
                array[k] = temp[i];
                i++;
            } else {
                array[k] = temp[j];
                j++;
            }
            k++;
        }
        
        while(i <= middle){
            array[k] = temp[i];
            k++;
            i++;
        }
    }
    public static void main(String[] args) {
        int[] data = {4, 3, 2, 1, 66, 5, 8, -4, 99, -2};
        MergeSort sorter = new MergeSort();
        sorter.sort(data);
    }
    
}
