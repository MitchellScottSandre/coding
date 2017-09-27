/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class BalancedBinaryTree {

    /**
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     */
    
    public boolean isBalanced(TreeNode root) {
        if (solve(root, 0) == -1){
            return false;
        } else {
            return true;
        }
    }
    
    public int solve(TreeNode node, int parentHeight){
        if (node == null) return parentHeight;
        int left = solve(node.left, parentHeight + 1);
        int right = solve(node.right, parentHeight + 1);
        if (left == -1 || right == -1 || left > right + 1 || right > left + 1){
            return -1;
        }
        return Integer.max(left, right);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
