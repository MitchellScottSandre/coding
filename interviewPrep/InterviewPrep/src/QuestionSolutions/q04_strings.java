/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionSolutions;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author scott
 */
public class q04_strings {

    /**
     * Write a function to find out longest palindrome in a given string?
     */
    public static Scanner input = new Scanner (System.in);
    
    public static String findLongestPalindrome(String s){
        String longestString = s.charAt(0) + "";
        int longestLength = 1;
        for (int i = 0 ; i < s.length(); i++){//index of START of palindrome
            for (int len = 2; len < s.length() - i + 1; len++){//LENGTH of palindrome
                //only add on to stack with itartion index is less than length / 2
                //for each length, make a stack
                LinkedList<Character> stack = new LinkedList<Character>();
                
                for (int j = 0; j < len; j++){//index in attempted palindrom
                    if (j < len / 2){//a b | c d, adds a b
                       stack.push(s.charAt(i + j)); //add to TOP
                    } else if (j >= Math.ceil((double) (len) / 2.0) && s.charAt(i + j) == stack.getFirst() ){// a b | b a      ;a b | c d
                       stack.pop(); //remove TOP
                    } else if (j >= Math.ceil((double) (len) / 2.0) && s.charAt(i + j) != stack.getFirst() ){
                       break;
                    }
                    if (j == len - 1 && len > longestLength){//entier string from i to i + j is a palindrome
                        longestString = s.substring(i, i + len); 
                        longestLength = len;
                        System.out.println("----" + longestString);
                    }
                }
            }
            if (s.length() - i < longestLength){//won't find one longer
                System.out.println("no point in trying anymore since index " + i);
                return longestString;
            }
        }
        return longestString;
    }
    public static void main(String[] args) {
        System.out.print("Enter string: ");
        String s = input.nextLine();
        System.out.println("returned: " + findLongestPalindrome(s));
        
    }
    
}
