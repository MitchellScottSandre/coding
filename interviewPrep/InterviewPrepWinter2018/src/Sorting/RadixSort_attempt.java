
package Sorting;

import java.util.PriorityQueue;

public class RadixSort_attempt {

    
    public static void radixSort(int data[]){
        PriorityQueue<Integer> buckets[] = new PriorityQueue[10];
        for (int i = 0; i < 10; i++){
            buckets[i] = new PriorityQueue();
        }
        
        int currentCheckDigit = 1;
        int digitsToCheck = 1; //will change this later
        boolean firstTime = true;
        
        while (currentCheckDigit <= digitsToCheck){
            // Placing numbers into buckets
            for (int x : data){
                String stringVal = Integer.toString(x);
                int bucketNum = currentCheckDigit > stringVal.length() ? 0 : Integer.parseInt(stringVal.charAt(currentCheckDigit - 1) + "");
                buckets[bucketNum].add(x);
                
                if (firstTime && (int) (Math.log10(x)) + 1 > digitsToCheck){
                    digitsToCheck = (int) Math.log10(x) + 1;
                }
            }
        
            firstTime = false;
            
            // Place back into new data array from 0 to 9 from queue (first in, first out);
            int dataIndex = 0;
            for (int i = 0; i < 10; i++){
                while (buckets[i].isEmpty() == false){
                    data[dataIndex] = buckets[i].poll();
                    dataIndex++;
                }
            }
            
            System.out.println(currentCheckDigit);
            currentCheckDigit++;  
        }
    }
    
    public static void main(String[] args) {
        int data[] = {7, 55, 22, 100, 77};
        radixSort(data);
        for (int x : data){
            System.out.print(x + ", ");
        }
    }
    
}
