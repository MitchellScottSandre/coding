/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

/**
 *
 * @author scott
 */
public class StringToInteger {
    
    public static boolean isDigit(char c){
        return c >= 48 && c <= 57;
    }

    public static int myAtoi(String str) {
        // Check if empty
        if (str.isEmpty()) return 0;
        
        // Find index of first non-white space
        int firstCharIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                firstCharIndex = i;
                break;
            }
        }
        
        // Check if only white spaces
        if (firstCharIndex == -1) return 0;
        
        // Get non-white-space string
        str = str.substring(firstCharIndex);
        
        boolean positive = true;
        String number = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if (i == 0 && c == '-') {
                positive = false;
            } else if (c >= 48 && c <= 57) {
                number += c;
            } else if (!(i == 0 && c == '+')){
                break;
            }
        }
        
        // Not a valid entry
        if (number.length() == 0) {
            return 0;
        }
        
        // Output valid entry
        double numberValue = Double.parseDouble(number);
        System.out.println(numberValue);
        System.out.println(number);
        if (positive && numberValue > Integer.MAX_VALUE) {
            return 2147483647;
        } else if (!positive && -1 * numberValue < Integer.MIN_VALUE) {
            return -2147483648;
        } else {
            return (int) (numberValue * (positive ? 1 : -1));
        }
    }
    
    public static void main(String[] args) {
        System.out.println(myAtoi("-2147483648"));
    }
    
}
