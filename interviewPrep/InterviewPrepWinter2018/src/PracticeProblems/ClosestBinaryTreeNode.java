/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class ClosestBinaryTreeNode {

    public int closestValue(TreeNode root, double target) {
        TreeNode curr = root;
        double closestDistance = Double.MAX_VALUE;
        Integer closestNodeVal = null;
        
        while (curr != null){
            Double distance = Math.abs(curr.val - target);
            if (distance < closestDistance){
                closestDistance = distance;
                closestNodeVal = curr.val;
            }
            if (curr.val < target){
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return closestNodeVal;
    }

    public static void main(String[] args) {
        ClosestBinaryTreeNode sol = new ClosestBinaryTreeNode();
        TreeNode a = new TreeNode(1500000000);
        TreeNode b = new TreeNode(1400000000);
        a.left = b;
        System.out.println(sol.closestValue(a, -1500000000));
    }

}
