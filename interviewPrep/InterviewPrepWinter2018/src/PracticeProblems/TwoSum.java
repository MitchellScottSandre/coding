/*
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
package PracticeProblems;

import java.util.Hashtable;


/**
 *
 * @author scott
 */
public class TwoSum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int data[] = {3, 3};
        int answer[] = twoSum(data, 6);
        
        System.out.println(answer[0] + " :::: " + answer[1]);
    }
    
    public static int[] twoSum(int[] nums, int target) {
        int answer[] = new int[2];
        Hashtable<Integer, int[]> table = new Hashtable<>(); // value, [index in nums, numTimesOccured]
        
        // Create hash table
        for (int i = 0; i < nums.length; i++){
            if (table.containsKey(nums[i])){
                int info[] = table.get(nums[i]);
                info[1] ++;
                table.put(nums[i], info);
            } else {
                int info[] = {i, 1}; 
                table.put(nums[i], info); 
            }
        }
        
        // Go and check if complement exists (target - val)
        for (int i = 0; i < nums.length; i++){
            if (table.containsKey(target - nums[i])){ 
                if (i != table.get(target - nums[i])[0] || table.get(target - nums[i])[1] > 1){
                    answer[0] = i;
                    answer[1] = table.get(target - nums[i])[0]; //get the index
                    break;
                } 
            }
        }
        
        return answer;
    }
    
}
