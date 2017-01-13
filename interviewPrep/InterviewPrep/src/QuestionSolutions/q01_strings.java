/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionSolutions;

import java.util.Scanner;

/**
 *
 * @author scott
 */
public class q01_strings {

    /**
     * check if string is palindrome or not 
     */
    
    public static Scanner input = new Scanner (System.in);
    
    public static boolean isPalindrome(String s){
        String copy = s;
        StringBuilder sb = new StringBuilder();
        sb.append(copy);
        sb.reverse();
        
        return sb.toString().equalsIgnoreCase(s);
        
    }
    
    public static void main(String[] args) {
        String s;
        s = input.nextLine();
        System.out.println(isPalindrome (s));
    }
    
}
