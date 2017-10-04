
package PracticeProblems;

import java.util.HashMap;

public class TwoSum2 {

    /**
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
     */
    
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++){
            int curr = nums[i];
            int complement = target - curr;
            if (map.containsKey(complement)){
                answer[0] = i;
                answer[1] = map.get(complement);
                return answer;
            } else {
                map.put(curr, i);
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
