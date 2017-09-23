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
public class Powxn {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        } 
        if (x == 0.0){
            return x;
        }
        if (n < 0) return 1/x * myPow(1/x, -(n + 1));
        if (n == 2) return x*x;
        
        if (n % 2 == 0) return myPow(x*x, n/2); // 2^8 = (2^4)^2
        
        return x * myPow(x*x, n/2); // 2^9 = 2 * (2^4)^2
    }

    public static void main(String[] args) {
        Powxn sol = new Powxn();
        System.out.println(sol.myPow(2, -2147483648));
    }
    
    /*
    if (x == 0.0){
            return x;
        } else if (n < 0){
            return myPow(1.0 / x, -n);
        } else if (n == 0){
            return 1.0;
        } else {
            double product = 1;
            for (int i = 0; i < n; i++){
                product *= x;
            }
            return product;
        }
    */

}
