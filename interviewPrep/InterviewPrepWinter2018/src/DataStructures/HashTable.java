/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.ArrayList;

/**
 *
 * @author scott
 */
public class HashTable {
    public class Node {
        String value;
        Node next;
        
        public Node(String val){
            this.value = val;
            this.next = null;
        }
    }
    
    int startSize = 20;
    Node table[];
    // Initializer
    public HashTable(){
        table = new Node[this.startSize];
    }
    
    public void insert(String value){
        Node newNode = new Node(value);
        int hashLocation = hashLocation(value);
        Node currNode = this.table[hashLocation];
        newNode.next = currNode;
        this.table[hashLocation] = newNode;

        // Before:
        // x x x x currNode x x x
        // After:
        // x x x x newNode x x x
        //           ||
        //           \/
        //         currNode
    }
    
    public boolean lookup(String value){
        int hashLocation = hashLocation(value);
        Node currNode = table[hashLocation];
        
        if (currNode == null){
            return false; // doesn't exist
        } else if (currNode.value.equals(value)){
            return true;
        } else {
            // first node doesn't hold the value, so search for it 
            currNode = currNode.next;
            while (currNode.next != null){
                if (currNode.value.equals(table)){
                    return true;
                }
                currNode = currNode.next;
            }
        }
        return false;
    }
    
    // Hash int value for a String
    public int hash(String value){
        int hash = 7;
        for(int i = 0; i < value.length(); i++){
            hash = hash*31 + value.charAt(i);
        }
        return hash;
    }
    
    public int hashLocation(String value){
        return hash(value) % this.table.length;
    }
    
    public void print(){
        for (int i = 0; i < table.length; i++){
            Node currNode = table[i];
            System.out.print(i + " : ");
            if (currNode != null){
                System.out.print(currNode.value + ", ");
                
                while (currNode.next != null){
                    currNode = currNode.next;
                    System.out.print(currNode.value + ", ");
                }
            }
            System.out.print("\n");
        }
    }
    
    public static void main(String[] args) {
        HashTable t = new HashTable();
        t.insert("first");
        t.insert("second");
        t.insert("third");
        t.insert("a");
        t.insert("zzzz");
        
        System.out.println(t.lookup("a")); // true
        System.out.println(t.lookup("aaaaaa")); // false
        t.print();
    }
    
}
