
package PracticeProblems;

import java.util.Arrays;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Find largest index i such that array[i − 1] < array[i].
(If no such i exists, then this is already the last permutation.)

Find largest index j such that j ≥ i and array[j] > array[i − 1].

Swap array[j] and array[i − 1].

Reverse the suffix starting at array[i].
*/

public class NextGreaterPermutation {

    public static void nextPermutation(int[] nums) {
        int indexOfLongest = 0; // index of longest non-increasing block of numbers
        int lastPossibleIndexOfLongest = 0;
        int currLength = 0;
        int longestLength = 0;
        boolean countingForThisBlock = false;
        
        // find indexOfLongest block
        for (int i = 0; i < nums.length - 1; i++){
            if (countingForThisBlock == false && nums[i] >= nums[i + 1]){
                countingForThisBlock = true;
                lastPossibleIndexOfLongest = i;
                currLength = 2;
            } else if (countingForThisBlock == true && nums[i] >= nums[i + 1]){
                currLength++;
                if (currLength > longestLength){
                    longestLength = currLength;
                    indexOfLongest = lastPossibleIndexOfLongest;
                }
            } else if (countingForThisBlock == true && nums[i] < nums[i + 1]){
                countingForThisBlock = false;
            }
        }
        
        
        if (indexOfLongest == -1 && lastPossibleIndexOfLongest != -1){
            indexOfLongest = lastPossibleIndexOfLongest;
        } else if (indexOfLongest == -1){
            indexOfLongest = nums.length - 1;
            longestLength = 1;
        }
        
        if (indexOfLongest == 0){
            Arrays.sort(nums);
            return;
        }
        
        // find smallest val in block
        int nextSmallestValInBlock = Integer.MAX_VALUE;
        int indexNextSmallestVal = -1;
        for (int i = indexOfLongest; i < indexOfLongest + longestLength; i++){
            if (nums[i] < nextSmallestValInBlock && nums[i] > nums[indexOfLongest - 1]){
                nextSmallestValInBlock = nums[i];
                indexNextSmallestVal = i;
            }
        }
        
        System.out.println("pivot val: " + nums[indexOfLongest - 1]);
        
        // swap smallest value in block that is larger than the pivot val (pivot val is to left of start of longest block)
        nums[indexNextSmallestVal] = nums[indexOfLongest - 1];
        nums[indexOfLongest - 1] = nextSmallestValInBlock;
        
        // sort the block 
        Arrays.sort(nums, indexOfLongest, indexOfLongest + longestLength);
    }
    
    public static void main(String[] args) {
        int array[] = {1,2,2};
        nextPermutation(array);
        
        for (int x : array){
            System.out.println(x);
        }
    }
    
}
