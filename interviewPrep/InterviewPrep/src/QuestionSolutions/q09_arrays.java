/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionSolutions;

/**
 *
 * @author scott
 */
public class q09_arrays {

    /**
     * In an array that's supposed to include numbers 1 - 100, one number is missing. How do you find it?

     */
    public static int[] makeArrayNums1toY(int y){//makes RANDOM array
        int array[] = new int[y];//all 0
        
        for (int i = 0; i < y; i++){
            array[i] = i + 1;//create SORTED array
        }
        
        //shuffle
        for (int i = 0; i < y * 5; i++){
            int index1 = (int) (Math.random() * y);
            int index2 = (int) (Math.random() * y);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
            
        }
        
        
        return array;
    }
    
    public static void displayArray(int array[]){
        for (int i = 0 ; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
    
    public static int findMissingNumber(int array[]){
        int values[] = new int[array.length];//all set to 0
        for (int i = 0; i < array.length; i++){
            values[array[i] - 1]++;
        }
        for (int i = 0 ; i < values.length; i++){
            if (values[i] == 0){
                return i + 1;
            }
        }
        return -1;
    }
    
    
    public static void main(String[] args) {
        int len = 10;
        int data[] = makeArrayNums1toY(len);
        
        //============ make 1 of them missing =======
        int index1 = (int) (Math.random() * len);
        int index2;
        while (true){
            index2 = (int) (Math.random() * len);
            if (index2 != index1){
                break;
            }
        }
        data[index1] = data[index2];
        
        //============ end of make 1 of them missing =======
        
        System.out.println("Array is:");
        displayArray(data);
        
        System.out.println("Missing value is " + findMissingNumber(data));
        
        
       
    }
    
}
