
package DynamicProgramming;

/**
 * Mitchell Scott Sandre
 * 
 * Given a set of non-negative integers, and a value sum, determine if there is a 
 * subset of the given set with sum equal to given sum.
 * 
 * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 */
public class SubsetSum {
    
    public static boolean sumInSet(int[] set, int target, int setLength) {
        if (target == 0) return true; //we hae found an answer!
        if (setLength <= 0) return false;
        
        // Case 1: the last value is used 
        int lastVal = set[setLength - 1];
        
        // Case 2: the last value is not used.
        sumInSet(set, target - lastVal, setLength - 1);
        
        
        return sumInSet(set, target - lastVal, setLength - 1) || sumInSet(set, target, setLength - 1);
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        System.out.println(sumInSet(set, 14, set.length));
    }

}
