/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
package PracticeProblems;

import java.util.ArrayDeque;
import java.util.Deque;



public class AddTwoNumbers {
    
    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    
    // need to be able to handle very very very large numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> deque1 = new ArrayDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();
        
        //add to deque 1
        while (l1 != null){
            deque1.add(l1.val);
            l1 = l1.next;
        }
        
        //add to deque 2
        while (l2 != null){
            deque2.add(l2.val);
            l2 = l2.next;
        }
        
        //make dequ1 the shorter deque
        if (deque1.size() > deque2.size()){
            Deque<Integer> temp = deque1;
            deque1 = deque2;
            deque2 = temp;
        }
        
        //append 0s to the shorter stack
        //append 0s to the shorter stack
        while(deque1.size() != deque2.size()){
            deque1.add(0);
        }
        System.out.println(deque2.size());
        System.out.println(deque1.size());
        //add them together! they are the same size
        int carry = 0;
        ListNode front = null;
        ListNode curr = null;
        while(deque1.isEmpty() == false){
            int num1 = deque1.pollFirst();
            int num2 = deque2.pollFirst();
            int sum = num1 + num2 + carry;
            if (sum > 9){
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }

            if (front == null){
                front = new ListNode(sum);
                curr = front;
            } else {
                ListNode node = new ListNode(sum);
                curr.next = node;
                curr = node;
            }
        }
        
        // need to add this number too!
        if (carry == 1){
            ListNode node = new ListNode(1);
            curr.next = node;
        }
        
        return front;
    }


}
