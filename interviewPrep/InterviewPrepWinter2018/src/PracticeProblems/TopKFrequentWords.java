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
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author scott
 */
public class TopKFrequentWords {
    
    class WordInfo {
        String word;
        int occurs;
        public WordInfo(String w, int x){
            word = w;
            occurs = x;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> answer = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        // add to map
        for (String s : words){
            Integer x = map.get(s);
            if (x == null){
                map.put(s, 1);
            } else {
                map.put(s, x + 1);
            }
        }
        
        // add to max heap
        Queue<WordInfo> maxHeap = new PriorityQueue<WordInfo>((a,b) -> b.occurs == a.occurs ? a.word.compareTo(b.word) : b.occurs - a.occurs);
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            maxHeap.offer(new WordInfo(entry.getKey(), entry.getValue()));
        }
        
        // add to answer list
        for (int i = 0; i < k; i++){
            answer.add(maxHeap.poll().word);
        }
        
        return answer;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
