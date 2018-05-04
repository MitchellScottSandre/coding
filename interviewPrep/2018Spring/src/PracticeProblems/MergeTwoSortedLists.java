/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode head = null;
        while (l1 != null || l2 != null) {
            int val;
            boolean isL1Null = l1 == null;
            boolean isL2Null = l2 == null;
            // Get lowest value, move that node ahead
            if ((!isL1Null && isL2Null) || (!isL1Null && !isL2Null && l1.val < l2.val)) {
                val = l1.val;
                l1 = l1.next;
            } else {
                val = l2.val;
                l2 = l2.next;
            }
            
            if (node == null) {
                node = new ListNode(val);
                head = node;
            } else {
                ListNode nextNode = new ListNode(val);
                node.next = nextNode;
                node = nextNode;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
