
package Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSort {

    // Select a pivot. Make all elements to left of pivot < pivot, all to the right > pivot
    
    public static int partition(int data[], int left, int right){
        int i = left;
        int j = right;
        int pivotVal = data[(left + right) / 2]; // get pivot in middle of list
        System.out.println("\npivotVal: " + pivotVal + ", left is: " + left + ", right is: " + right);
        
        while (i <= j){ // divide into 2 lists
            // If value in left list is smaller than the pivotVal, then
            // get the next element from the left list (keep going)
            while (data[i] < pivotVal){
                i++;
            }
            
            // If value in right list is larger than pivotVal, then
            // get the next element from the right list (keep going)
            while (data[j] > pivotVal){
                j--;
            }
            
            // if we have found a value in left list that is larger than pivotVal
            // and found a value in right list that is smaller than pivotVAl,
            // then swap them. and increase i and j.
            if (i <= j){
                System.out.print("Before swapping: ");
                print(data);
                swap(data, i , j);
                System.out.print("After swapping: ");
                print(data);
                i++;
                j--;
            }
        }
        return i; 
    }
    
    public static void swap(int data[], int a, int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
    
    public static void quicksort(int data[], int left, int right){
        int index = partition(data, left, right);
        System.out.println("index is: " + index);
        if (left < index - 1){
            System.out.println("sorting left");
            quicksort(data, left, index - 1);
        } 
        
        if (index < right){
            System.out.println("sorting right");
            quicksort(data, index, right);
        }
    }
    
    public static void print(int data[]){
        for (int x : data){
            System.out.print(x + ", ");
        }
        System.out.print("\n");
    }
    
    public static void main(String[] args) {
        int data[] = {7,1,5,9,6,10,2};
        
        quicksort(data, 0 , data.length - 1);
        
        System.out.println("Final: ");
        print(data);
    }
    
}
