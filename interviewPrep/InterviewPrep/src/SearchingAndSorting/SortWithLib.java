/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchingAndSorting;

import java.util.Arrays;

/**
 *
 * @author scott
 */
public class SortWithLib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int length = 50;
        int data[] = new int[length];
        //make random array
        for (int i = 0; i < length; i++){
            data[i] = (int) (Math.random() * 100) - 50;
        }
        
        //Arrays.sort(a, fromIndex, toIndex); 
        Arrays.sort(data);//sorts it low to high
        
        //display
        for (int i = 0 ; i < length; i++){
            System.out.println(data[i]);
        }
        

        
    }
    
}
