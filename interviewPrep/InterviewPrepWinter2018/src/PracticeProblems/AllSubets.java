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
 *
 * @author scott
 */
public class AllSubets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        System.out.println("\nBacktrack called: start = " + start);
        System.out.print("    Templist: ");
        tempList.stream().forEach(x -> System.out.print(x + ","));
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);

            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1); // remove what you just added on, to go back to templist that was passed to this function
                                                  // only add one at a time
        }
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3};
        subsets(nums);
    }

}
