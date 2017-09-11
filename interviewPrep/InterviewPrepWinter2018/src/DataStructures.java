
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
        
        Hashtable<String, Integer> table = new Hashtable<>();
        Stack<Integer> stack = new Stack<>();
        LinkedList<String> list = new LinkedList<>();
        PriorityQueue<String> queue = new PriorityQueue<>();
        Deque<String> deck = new ArrayDeque<>();
        
        // Hashtable
        table.put("key", 0);
        table.put("bob", 1);
        table.get("key");
        table.containsKey("key");
        table.containsValue(0);
        table.remove("key");
        table.clear();
        
        // Stack
        stack.add(0); // add to top of stack
        stack.peek(); // look at top of stack
        stack.pop(); // pop and return value from top of stack
        stack.firstElement(); // returns bottom, first element
    }
    
}
