
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_break(s, wordDict, 0);
    }
    
    public boolean word_break(String s, List<String> wordDict, int start){
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++){
            if(wordDict.contains(s.substring(start, end)) && word_break(s,wordDict, end)){
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("dog");
        dict.add("bean");
        dict.add("fly");
        dict.add("tiger");
        
        System.out.println(sol.wordBreak("dogtiger", dict));
    }
    
}
