/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author scott
 */
public class ListTester1 {

    /**
     * @param args the command line arguments
     */
    
    public static void displayList(Node head){
        Node n = head;
        while (n.next != null){
            System.out.println(n.data);
            n = n.next;
        }
    }
    
    public static void main(String[] args) {
        Node head = new Node (0);//create head
        for (int i = 0; i < 30; i++){
            head.appendToTail((int) (Math.random() * 100 - 50)); //create list 
        }
        displayList(head);
    }
    
}
