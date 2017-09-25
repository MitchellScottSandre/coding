/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsOfAPhoneNumber {
    List<String> solution = new ArrayList<>();
    Map<Character, String> phone = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");
        
        getAll(digits, "", 0);
        return solution;
    }
    
    public void getAll(String digits, String curr, int i){
        if (curr.length() == digits.length()){
            solution.add(curr);
            return;
        }
        
        String nums = phone.get(digits.charAt(i));
        for(int j = 0; j < nums.length(); j++){
            String temp = curr;
            temp += nums.charAt(j);
            getAll(digits, temp, i + 1);
        }
    }
    
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber sol = new LetterCombinationsOfAPhoneNumber();
        List<String> answer = sol.letterCombinations("23");
        for(String s : answer){
            System.out.println(s);
        }
    }
    
}
