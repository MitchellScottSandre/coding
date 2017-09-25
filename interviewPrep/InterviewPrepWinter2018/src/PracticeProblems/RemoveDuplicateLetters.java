/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
*You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        HashSet<Character> set = new HashSet<>();
        Queue<Character> minHeap = new PriorityQueue<>((a,b) -> (a - b));
        
        while(s.length() > 0){ //iterate through the entire string
            char c = s.charAt(0); // grab the character
            if(!set.contains(c)){ // if not in seen before, add it
                set.add(c);
                minHeap.offer(c);
                s = s.replace(c + "", "");
            }
        }
        String answer = "";
        while(minHeap.size() > 0){ // build string while removing smallest character at a time from heap
            answer += minHeap.poll(); 
        }
        return answer;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
