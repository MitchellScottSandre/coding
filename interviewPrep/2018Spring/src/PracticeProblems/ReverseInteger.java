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
public class ReverseInteger {

    public static int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        boolean isNegative = x < 0;
        x = Math.abs(x);
        int exponent = (int) Math.log10(x);
        double newNumber = 0;
        while (exponent >= 0) {
            int backNumber = x % 10;
            newNumber += backNumber * Math.pow(10, exponent);

            exponent--;
            x = x /10;
            if (newNumber > Integer.MAX_VALUE) return 0;
        }

        return (int) newNumber * (isNegative ? -1: 1);
    }
    
    public static void main(String[] args) {
//        System.out.println(reverse());
    }
    
}
