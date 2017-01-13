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
public class q02_strings {

    /**
     * Write a method which will remove any given character from a String? 
     */
    public static Scanner input = new Scanner (System.in);
    
    public static String removeCharFromS(String s, char c){
        return s.replaceAll(c + "", "");
        
    }
    public static void main(String[] args) {
        String s;
        char c;
        System.out.print("Enter String: ");
        s = input.nextLine();
        System.out.print("Enter char to remove from string: ");
        c = input.nextLine().charAt(0);
        System.out.println(removeCharFromS(s, c));
        
    }
    
}
