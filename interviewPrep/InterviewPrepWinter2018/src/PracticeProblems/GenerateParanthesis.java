package PracticeProblems;


import java.util.ArrayList;
import java.util.List;


/**
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class GenerateParanthesis {
    List<String> solution = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) return solution;
        
        generateAll(n, "(");
        return solution;
    }
    
    public void generateAll(int n, String curr){
        if (curr.length() == n * 2){
            solution.add(curr);
            return;
        }
        int numOpen = curr.length() - curr.replace("(", "").length();
        int numClosed = curr.length() - curr.replace(")", "").length();
        
        if (numOpen == n){
            generateAll(n, curr + ")");
        } else if (numOpen == numClosed){
            generateAll(n, curr + "(");
        } else {
            generateAll(n, curr + "(");
            generateAll(n, curr + ")");
        }
    }
    
    public static void main(String[] args) {
        GenerateParanthesis sol = new GenerateParanthesis();
        List<String> answer = sol.generateParenthesis(4);
        for (String s : answer){
            System.out.println(s);
        }
    }
    
}
