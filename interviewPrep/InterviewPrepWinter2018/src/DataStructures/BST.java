/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import java.util.ArrayDeque;
import java.util.Deque;

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
    
    public void insert(int val){
        Node newNode = new Node(val);
        
        if (this.root == null){
            this.root = newNode;
            return;
        }
        
        Node current = this.root;
        Node parent = null;
        
        while(true){
            parent = current;
            if (val < current.val){
                current = current.left;
                if (current == null){
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null){
                    parent.right = newNode;
                    return;
                }
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
    public void printInOrder(Node currNode){
        if (currNode != null){
            printInOrder(currNode.left);
            System.out.print(currNode.val + ", ");
            printInOrder(currNode.right);
        }
    }
    
    // Preorder: root, left, right
    // Pre Order is DEPTH FIRST (looks deeper before moving on to other siblings)
    public void printPreOrder(Node currNode){
        if (currNode != null){
            System.out.print(currNode.val+ ", ");
            printInOrder(currNode.left);
            printInOrder(currNode.right);
        }
    }
    
    // Postorder: left, right, root
    public void printPostOrder(Node currNode){
        if (currNode != null){
            printInOrder(currNode.left);
            printInOrder(currNode.right);
            System.out.print(currNode.val + ", ");
        }
    }
    
    // Tree Searching
    // Search Level by Level (we explore the breadth -> the full with, first, before going deeper)
    // Can be arranged such that 1. each level is more important that ones below it, and 2. left side is more important than right
    // Approach: 
    // 1. take an empty queue
    // 2. start from the root. insert the root into the queue
    // 3. while queue is not empty: a. extract node from queue and insert all its children into queue. AND b. print extracted node.
    public void breadthFirstSearch(){
        Deque<Node> q = new ArrayDeque<>(); // In an interview just write Queue ? Issue is java 8 only has priority queue, 
                                            // meaning I have to pass it a COMPARABLE for the Node class
        if (this.root == null){
            return;
        }
        
        q.add(this.root);
        while(q.isEmpty() == false){
            Node n = (Node) q.remove();
            System.out.print(n.val + ", ");
            if (n.left != null){
                q.add(n.left);
            }
            if (n.right != null){
                q.add(n.right);
            }
        }
    }
    
    public void depthFirstSearch(){
        
    }
    
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(20);
        b.insert(50);
        b.insert(10);
        b.insert(11);
        b.insert(14);
        b.insert(13);
        b.insert(8);
        b.insert(2);
        b.insert(30);
        b.insert(40);
        b.insert(22);
        b.insert(9);
        System.out.print("\nIn Order: ");
        b.printInOrder(b.root);
        System.out.print("\nPre Order: ");
        b.printPreOrder(b.root);
        System.out.print("\nPost Order: ");
        b.printPostOrder(b.root);
        System.out.println("Breadh first search");
        b.breadthFirstSearch();
    }
    
}
