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
public class MatcheBrackets {
    
    public static Scanner input = new Scanner (System.in);

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
    public static void main(String[] args) {
        String line;
        line = input.nextLine();
        LinkedList <Character> stack = new LinkedList<Character>();
        //ArrayList <Character> stack = new ArrayList<Character>();
        
        for (int i = 0; i < line.length(); i++){
           if (line.charAt(i) == '('){
               stack.push(')');//inserts at front
           } else if (line.charAt(i) == '['){
               stack.push(']');
           } else if (line.charAt(i) == stack.getFirst()){//closing bracket, matches
               stack.pop(); // remove one on TOP
           } else {//closing bracket, does NOT MATCH
               System.out.println("FALSE");
               break;
           }
        }
        
        if (stack.isEmpty() == true){
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
    
}
