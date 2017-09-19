/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumsSumToZero {
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        if (nums.length < 3) return answer;
        
        Arrays.sort(nums);
        int i =0;
        while(i < nums.length - 2){
            if (nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0){
                    // add the numbers to our answer
                    answer.add(Arrays.asList(nums[i],nums[j],nums[k]));
                }
                if (sum <= 0){
                    // skip over the rest of the middle numbers that are the same
                    while (nums[j] == nums[++j] && j < k){}
                }
                if (sum >= 0){
                    // skip over the rest of the right-hand numbers that are the same
                    while (nums[k--] == nums[k] && j < k){}
                }
                // skip over the rest of the left-hand numbers that are the same
                while (nums[i] == nums[++i] && i < nums.length - 2){}
            }
        }
        
        return answer;
    }

    public static void main(String[] args) {
        
    }
    
}
