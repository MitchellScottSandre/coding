
package PracticeProblems;

import java.util.HashMap;

/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> numCounter = new HashMap<>();
        int maxCount = 0;
        int maxNum = nums[0];
        for (int i = 0; i < nums.length; i++){
            if (numCounter.containsKey(nums[i])){
                int x = numCounter.get(nums[i]);
                numCounter.put(nums[i], x + 1);
            } else {
                numCounter.put(nums[i], 1);
            }
            
            if (numCounter.get(nums[i]) > maxCount){
                maxCount = numCounter.get(nums[i]);
                maxNum = nums[i];
            }
        }
        return maxNum;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
