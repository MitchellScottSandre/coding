/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchingAndSorting;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author scott
 */
public class SearchWithLib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int length = 50;
        int data[] = new int[length];
        //make random array
        for (int i = 0; i < length; i++){
            data[i] = (int) (Math.random() * 50) - 25;
        }
        
        Arrays.sort(data);//sorts it low to high
        System.out.println("Searching for value 5. occurs at index: " + Arrays.binarySearch(data, 5));//will return a negative value if it cannot find it 
        
        //display
        for (int i = 0 ; i < length; i++){
            System.out.println(data[i]);
        }
    }
    
}
