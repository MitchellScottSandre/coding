/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterviewQuestions;

import DataStructures.Node;

/**
 *
 * @author scott
 */
public class LinkedList_q3 {

    /**
     * Implement an algorithm to delete a node in the middle of a single linked list, given only access to that node
        EXAMPLE
        Input: the node ‘c’ from the linked list a->b->c->d->e
        Result: nothing is returned, but the new linked list looks like a->b->d->e  
     */
    
    public static void deleteNodeN(Node n){
        Node temp = n;
        n = n.next;
        temp.deleteNode(temp, 0);
    }
    
    public static void main(String[] args) {
        Node head = new Node(0);
        head.appendToTail(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        
        deleteNodeN(head.next.next.next);
        
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
        
        
    }
    
}
