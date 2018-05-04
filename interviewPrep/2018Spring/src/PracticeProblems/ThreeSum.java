package PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero. Note: The solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> answer = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // Generate a map of elementValue -> index(s)
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (map.containsKey(val)) {
                List<Integer> curr = map.get(val);
                curr.add(i);
                map.put(val, curr);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                map.put(val, newList);
            }
        }

        // Iterate through nums, see if triplets exist
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            for (int k = i + 1; k < nums.length; k++) {
                int b = nums[k];
                int desiredVal = -(a + b);
                
                if (map.containsKey(desiredVal)) {
                    List<Integer> curr = map.get(desiredVal);
                    List<Integer> solution = Arrays.asList(a, b, desiredVal);
                    Collections.sort(solution);
                    if (set.contains(solution)) continue;
                    for (int j = 0; j < curr.size(); j++) {
                        int index = curr.get(j);
                        if (index != i && index != k) {
                            if (!set.contains(solution)) {
                                set.add(solution);
                                answer.add(solution);
                            }
                            break;
                        }
                    }
                }
            }
        }
//        System.out.println(map.toString());

        return answer;
    }

    public static void main(String[] args) {
        int[] data = {-1, 0, 1, 2, -1, -4};
        threeSum(data);
    }

}
