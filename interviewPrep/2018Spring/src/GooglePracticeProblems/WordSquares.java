package GooglePracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
For example, the word sequence ["ball","area","lead","lady"] forms a word square 
because each word reads the same both horizontally and vertically.
 */
public class WordSquares {
    
    List<List<String>> answer = new ArrayList<>();
    
    public static void helper(String[] availableWords, String[] currWords) {
        // check currWords
        int r = currWords.length - 1;
//        for (int r = 0; r < currWords.length; r++) {
            String horWord = "";
            String verWord = "";
            
            for (int c = 0; c < currWords.length; c++) {
                horWord += currWords[r].charAt(c) + "";
                verWord += currWords[c].charAt(r) + "";
            }
            
            System.out.println(horWord + " .. " + verWord);
            if (!horWord.equals(verWord)) return;
//        }
        // try adding each available word
        for (int i = 0; i < availableWords.length; i++) {
            
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        
        return null;
    }
    public static void main(String[] args) {
        String[] availableWords = {"lead","lady","ball"};
        String[] currWords = {"wall", "area"};
        helper(availableWords, currWords);
    }
    
}
