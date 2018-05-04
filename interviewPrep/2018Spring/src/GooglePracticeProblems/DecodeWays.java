/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GooglePracticeProblems;

/**
 *
 * @author scott
 */
public class DecodeWays {
    
    public static int helper(String s) {
        if (s.length() == 2) {
            int a = Integer.parseInt(s.charAt(0) + "");
            int b = Integer.parseInt(s.charAt(1) + "");
            if (a <= 2 && b <= 6) return 2;
            return 1;
        } else {
            return 1;
        }
    }

    public static int numDecodings(String s) {
        int totalSum = 0;
        int[] savedData = new int[s.length() - 1];
        
        // iterate through each starting position
        for (int i = 0; i < s.length() - 1; i++) {
            int totalProduct = 1;
            // generate all the pairs of letters
            String[] pairs = new String[(s.length() - i) / 2];
            int count = 0;
            for (int j = i; j < s.length() - 1; j = j + 2){
                pairs[count] = s.substring(j, j + 2);
                count++;
            }
            int x = 0;
            for (int j = 0; j < pairs.length; j++) {
                int prod = helper(pairs[j]);
                System.out.println(pairs[j] + " .. " + prod);
                totalProduct = totalProduct * prod;
                if (prod == 1 && i > 0) {
                    System.out.println("GG");
                    x++;
                }
            }
            totalProduct -= x + 1;
//            totalProduct = totalProduct - (i == 0 ? 0 : i);
            totalSum += totalProduct; // don't double count having all the indivudal numbers
            System.out.println("totalProduct " + totalProduct);
        }
        return totalSum;
    }
    
    public static void main(String[] args) {
        System.out.println(numDecodings("1234121"));
    }
    
}
