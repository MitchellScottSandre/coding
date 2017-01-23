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
public class LinkedList_q2 {

    //Implement an algorithm to  nd the nth to last element of a singly linked list
    
    public static int findNthLastElement(Node head, int index){
        Node temp = head;
        while(temp.next.next.next != null){
            temp = temp.next;
        }
        return temp.data;
    }
    public static void main(String[] args) {
        Node head = new Node(0);
        head.appendToTail(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        head.appendToTail(6);
        head.appendToTail(7);
        //now we want to find the nth last element of the singly linked list
        System.out.println("3rd last element is " + findNthLastElement(head, 3));
        
        
    }
    
}
