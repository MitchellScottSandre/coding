/*
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 */
package PracticeProblems;

import java.util.List;


public class MaxDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        Integer min = null;
        Integer max = null;
        int maxD = 0;
        
        for(List<Integer> list : arrays){
            int small = list.get(0);
            int big = list.get(list.size() - 1);
            if (min == null) min = small;
            if (max == null) max = big;
            int a = (int) (Math.abs(big - min));
            int b = (int) (Math.abs(max - small));
            if (a > maxD){
                maxD = a;
            }
            if (b  >maxD){
                maxD = b;
            }
            if (small < min){
                min = small;
            }
            if (big > max){
                max = big;
            }
        }
        return maxD;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}