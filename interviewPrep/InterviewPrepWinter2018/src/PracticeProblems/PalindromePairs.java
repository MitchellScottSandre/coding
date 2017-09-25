
package PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) { // unique words, at most 1 ""
        List<List<Integer>> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // String to index in Words
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        
        // "" case
        if (map.get("") != null){
            // find all palindromes, add
            int index = map.get("");
            for (int i = 0; i < words.length; i++){
                String s = words[i];
                if (s != "" && isPalindrome(s)){
                    answer.add(Arrays.asList(index, i));
                    answer.add(Arrays.asList(i, index));
                }
            }
            map.remove("");
        }
        
        // a + b == b + a
        for(int i = 0; i < words.length; i++){
            String s = (words[i]);
            if (s.equals(""))continue;
            Integer found = map.get(reverseOf(s));
            if (found != null && found != map.get(s)){
                answer.add(Arrays.asList(i, found));
            }
        }
        
        // splicing
        // Case 1: s1[0 to cut] is a P && reverse of s1(cut + 1 to end) == s2, then P = s2 + s1
        // Case 2: s1[cut + 1 to end] is a P && reverse of s1(0 to cut) == s2, then P is s1 + s2
        for(int i = 0; i < words.length; i++){
            String s = words[i];
            if (s.equals(""))continue;
            for (int c = 1; c < s.length() - 1; c++){
                // Case 1
                String sub1 = s.substring(0, c);
                String rev = reverseOf(s.substring(c + 1, s.length()));
                if (isPalindrome(sub1) && map.containsKey(rev)){
                    answer.add(Arrays.asList(map.get(rev), i));
                }
                // Case 2
                String sub2 = s.substring(c + 1, s.length());
                String rev2 = reverseOf(s.substring(0, c));
                if (isPalindrome(sub2) && map.containsKey(rev2)){
                    answer.add(Arrays.asList(i, map.get(rev2)));
                }
            }
        }
        return answer;
    }
    
    public String reverseOf(String s){
        StringBuilder str = new StringBuilder(s);
        return str.reverse().toString();
    }
    
    public boolean isPalindrome(String a){
        return isPalindrome(a,"");
    }
    
    public boolean isPalindrome(String a, String b){
        String s = a + b;
        int i = 0;
        int j = s.length() - 1;
        while (i < j){
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args) {
        PalindromePairs sol = new PalindromePairs();
        String[] words = (String[]) Arrays.asList("a", "").toArray();
        List<List<Integer>> answer = sol.palindromePairs(words);
        for (List<Integer> l : answer){
            System.out.println(l.get(0) + " :: " + l.get(1));
        }
    }
    
}
