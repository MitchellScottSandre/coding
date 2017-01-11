package QuestionSolutions;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scott
 */
public class chapter1_q1 {

    /**
     * Implement an algorithm to determine if a string has all unique 
     * characters What if you can not use additional data structures?
     */
    
    public static Scanner input = new Scanner (System.in);
    
    public static boolean solve(String line){
        for (int i = 0; i < line.length(); i++){
            String copy = line;
            int before = copy.length();
            copy = copy.replace(line.charAt(i) + "", "");
            int after = copy.length();
            if (after != before - 1){
                return false;
            } 
        }
        return true;
    }
    
    public static void main(String[] args) {
        String line;
        System.out.println("Question: are there any repeated chars in the string?");
        System.out.print("Enter string: ");
        line = input.nextLine();
        System.out.println(solve(line));
        
    }
    
}
