/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GooglePracticeProblems;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class MergeKSortedLinkedLists {
    
    public ListNode merge2SortedLists(ListNode a, ListNode b) {
        ListNode root = null;
        ListNode curr = null;
        
        while(!(a == null && b == null)) {
                if ((a != null && b != null && a.val <= b.val) || (a != null && b == null)) {
                    if (root == null) {
                        root = a;
                        curr = a;
                    } else {
                        curr.next = a;
                        curr = a;
                    }
                    a = a.next;
                } else {
                    if (root == null) {
                        root = b;
                        curr = b;
                    } else {
                        curr.next = b;
                        curr = b;
                    }
                    b = b.next;
                }
        }
        
        return root;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int last = lists.length;
        int i = 0;
        
        while (last >= 0) {
            // merge lists at i and last together, put into i
            lists[i] = merge2SortedLists(lists[i], lists[last]);
            i ++;
            last --;
            
        }
        return lists[0];
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(5);
        
        a.next = b;
        b.next = c;
        
        ListNode dd = new ListNode(2);
        ListNode ee = new ListNode(4);
        dd.next = ee;
        
//        ListNode sorted = merge2SortedLists(a, dd);
        
//        ListNode curr = sorted;
//        while (curr != null) {
//            System.out.println(curr.val);
//            curr = curr.next;
//        }
    }

}
