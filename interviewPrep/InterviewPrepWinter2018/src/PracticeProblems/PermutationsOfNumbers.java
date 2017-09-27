/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]////MY ANSWER IS WRONG
 */
public class PermutationsOfNumbers {
    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            List<Integer> curr = new ArrayList<>();
            curr.add(nums[i]);
            solve(curr, nums, 0);
        }
        return null;
    }
    
    public void solve(List<Integer> curr, int[] nums, int index){
        if (curr.size() == nums.length){
            answer.add(curr);
            return;
        }
        for(int i = index + 1; i < nums.length; i++){
            List<Integer> list = new ArrayList<>();
            list.addAll(curr);
            list.add(nums[i]);
            solve(list, nums, index + 1);
        }
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
