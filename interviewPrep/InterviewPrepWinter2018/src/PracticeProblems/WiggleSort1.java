
package PracticeProblems;

import java.util.Arrays;

/**
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort1 {
    
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        
        int left = 1;
        int right = nums.length - 2;
        for (int i = 1; i < (nums.length - 2) / 2; i++){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
    }


    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
