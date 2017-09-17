/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author scott
 */
public class LongestSubstringNoRepeats {

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> letters = new HashSet<>();
        ArrayList<Character> substring = new ArrayList<>();
        int counter = 0;
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            substring.add(c);
            counter++;
            if (letters.contains(c) == false) {
                letters.add(c);
            } else {
                // remove all characters up to that letter in the array list
                while (true) {
                    if (substring.get(0) == c) {
                        letters.remove(substring.get(0));
                        substring.remove(0);
                        break;
                    } else {
                        letters.remove(substring.get(0));
                        substring.remove(0);
                    }
                }
                letters.add(c);
                counter = substring.size();
            }
            if (counter > longest){
                longest = counter;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("ababaccabcdefgha"));
    }

}
