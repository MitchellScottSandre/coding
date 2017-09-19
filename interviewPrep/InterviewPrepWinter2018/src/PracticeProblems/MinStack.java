
package PracticeProblems;
/*
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. CONSTANT TIME!

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

*/

class MinStack {
    private Node head = null;
    
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        if (head == null){
            head = new Node(x, x);
        } else {
            Node node = new Node(x, Integer.min(x,head.min), head);
            this.head = node;
        }
    }
    
    public void pop() {
        this.head = head.next;
    }
    
    public int top() {
        return this.head.val;
    }
    
    public int getMin() {
        return this.head.min;
    }
    
    private class Node {
        int val;
        int min;
        Node next;
        
        public Node (int val, int min){
            this (val, min, null);
        }
        
        public Node (int val, int min, Node next){
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */