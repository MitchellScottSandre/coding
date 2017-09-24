
package PracticeProblems;

/**
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.


 */
public class ReverseList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n) return head;
        
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        
        ListNode beforeReverse = null;
        ListNode afterReverse = null;
        
        ListNode firstInReverse = null;
        ListNode lastInReverse = null;
        int counter = 1;
        while (counter <= n){
            if (counter == m - 1){
                beforeReverse = curr;
            } else if (counter == m){
                firstInReverse = curr;
            } else if (counter == n){
                lastInReverse = curr;
            }
            
            if (counter >= m){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            } else {
                curr = curr.next;
            }

            counter++;
        }
        if (m == 1){
            head = lastInReverse;
        }
        
        if (beforeReverse != null){
            beforeReverse.next = lastInReverse; 
        }
        if (firstInReverse != null){
            firstInReverse.next = curr; 
        }
        return head;
    }
    
    public static void main(String[] args) {
        ReverseList2 sol = new ReverseList2();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = null;
        
        ListNode start = sol.reverseBetween(a, 1, 5);
        while(start != null){
            System.out.println(start.val);
            start = start.next;
        }
        
        
        
    }
    
}
