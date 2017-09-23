
package PracticeProblems;

import java.util.Stack;

/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ')' || c == '}' || c == ']'){
                if (!stack.isEmpty() && stack.peek() == c){
                    stack.pop();
                } else {
                    return false;
                }
            } else {
            switch (c) {
                case '(':
                    stack.add(')');
                    break;
                case '{':
                    stack.add('}');
                    break;
                case '[':
                    stack.add(']');
                    break;
            }
            }
        }
        
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // TdfsdfODO code application logic here
    }
    
}
