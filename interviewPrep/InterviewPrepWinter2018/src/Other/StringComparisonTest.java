/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 *
 * @author scott
 */
public class StringComparisonTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String a = "cat";
        String b = "cat";
        String c = null;
        System.out.println("1: == " + a == b);
        System.out.println("2: .equals " + a.equals(b));
        System.out.println(a.equals(c));
    }
    
}
