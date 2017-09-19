/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author scott
 */
public class CountNumbersFrom1To10000 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int fourDif = 0;
        int threeDif = 0;
        int twoDif = 0;
        int oneDif = 0;
        for (int i = 0; i < 10000; i++) {
            String s = Integer.toString(i);
            // add on 0s to front as necessary
            for (int j = 0; j < 4 - s.length(); j++) {
                s = "0" + s; 
            }
            Set<Character> differentDigits = new HashSet<>();
            for (int j = 0; j < s.length(); j++) {
                differentDigits.add(s.charAt(j));
            }
            switch (differentDigits.size()) {
                case 1:
                    oneDif++;
                    break;
                case 2:
                    twoDif++;
                    break;
                case 3:
                    threeDif++;
                    break;
                case 4:
                    fourDif++;
                    break;

            }
        }
        
        System.out.println("1: " + oneDif);
        System.out.println("2: " + twoDif);
        System.out.println("3: " + threeDif);
        System.out.println("4: " + fourDif);
    }

}
