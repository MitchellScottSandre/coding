
package PracticeProblems;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        if (num.indexOf('2') > -1 || num.indexOf('4') > -1 || num.indexOf('7') > -1) return false;
        Map<Character, Character> map = new HashMap<>();
        map.put('1', '1');
        map.put('2', '2');
        map.put('5', '5');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        map.put('0', '0');
        
        String newString = "";
        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            newString += map.get(c);
        }
        
        String reversed = "";
        for (int i = newString.length() - 1; i >= 0; i--){
            reversed += newString.charAt(i);
        }
        
        return reversed.equals(num);
       
    }
    public static void main(String[] args) {
        StrobogrammaticNumber sol = new StrobogrammaticNumber();
        System.out.println(sol.isStrobogrammatic("88"));
    }
    
}
