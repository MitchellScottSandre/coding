/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.LinkedList;

/**
 *
 * @author scott
 */
public class ListTester2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList n = new LinkedList();
        n.add(10);
        n.add(20);
        n.add(30);
        n.add(40);
        n.add(50);
        n.add(66);
        n.add(4456);
        n.add(78);
        n.add(2);
        for (int i = 0; i < n.size(); i++){
            System.out.println("Index " + i + ": " + n.get(i));
        }
        System.out.println("Popping..." + n.pop());
        System.out.println("Inserting into index 4 number 7...");
        n.add(4, 7);
        for (int i = 0; i < n.size(); i++){
            System.out.println("Index " + i + ": " + n.get(i));
        }
        
        System.out.println("Pushing 3333....");
        n.push(3333);
        
         for (int i = 0; i < n.size(); i++){
            System.out.println("Index " + i + ": " + n.get(i));
        }
         
        //pushing adds to front
        //adding adds to back
        //pop removes first
        //how to remove last? n.getLast()
        
        
        
      
        
    }
    
}
