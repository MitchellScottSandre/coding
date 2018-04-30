/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
package PracticeProblems;

public class TwoSum {
    
    public static int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            // Check if nums contains target - a
            int b = target - a;
            
            for (int k = 0; k < nums.length; k++) {
                if (k == i) continue;
                int c = nums[k];
                if (c == b) {
                    answer[0] = i;
                    answer[1] = k;
                    return answer;
                }
                
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] data = { 2, 7, 11, 15 };
        int[] gg = twoSum(data, 9);
        
        System.out.println(gg[0]);
        System.out.println(gg[1]);
    }
    
}
