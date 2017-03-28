/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtablecomparisons;

import java.util.ArrayList;

/**
 *
 * @author scott
 */
public class HashTable_linked<T> {//basic hash table
    
    public static Node table[];//array of nodes
    public static int defaultSize = 100000;
    
    public HashTable_linked(){
        table = new Node[defaultSize];//array of nodes
    }
    
    public HashTable_linked(int size){
         table = new Node[size];
    };
    
    public void insert(int hashValue,String s){//hashValue already determined
        Node newNode = new Node();
        newNode.data = s;
        newNode.next = table[hashValue];
        table[hashValue] = newNode;
    }
    
    public void printTable()  {  
        for (int i=0; i<table.length; i++) {
            if (table[i] != null) {
                Node p = table[i];
                while (p != null) {
                    System.out.println(i + " : " + p.data);
                    p = p.next;
                }
            }
        }
    }
    

}
