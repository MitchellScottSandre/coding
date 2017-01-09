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
public class Node {
    Node next = null;
    int data;
    
    public Node(int d) {
        this.data = d;
    }
    
    public void appendToTail(int d){
        Node end = new Node(d);
        Node n = this;
        while (n.next != null){
            n = n.next;
        }
        n.next = end;
    }
    
    public Node deleteNode(Node head, int d){
       Node n = head;
       if (n.data == d){//first one has data d, we want to remove it
           return head.next;
       }
       while (n.next != null){
           if (n.next.data == d){
               n.next = n.next.next; //delete that node
               return head;//the head never changed!
           }
           n = n.next;
       }
       return null; // ERROR
    }
}
