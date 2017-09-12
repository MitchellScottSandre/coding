
package DataStructures;

/**
 *
 * @author scott
 */
public class LinkedList {

    public class Node {
        String val;
        Node prev;
        Node next;
        
        public Node(String value){
            this.val = value;
            this.prev = null;
            this.next = null;
        }
    }
    
    Node head;
    Node tail;
    
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public void addFront(String val){
        Node newNode = new Node(val);
        // If empty, set head and tail to new Node. Else, add to front
        if (this.head == null && this.tail == null){
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
            newNode.prev = null;
            newNode.next = this.head;
            this.head = newNode;
        } 
    }
    
    public void addBack(String val){
        Node newNode = new Node(val);
        // If empty, set head and tail to new Node. Else, add to back
        if (this.head == null && this.tail == null){
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            newNode.next = null;
            this.tail = newNode;
        }  
    }
    
    public void delete(String val){
        if (this.head == null){
            return;
        } else {
            // search for the val
            Node currNode = this.head;
            while(currNode != null){
                if (currNode.val.equals(val)){
                    if (currNode.prev != null){
                        currNode.prev.next = currNode.next;
                    } else {
                        // this is the head
                        this.head = currNode.next;
                    }
                    
                    if (currNode.next != null){
                        currNode.next.prev = currNode.prev;
                    } else {
                        // this is the tail
                        this.tail = currNode.prev;
                    }
                    
                    currNode = null;
                } else {
                    currNode = currNode.next;
                }
            }
        }
    }
    
    public void print(){
        System.out.print("\n");
        
        Node currNode = this.head;
        while (currNode != null){
            System.out.print(currNode.val + ", ");
            currNode = currNode.next;
        }
    }
    
    public void reverse(){
        Node currNode = this.head;
        while (currNode != null){
            Node temp = currNode.next;
            // swift prev and next for all nodes
            currNode.next = currNode.prev;
            currNode.prev = temp;
            
            // Mode on to actual next node in linked list
            currNode = temp;
        }
        
        currNode = this.head;
        this.head = this.tail;
        this.tail = currNode;
    }
    
    
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.addBack("first");
        l.addBack("second");
        l.addBack("third");
        l.addBack("fourth");
        l.addBack("fifth");
        l.addFront("newFirst");
        l.print();
        l.delete("newFirst"); // deleting head
        l.print();
        l.delete("fifth"); // deleting tail
        l.print();
        l.delete("second"); // deleting middle node
        l.print();
        l.delete(("DNE")); // does not exist
        l.print();
        l.reverse(); // should reverse order
        l.print(); 
    }
    
}
