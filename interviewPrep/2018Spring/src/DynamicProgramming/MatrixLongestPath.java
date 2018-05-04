
package DynamicProgramming;

/**
 * Mitchell Scott Sandre
 * 
 * Given an n x n matrix, find the longest path where you can only move up, down, left,right, and the number you move to must be 1 higher
 */
public class MatrixLongestPath {
    
    public static int longestPath = 0;
    public static int longestPath(int[] savedData, int[][] matrix, int r, int c) {
        System.out.println(r + " .. " + c);
        if (r >= matrix.length || c >= matrix.length) return 0;
        int val = matrix[r][c];
        
        // First, check lookup table if it exists already
        if (savedData[val - 1] != -1) return savedData[val - 1] ;
        
        // Else, look up, left, down, right
        int pathLength = 0;
        if (r > 0 && matrix[r - 1][c] == val + 1) {
            System.out.println("going up");
            pathLength = 1 + longestPath(savedData, matrix, r - 1, c);
        } else if (c > 0 && matrix[r][c - 1] == val + 1) {
            System.out.println("going left");
            pathLength = 1 + longestPath(savedData, matrix, r, c - 1);
        } else if (c < matrix.length - 1 && matrix[r][c + 1] == val + 1) {
            System.out.println("going right");
            pathLength = 1 + longestPath(savedData, matrix, r, c + 1);
        } else if (r < matrix.length - 1 && matrix[r + 1][c] == val + 1) {
            System.out.println("going down");
            pathLength = 1 + longestPath(savedData, matrix, r + 1, c);
        }
        
        // Update the lookup table
        savedData[val - 1] = pathLength;
        
        // Check if new longest path
        if (pathLength > longestPath) {
            longestPath = pathLength;
        }
        
        // Now, move on to next cell to check all paths from that cell
        c++;
        if (c == matrix.length) {
            c = 0;
            r++;
        }
        System.out.println("DONE, trying new cell " + r + " .. " + c);
        return longestPath(savedData, matrix, r, c);
    }

    public static void main(String[] args) {
        int[] data = new int [9];
        for (int i = 0; i < data.length; i ++) {
            data[i] = -1;
        }
        int  mat[][] = { {1, 2, 9},
                         {5, 3, 8},
                         {4, 6, 7} };
        
        System.out.println("Length of the longest path is " + longestPath(data, mat, 0, 0));
        System.out.println(longestPath);
    }

}
