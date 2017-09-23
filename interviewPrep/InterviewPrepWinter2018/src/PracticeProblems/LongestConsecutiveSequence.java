/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 */
package PracticeProblems;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LongestConsecutiveSequence {
    
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 1;
        for (int n : nums) {set.add(n);}
        for(Iterator<Integer> it = set.iterator(); it.hasNext(); it.next()){
            Set<Integer> removeThese = new HashSet<>();
            int initialVal = it.next();
            int val = initialVal;
            int counter = 1;
            // search up
            while(true){
                removeThese.add(val);
                if (set.contains(val + 1)){
                    counter++;
                    val = val + 1;
                } else {
                    if (counter > max){
                        max = counter;
                    }
                    break;
                }
            }
            val = initialVal;
            //search down
            while(true){
                removeThese.add(val);
                if (set.contains(val - 1)){
                    counter++;
                    val = val - 1;
                } else {
                    if (counter > max){
                        max = counter;
                    }
                    break;
                }
            }
            
//            set.removeAll(removeThese);
        }
        
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sol = new LongestConsecutiveSequence();
        int[] data = {100, 4, 200, 1, 3, 2, 5, 7, 8, 9, 10,11,12, 6};
        System.out.println(sol.longestConsecutive(data));
    }
    
}
