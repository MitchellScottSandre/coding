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
public class MatchBrackets {
    
    public static Scanner input = new Scanner (System.in);
    //NO SPACES
    //given a string containing only ( [ ] and ), output true if 
    //all of the brackets are properly paired, or false if not
    //ex: ( [] () [ () ] ) is good
    //ex: ( ( ] ) is bad
    //ex: ) ) is bad
    //ex: ( ( ( is bad
    //ex: ( ( [ ) is bad
    
    public static void displayStack(LinkedList<Character> s){
        for (int i = 0 ; i < s.size(); i++){
            System.out.print(s.get(i) + ", ");
        }
        System.out.println();
    }
    
    public static boolean solve(String line){
        LinkedList <Character> stack = new LinkedList<Character>();
        
        for (int i = 0; i < line.length(); i++){
           if (line.charAt(i) == '('){
               stack.push(')');//inserts at front
           } else if (line.charAt(i) == '['){
               stack.push(']');
           } else if (stack.isEmpty() == false && line.charAt(i) == stack.getFirst()){//closing bracket, matches
               stack.pop(); // remove one on TOP
           } else {//closing bracket, does NOT MATCH
               return false;
           }
        }
        
        if (stack.isEmpty() == true){
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        String line;
        System.out.print("Enter line of brackets, no spaces: ");
        line = input.nextLine();
        System.out.println(solve(line));
        
    }
    
}
