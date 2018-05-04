/*
Given a time represented in the format "HH:MM", form the next closest 
time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34",
"12:09" are all valid. "1:34", "12:9" are all invalid.
 */
package GooglePracticeProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextClosestTime {
    
    public static int getNextHighestVal(List<Integer> sortedDigits, List<Integer> currDigits, int currIndex) {
        System.out.println(currDigits.toString() + " .... " + currIndex);
        // No number higher than it
        int currNumber = currDigits.get(currIndex);
        int currIndexNumberRank = sortedDigits.indexOf(currNumber); // where it is inside of sortedDigits
        if (currIndexNumberRank == sortedDigits.size() - 1) return -1; 
        
        int nextNum = sortedDigits.get(currIndexNumberRank + 1);
        switch (currIndex) {
            // Ones column
            case 3: return nextNum;
            
            // Tens column
            case 2: return nextNum < 6 ? nextNum : -1;
            
            // Hours column
            case 1:
                if (currDigits.get(0) != 2) return nextNum;
                if (nextNum <= 4) return nextNum;
                return -1;
                
            // Tens of Hours column
            case 0: return nextNum <= 2 ? nextNum : -1;
        }
        
        return -1;
    }
    
    public static String nextClosestTime(String time) {
        // Create digits array
        List<Integer> sortedDigits = new ArrayList<>();
        Set<Integer> origDigits = new HashSet<>();
        List<Integer> currDigits = new ArrayList<>();
        
        int count = 0;
        int i = 0;
        
        while (count < 4) {
            char c = time.charAt(i);
            if ((int) c >= 48 && (int) c <= 57) {
                currDigits.add((int)(c) - 48);
                
                if (!origDigits.contains((int)(c) - 48)) {
                    sortedDigits.add((int)(c) - 48);
                    origDigits.add((int)(c) - 48);
                }
                count++;
            }
            i++;
        }
        
//        System.out.println(origDigits.toString());
        Collections.sort(sortedDigits);
        int smallestVal = sortedDigits.get(0);
        
        int currIndex = 3;
        
        while(true) {
            int nextHighestVal = getNextHighestVal(sortedDigits, currDigits, currIndex);
            System.out.println("nextHighestVal " + nextHighestVal);
            if (nextHighestVal == -1 && currIndex == 0) {
                // No possible answer found for today, return tomorrow's smallest answer
                return smallestVal + "" + smallestVal + ":" + smallestVal + "" + smallestVal;  
            } else if (nextHighestVal == -1) {
                // couldn't find next highest val, try lower index and set all 
                // values to the right to smallest val
                currIndex--;
                for (int index = currIndex + 1; index < 4; index ++) {
                    currDigits.set(index, smallestVal);
                }
            } else {
                currDigits.set(currIndex, nextHighestVal);
//                System.out.println("ANSWER" + currDigits.toString());
                return currDigits.get(0) + "" + currDigits.get(1) + ":" + currDigits.get(2) + "" + currDigits.get(3);
            }
        }
       
    }

    public static void main(String[] args) {
        System.out.println(nextClosestTime("20:56"));
    }
    
}
