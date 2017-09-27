/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

/**
 *
 * @author scott
 */
public class MaximumDepthBinaryTree {

    int max = 0;

    public int maxDepth(TreeNode root) {
        solve(root, 0);
        return max;
    }

    public void solve(TreeNode node, int currHeight) {
        if (node != null) {
            if (currHeight + 1 > max) {
                max = currHeight + 1;
            }
            solve(node.left, currHeight + 1);
            solve(node.right, currHeight + 1);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
