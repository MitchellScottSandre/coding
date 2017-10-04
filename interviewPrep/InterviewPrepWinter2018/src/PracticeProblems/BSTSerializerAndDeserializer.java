/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author scott
 */
public class BSTSerializerAndDeserializer {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String answer = "IMPOSSIBLE";
        if (root != null){
            answer += "," + root.val;
            answer = serializeMe(root, answer);
        }
        return answer;
    }
    
    public String serializeMe(TreeNode root, String curr){
        String s = "";
        s += root.left == null ? ",NULL" : "," + root.left.val;
        s += root.right == null ? ",NULL" : "," + root.right.val;
        
        if (root.left != null){
            s =serializeMe(root.left, s);
        }
        if (root.right != null){
            s = serializeMe(root.right, s);
        }
        return curr + s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root = null;
        List<String> list = (List<String>) Arrays.asList(data.split(","));
        for (int i = 0; i < list.size() / 2; i ++){
            
        }
        return null;
    }
    
//    public TreeNode deserialize(String data) {
//        TreeNode root = null;
//        List<String> list = (List<String>) Arrays.asList(data.split(","));
//        for (int i = 1; i < list.size() / 2; i++){
//            String val = list.get(i);
//            if (!val.equals("NULL")){
//                TreeNode parent = new TreeNode(Integer.parseInt(val));
//                if (i == 1){
//                    root = parent;
//                }
//                String left = list.get(i * 2);
//                String right = list.get(i * 2 + 1);
//                System.out.println(left + " .... " + right);
//                TreeNode leftChild = null;
//                TreeNode rightChild = null;
//                if (!left.equals("NULL")){ 
//                    leftChild = new TreeNode(Integer.parseInt(left));
//                    System.out.println(parent.val + " --> " + left);
//                    parent.left = leftChild;
//                }
//                if (!right.equals("NULL")){ 
//                    rightChild = new TreeNode(Integer.parseInt(right));
//                    System.out.println(parent.val + " --> " + right);
//                    parent.right = rightChild;
//                }
//                
////                if (parent.right != null) System.out.println(parent.right.val);
//            }
//        }
//        
//        return root;
//    }
    
    public static void printInOrder(TreeNode root){
        if (root == null) return;
        printInOrder(root.left);
        System.out.println(root.val);
        if (root.right != null) System.out.println("xxxx" + root.right.val);
        printInOrder(root.right);
    }
    
    public static void main(String[] args) {
        BSTSerializerAndDeserializer sol = new BSTSerializerAndDeserializer();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        String ser = sol.serialize(a);
        System.out.println(ser);
        
        TreeNode root = sol.deserialize(ser);
        printInOrder(root);
    }
    
}
