/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

/**
 *
 * @author scott
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        
        int leftExponent = (int) Math.log10(x);
        int rightExponent = 0;
        
        while (leftExponent > rightExponent) {
//            System.out.println("x == " + x);
//            System.out.println("xxxxx" + leftExponent);
            int leftNum = (int) (x / Math.pow(10, leftExponent));
            int rightNum = x % 10;
//            System.out.println("..." + leftNum + " ... " + rightNum);
            if (leftNum != rightNum) return false;
            
            // remove left and right numbers
            x = x - leftNum * (int) Math.pow(10, leftExponent);
            x = x / 10; 
            
            leftExponent-= 2;
            
//            System.out.println("x is now " + x);
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome(1101221011));
    }
    
}
