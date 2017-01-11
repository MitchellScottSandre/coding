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
public class chapter1_q4 {

    /**
     * Write a method to decide if two strings are anagrams or not
     */
    public static Scanner input = new Scanner (System.in);
    
    public static boolean solve(String one, String two){
        if (one.length() != two.length()){
            return false;
        }
        for (int i = 0; i < one.length(); i++){
            if (one.charAt(i) != two.charAt(one.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        String one, two;
        System.out.println("Question: Are these anagrams?");
        //what, what the hell is an anagram??? same forwards and backwards? on contain in total the same number of
        //the same chars?? aha that is important to know
        System.out.print("Enter string one: ");
        one = input.nextLine();
        System.out.print("Enter string two: ");
        two = input.nextLine();
        System.out.println(solve(one, two));
    }
    
}
