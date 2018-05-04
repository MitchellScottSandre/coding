package GooglePracticeProblems;

public class LongestUnivaluePath {

//Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        int longestPath = 0;
        
        public int helper(TreeNode root) {
            if (root == null) return 0;
            int leftLength = 0;
            int rightLength = 0;
            if (root.left != null && root.left.val == root.val) {
                leftLength = 1 + helper(root.left);
                if (leftLength > longestPath) {
                    longestPath = leftLength;
                }
            } else {
                helper(root.left);
            }
            
            if (root.right != null && root.right.val == root.val) {
                rightLength = 1 + helper(root.right);
                if (rightLength > longestPath) {
                    longestPath = rightLength;
                }
            } else {
                helper(root.right);
            }
            
            if (root.right != null && root.left != null && root.right.val == root.left.val) {
                if (leftLength + rightLength > longestPath) {
                    longestPath = leftLength + rightLength;
                }
                return Math.max(leftLength, rightLength);
            } else if (root.right != null && root.right.val == root.val) {
                return rightLength;
            } else if (root.left != null && root.left.val == root.val) {
                return leftLength;
            }
            
            return 0;
        }
        
        public int longestUnivaluePath(TreeNode root) {
            helper(root);
            return longestPath;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
