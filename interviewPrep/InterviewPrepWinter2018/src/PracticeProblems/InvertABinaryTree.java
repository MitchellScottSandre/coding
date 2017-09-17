package PracticeProblems;

public class InvertABinaryTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null){
            // swap left child with the right child
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
           
        }
        
        return root;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
