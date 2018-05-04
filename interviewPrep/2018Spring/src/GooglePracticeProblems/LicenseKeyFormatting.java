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
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        String answerString = "";

        int count = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c == '-') continue;
            if ((int) c >= 97) {
                c = (char)(c - 32);
            }
            
            if (count == K) {
                answerString = c + "-" + answerString;
                count = 1;
            } else {
                answerString = c + answerString;
                count++;
            }
        }
        
        return answerString;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
