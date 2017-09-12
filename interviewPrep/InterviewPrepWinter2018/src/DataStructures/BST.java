/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author scott
 */
public class BST {

    public class Node {
        int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
            left = null; 
            right = null;
        }
    }
    
    Node root;
    
    public BST(){
        this.root = null;
    }
    
    public void insert(Node rootNode, int val){
        Node newNode = new Node(val);
        if (rootNode == null){
            rootNode = newNode;
        } else {
            if (rootNode.val < val){
                insert(rootNode.right, val);
            } else {
                insert(rootNode.left, val);
            }
        }
    }
    
    public boolean lookup(int val){
        
        return false;
    }
    
    // 1. Node to be delete is a leaf node (no children)
    // 2. node to be deleted has only one child
    // 3. Node to be delete has 3 children
    public void delete(int val){
        Node parent = this.root;
        Node current = this.root;
        boolean isLeftChild = false;
        
        while (current.val != val){
            parent = current;
            if (current.val > val){
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            
            if (current == null){
                return;
            }
        }
        
        // If we are here it means we have found the node
        // Case 1. leaf node
        if (current.left == null && current.right == null){
            if (current == this.root){
                this.root = null;
            }
            
            if (isLeftChild){
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        
        // Case 2. one child
        else if (current.left == null){
            if (current == this.root){
                this.root = current.right;
            } else if (isLeftChild){
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null){
            if (current == this.root){
                this.root = current.left;
            } else if (isLeftChild){
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        
        // Case 3. two children. successor is the smallest node in the right subtree
        else if (current.left != null && current.right != null){
            Node successor = getSuccessor(current);
            if (current == this.root){
                root = successor;
            } else if (isLeftChild){
                parent.left = successor;
            } else {
                parent.right = successor;
            }
        }
        
    }
    
    // returns smallest node in right subtree
    // Current is not null and has 2 children
    public Node getSuccessor(Node deleteThisNode){
        Node successor = null;
        Node successorParent = null;
        Node current = deleteThisNode.right;
        
        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        
        // check if successor has right child (we know it does not have left)
        // if it does, add it to the left of successor parent
        
        if (successor != deleteThisNode.right){
            successorParent.left = successor.right;
            successor.right = deleteThisNode.right;
        }
        
        return successor;
    }
    
    // Tree Traversals
    // Inorder: left, root, right
    public void printInOrder(){
        
    }
    
    // Preorder: root, left, right
    public void printPreOrder(){
        
    }
    
    // Postorder: left, right, root
    public void printPostorder(){
        
    }
    
    // Tree Searching
    public boolean breadthFirstSearch(int val){
        
        return false;
    }
    
    public boolean depthFirstSearch(int val){
        
        return false;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
