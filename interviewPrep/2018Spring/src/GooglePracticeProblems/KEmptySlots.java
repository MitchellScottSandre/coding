/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GooglePracticeProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scott
 */
public class KEmptySlots {
    public static int kEmptySlots(int[] flowers, int k) {
        // Looking for 2 numbers that are (k + 1) apart and numbers between them have not been seen
        
        // Create possible pairs
        Map<Integer, Integer> possiblePairs = new HashMap<>();
        for (int i = 0; i < flowers.length; i++) {
            int val = flowers[i];
            if (val + 1 + k <= flowers.length) {
                possiblePairs.put(val, val + 1 + k);
                possiblePairs.put(val + 1 + k, val);
            }
        }
        
        // Iterate and find solution
        for (int i = 0; i < flowers.length; i++) {
            int val = flowers[i];
//            System.out.println("val is " + val + " day is " + i + ", pairs are: " + possiblePairs.entrySet().toString());
            
            // Check for solution
            if (possiblePairs.size() == 2 && possiblePairs.get(val) != null) {
                return i + 1;
            }
            // Remove pairs from possible pairs
            List<int[]> removeThese = new ArrayList<>();
            possiblePairs.entrySet().forEach(entry -> {
                int key = entry.getKey();
                int value = entry.getValue();
                
                if ((val > key && val < value) || (val < key && val > value)) {
                    int[] pair = { key, value };
                    removeThese.add(pair);
                }
            });
            
            removeThese.forEach(x -> possiblePairs.remove(x[0], x[1]));

        }
//        System.out.println(possiblePairs);
        return -1;
    }

    public static int kEmptySlots1(int[] flowers, int k) {
        int[] garden = new int[flowers.length];
        
        for (int day = 0; day < flowers.length; day++) {
            // Generate the garden
            int blossomIndex = flowers[day] - 1;
            garden[blossomIndex] = 1;
            
            for (int i = 0; i < garden.length; i++) {
                if (garden[i] == 1 && i + k + 1 < garden.length && garden[i + k + 1] == 1) {
                    boolean validAnswer = true;
                    for (int j = i + 1; j < i + k + 1; j++) {
                        if (garden[j] == 1) {
                            validAnswer = false;
                            break;
                        }
                    }
                    if (validAnswer) {
                        return day + 1;
                    }
                }
            }
        }
        
        return -1;
    }
    public static void main(String[] args) {
        int[] data = { 1, 2, 3 };
        int[] data2 = { 1,3,4,2,7,5,6 };
        System.out.println(kEmptySlots(data, 1));
        System.out.println(kEmptySlots(data2, 2));
    }
    
}
