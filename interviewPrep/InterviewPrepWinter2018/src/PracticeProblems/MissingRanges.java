
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        boolean coveredLower = false;
        List<String> answer = new ArrayList<>();
        
        if (nums.length == 0){
            if (lower == upper){
                answer.add(Integer.toString(lower));
            } else {
                answer.add(Integer.toString(lower) + "->" + Integer.toString(upper));
            }
            return answer;
        }
        for (int i = 0; i < nums.length; i++){
            int curr = nums[i] ;
            if (curr > upper)break;
            int low = -1;
            int high = -1;
            if (curr >= lower && curr < upper){
                int next = i == nums.length - 1? upper : nums[i + 1];
                if (coveredLower == false){
                    coveredLower = true;
                    if (curr == lower && next != curr + 1){
                        answer.add(Integer.toString(curr) + "->" + Integer.toString(next - 1));
                    } else if (curr != lower){
                        if (curr == lower + 1){
                            answer.add(Integer.toString(lower));
                        } else {
                            answer.add(Integer.toString(lower) + "->" + Integer.toString(curr - 1));
                        }
                    }
                } 
                else if (next != curr + 1){
                    low = curr + 1;
                    high = next == upper && i == nums.length - 1? upper: Integer.min(upper, next - 1);
                    if (low == high){
                        answer.add(Integer.toString(low));
                    } else {
                        answer.add(Integer.toString(low) + "->" + Integer.toString(high));
                    }
                }
               
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        MissingRanges sol = new MissingRanges();
        int nums[] = {-1};
        List<String> ans = sol.findMissingRanges(nums, -2, 1);
        for (int i = 0; i < ans.size(); i++){
            String s = ans.get(i);
            System.out.println(s);
        }
    }
    
}
