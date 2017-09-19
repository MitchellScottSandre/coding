/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
package PracticeProblems;

//public class ListNode {

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//        int val;
//        ListNode next;
//        ListNode(int x) { val = x; }
//}

public class MergeKSortedLists {
 
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        ListNode currentNodeInCombinedList = null;
        List<ListNode> notNullListNodes = new ArrayList<>(Arrays.asList(lists));
        while(notNullListNodes.size() > 0){
            // add smallest value to the list
            ListNode smallestNode = null;
            int smallestVal = Integer.MAX_VALUE;
            int indexOfSmallestNode = 0;
            
            for (int i = 0; i < notNullListNodes.size(); i++){
                ListNode node = notNullListNodes.get(i);
                if (node != null){
                    if (node.val < smallestVal){
                        smallestVal = node.val;
                        smallestNode = node;
                        indexOfSmallestNode = i;
                    }
                } 
            }

            // we now have the smallest node
            if (head == null){
                head = smallestNode;
                currentNodeInCombinedList = head;
            } else {
                currentNodeInCombinedList.next = smallestNode;
                currentNodeInCombinedList = smallestNode;
            }
            
            if (notNullListNodes.get(indexOfSmallestNode) != null){
//                notNullListNodes.get(indexOfSmallestNode) = null; 
            } else {
                
            }
        }    
        
        return head;  
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(5);
        ListNode a3 = new ListNode(10);
        a1.next = a2;
        a2.next = a3;
        a3.next = null;
        
        ListNode b1 = new ListNode(2);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(22);
        b1.next = b2;
        b2.next = b3;
        b3.next = null;
        
        ListNode c1 = new ListNode(0);
        ListNode c2 = new ListNode(7);
        ListNode c3 = new ListNode(15);
        ListNode c4 = new ListNode(25);
        c1.next = c2;
        c2.next = c3;
        c3.next = c4;
        c4.next = null;
        ListNode[] array = {a1, b1, c1};
        long time1 = System.nanoTime();
        ListNode answer = mergeKLists(makeHugeListOfLength1Each(10000));
        long time2 = System.nanoTime();
        System.out.println("TIME: " + (time2 - time1));
        int lineCounter = 0;
        while (answer != null){
            System.out.print(answer.val + " -> ");
            answer = answer.next;
            lineCounter++;
            if(lineCounter == 30){
                System.out.print("\n");
                lineCounter = 0;
            }
        }
        System.out.println();
    }
    
    public static ListNode[] makeHugeListOfLength1Each(int length){
        ListNode[] list = new ListNode[length];
        for (int i = 0; i < length; i++){
            int x = (int) (Math.random() * 100);
            ListNode node = new ListNode(x);
            list[i] = node;
        }
        return list;
    }
    
}
