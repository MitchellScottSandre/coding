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
public class CountPaths {
    public static int BLOCKED = 1;
    int OPEN = 0;
    //can only move left or right
    public static int numWaysToEnd(int[][] grid, int r, int c, int[][] paths){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;
        if (isAtEnd(grid,r,c)) return 1;
        if (grid[r][c] == BLOCKED) return 0;
        if (paths[r][c] == -1){
            paths[r][c] = numWaysToEnd(grid, r + 1, c, paths) + numWaysToEnd(grid,r, c + 1,paths);
        }
        return paths[r][c];
    }
    
    public static boolean isAtEnd(int[][] grid, int r, int c){
        return r == grid.length - 1 && c == grid[0].length - 1;
    }
    public static void main(String[] args) {
        int data[][] = {{0,0,1,0,1,0},
                        {0,0,0,0,0,0},
                        {0,1,1,1,0,0},
                        {0,0,0,0,1,0},
                        {0,0,1,0,0,0}};
        
        int[][] paths = new int[5][6];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                paths[i][j] = -1;
            }
        }
        
        System.out.println(numWaysToEnd(data, 0, 0, paths));
    }
    
}
