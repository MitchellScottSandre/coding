
package DataStructures;

public class RedBlackBST {

    private static final int RED = 0;
    private static final int BLACK = 1;
    private final Node nil = new Node(-1); // smart to use this instead of NULL
    private Node root = nil;
    
    private class Node {
        int key = -1;
        int color = BLACK;
        Node left = nil;
        Node right = nil;
        Node parent = nil;
        
        public Node(int key){
            this.key = key;
        }
    }
    
    public void insert(int key){
        Node node = new Node(key);
        Node temp = root;
        if (root == nil){
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            // Go and search to insert it
            while (true){
                if (node.key < temp.key){
                    if (temp.left == nil){
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == nil){
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }
    
    // Fixes the tree. node is the newly inserted node
    private void fixTree(Node node){
        while(node.parent.color == RED){
            Node uncle = nil;
            if (node.parent == node.parent.parent.left){ // parent is to the left of grandparent
                uncle = node.parent.parent.right;
                
                if (uncle != nil && uncle.color == RED){
                    // Toggle colours of parent, uncle, and grandparent.
                    // Switch node with grandparent
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                
                // ****LEFT RIGHT CASE (rotate P left)****  //           GB            GB
                if (node == node.parent.right){             //     PR        ==>     NR
                    node = node.parent;                     //        NR           PR
                    rotateLeft(node);                       //
                }
                
                // ****LEFT LEFT CASE (rotate G right)****
                // uncle is nil and color = BLACK, or is    //            GB           P
                // not nil and color still is BLACK         //         PR    ==>     N   G
                node.parent.color = BLACK;                  //      NR
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);
                        
            } else { //parent is to the right of grand parent
                uncle = node.parent.parent.left;
                
                if (uncle != nil && uncle.color == RED){
                    // Toggle colours of parent, uncle, and grandparent.
                    // Switch node with grandparent
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                
                // ****RIGHT LEFT CASE (rotate P right)****   //   GB            GB
                if (node == node.parent.left){                //        PR  ==>     NR
                    node = node.parent;                       //     NR                PR
                    rotateRight(node);                        //
                }
                
                // **** RIGHT RIGHT CASE (rotate G left)        //    GR                 PB
                node.parent.color = BLACK;                      //      PB     ==>   GR     NR
                node.parent.parent.color = RED;                 //         NR
                rotateLeft(node.parent.parent);
            }
        }
        
        root.color = BLACK;
    }
    
    private void rotateLeft(Node node){
        if (node.parent != nil){
            if (node == node.parent.left){
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil){
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }
    
    private void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
