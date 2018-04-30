package PracticeProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scott
 */
public class TwoSum2 {

    public static int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        Map<Integer, List<Integer>> dataMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int numA = nums[i];
            int otherNum = target - numA;
            List<Integer> otherList = dataMap.get(otherNum);
            if (otherList != null) {
                // Iterate through other list and see if there is an index
                // that does not equal i
                for (int k = 0; k < otherList.size(); k++) {
                    int index = otherList.get(k);
                    if (index != i) {
                        answer[0] = index;
                        answer[1] = i;
                        return answer;
                    }
                }
            } else {
                List<Integer> newList = Arrays.asList(i);
                dataMap.put(numA, newList);
            }
            
        }
        return null;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
