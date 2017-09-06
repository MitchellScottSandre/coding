
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scott
 */
public class ArraysAndStrings_1_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(checkIfOneCharAaway("pale", "ple"));
        System.out.println(checkIfOneCharAaway("pale", "bake"));
        System.out.println(checkIfOneCharAaway("the", "then"));
        System.out.println(checkIfOneCharAaway("the", "fhe"));
        System.out.println(checkIfOneCharAaway("the", "fhtn"));
        System.out.println(checkIfOneCharAaway("the", "eht"));
    }
    
    public static boolean checkIfOneCharAaway(String a, String b){
//        String s1 = a.length() < b.length() ? a : b;
//        String s2 = a.length() < b.length() ? b : a;
//        
//        // Second string is too long
//        if (s2.length() - s1.length() > 1) {
//            return false;
//        }
//        
//        Set<String> s1Letters = new HashSet<>();
//        Set<String> s2Letters = new HashSet<>();
//        for (int i = 0; i < s1.length(); i++){
//            s1Letters.add(s1.charAt(i) + "");
//        }
//        for (int i = 0; i < s2.length(); i++){
//            s2Letters.add(s2.charAt(i) + "");
//        }
//        
//        // More than 1 character apart
//        int numberDifferentLetters = 0;
//        for(String letter : s1Letters){
//            if (s2Letters.contains(letter) == false){
//                numberDifferentLetters++;
//            }
//            if (numberDifferentLetters > 1){
//                return false;
//            }
//        }
//        return true;
    }
    
}
