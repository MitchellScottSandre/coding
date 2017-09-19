package PracticeProblems;

/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class RemoveNthNodeFromEndOfList {

    public static String rNodesAwayIsNull(ListNode node, int r) {
        for (int i = 0; i < r; i++) {
            if (node != null) {
                node = node.next;
            } else {
                return "ERROR";
            }
        }

        if (node == null) {
            return "NULL";
        } else {
            return "NOT_NULL";
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // handle length 1 case
        if (head.next == null) {
            return null;
        }
        boolean keepGoing = true;
        ListNode curr = head;

        // move curr to node BEFORE nodeToDelete
        while (keepGoing) {
            switch (rNodesAwayIsNull(curr, n + 1)) {
                case "NULL":
                    keepGoing = false;
                    break;
                case "NOT_NULL":
                    curr = curr.next;
                    break;
                case "ERROR":
                    head = head.next;
                    return head;
            }
        }

        // now, curr.next == nodeToDelete
        curr.next = curr.next.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;

        ListNode result = removeNthFromEnd(a, 4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
