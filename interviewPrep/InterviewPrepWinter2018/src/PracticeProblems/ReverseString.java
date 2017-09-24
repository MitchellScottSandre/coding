/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

/**
 * //think very very very long string case!
 */
public class ReverseString {

    public String reverseString(String s) {
        int l = s.length();
        if (l <= 1) return s;
        String left = s.substring(0, l / 2);
        String right = s.substring(l/2, l);
        return reverseString(right) + reverseString(left);
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
