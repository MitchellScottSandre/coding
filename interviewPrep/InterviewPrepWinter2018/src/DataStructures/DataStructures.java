package DataStructures;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scott
 */
public class DataStructures {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<Integer> pp = new LinkedList<>();
//        pp.offer();
//        pp.peek();
//        pp.poll();
        Queue<Integer> heap = new PriorityQueue<>((a,b) -> b - a); // max heap
        Set<Character> set;   
        heap.offer(5);
        heap.offer(22);
        heap.offer(3);
        heap.offer(7);
        System.out.println(heap.poll());
        
        
        Hashtable<String, Integer> hashTable = new Hashtable<>(); // put, containsKey
        HashSet ss = new HashSet(); // add
        
        Stack<Integer> stack = new Stack<>();
        LinkedList<String> list = new LinkedList<>();
//        list.stream().fil
        PriorityQueue<String> queue = new PriorityQueue<>(); // max heap??
        Deque<String> deck = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        
        // Google Guava data structures
        Multimap<String, Integer> multiMap = ArrayListMultimap.create();
        Table<String, Integer, Integer> table = HashBasedTable.create();
        
        // Hashtable
        hashTable.put("key", 0);
        hashTable.put("bob", 1);
        hashTable.get("key");
        hashTable.containsKey("key");
        hashTable.containsValue(0);
        hashTable.remove("key");
        hashTable.clear();
        hashTable.size();
        
        // Stack
        stack.add(0); // add to top of stack
        stack.peek(); // look at top of stack
        stack.pop(); // pop and return value from top of stack
//        stack.firstElement(); // returns bottom, first element
//        stack.remove(0); // remove value at that index
        stack.size();
        // List
        list.add("added to end");
        list.addFirst("now this is the first element");
        list.addLast("this is now actually the end");
        list.isEmpty();
        list.size();
        
        // Proprity Queue
        queue.add("first Element in and first element out"); // adds to front of queue
        queue.peek(); // returns value of what is in front of queue, or null
        queue.poll(); // returns AND REMOVES what is at the head of the queue, or null
        queue.remove("tryAndRemoveThis");
        
        // Deque
        deck.add("first Element in deck");
        deck.addFirst("now this is the first one");
        deck.addLast("this is the last one");
        deck.peekFirst(); // gets value of first
        deck.peekLast(); // gets value of last element
        deck.removeFirst();
        deck.removeLast();
        
    }
    
}
