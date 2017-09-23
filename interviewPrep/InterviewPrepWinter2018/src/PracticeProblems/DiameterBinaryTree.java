package PracticeProblems;

/**
 *
 * @author scott
 */
public class DiameterBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }
    
    public int helper(TreeNode root){
        int lengthL = root.left == null ? 0 : helper(root.left);
        int lengthR = root.right == null ? 0 : helper(root.right);
        if (lengthL + lengthR > max){
            max = lengthL + lengthR;
        }
        return Integer.max(lengthL, lengthR) + 1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
