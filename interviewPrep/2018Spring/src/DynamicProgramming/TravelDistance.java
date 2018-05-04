/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DynamicProgramming;

import java.util.Date;

/**
 *
 * @author scott
 */
public class TravelDistance {
    public static int[] data;
    
    // BAD WAY --> NO MEMOIZATION
    public static int countDistanceWays2(int n) {
        if (n <= 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int d = countDistanceWays2(n - 1) + countDistanceWays2(n - 2) + countDistanceWays2(n - 3) ;
        return d;
    }
    
    // Good way
    // number ways to walk distance n using 1,2,3 steps
    // much faster
    public static int countDistanceWays(int n) {
        if (n <= 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        if (data[n - 1] != 0) return data[n - 1];
        int d = countDistanceWays(n - 1) + countDistanceWays(n - 2) + countDistanceWays(n - 3) ;
        data[n - 1] = d;
        return d;
    }
    
    public static void main(String[] args) {
        int val = 25;
        data = new int[val];
        
        Long a = new Date().getTime();
        System.out.println(countDistanceWays(val));
        Long b = new Date().getTime();
        System.out.println("time " + (b - a));
        
        a = new Date().getTime();
        System.out.println(countDistanceWays2(val));
        b = new Date().getTime();
        System.out.println("time " + (b - a));
    }
    
}
