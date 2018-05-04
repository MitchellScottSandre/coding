/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GooglePracticeProblems;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author scott
 */
public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        // Check if B contains any letter not in A
        Set<Character> lettersB = new HashSet<>();
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            if (!lettersB.contains(c)) {
                lettersB.add(c);
                if (A.indexOf(c) == -1) return -1;
            }
            if (lettersB.size() == 26) break;
        }
        
        // Append A to itself and see if B is a substring of it (inneficient)
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (A.indexOf(B) != -1) return i;
            A += A;
        }
        
        return -1;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
