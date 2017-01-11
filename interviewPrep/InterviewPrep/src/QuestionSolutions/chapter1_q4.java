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
     * 
     * defn: a word, phrase, or sentence formed from another by rearranging its letters: “Angel” is an anagram of “glean.”.
     */
    public static Scanner input = new Scanner (System.in);
    
    public static boolean isAnagram(String one, String two){
        String copyOne = one.replaceAll(" ", "");//get rid of all of the spaces for each
        String copyTwo = two.replaceAll(" ", "");
        if (copyOne.length() != copyTwo.length()){//number of chars remaining must be the same
            return false;
        }
        
        //same length: to be anagrams, must have same number of each letter
        for (int i = 0; i < copyOne.length(); i++){
            char c = copyOne.charAt(i); //this is our char
            copyOne = copyOne.replaceAll(c + "", ""); //remove char
            int x = copyOne.length();//after length
            copyTwo = copyTwo.replaceAll(c + "", "");
            int y = copyTwo.length();
            if (x != y){
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        String one, two;
        System.out.println("Question: Are these anagrams?");
        System.out.print("Enter string one: ");
        one = input.nextLine();
        System.out.print("Enter string two: ");
        two = input.nextLine();
        System.out.println(isAnagram(one, two));
    }
    
}
