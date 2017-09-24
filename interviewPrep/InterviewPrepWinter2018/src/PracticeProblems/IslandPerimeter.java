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
public class IslandPerimeter {
    
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        if (grid.length == 1) return grid[0].length * 2 + 2;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] == 1){
                    int p = 0;
                    if (r == 0 && c == 0){ // top left
                        p = 2 + checkRight(grid,r,c) + checkDown(grid,r,c);
                    } else if (r == 0 && c == grid[0].length - 1){//top right
                        p = 2 + checkDown(grid,r,c) + checkLeft(grid,r,c);
                    } else if (r == grid.length - 1 && c == 0){//bottom left
                        p = 2 + checkUp(grid,r,c) + checkRight(grid,r,c);
                    } else if(r == grid.length - 1 && c == grid[0].length - 1){//bottom right
                        p = 2 + checkUp(grid,r,c) + checkLeft(grid,r,c);
                    } else if (c == 0){ // left side
                        p = 1 + checkUp(grid,r,c) + checkRight(grid,r,c) + checkDown(grid,r,c) ;
                    } else if (r == 0){ // top side
                        p = 1 + checkLeft(grid,r,c) + checkRight(grid,r,c) + checkDown(grid,r,c) ;
                    } else if (r == grid.length - 1){ // bottom side
                        p = 1 + checkUp(grid,r,c) + checkRight(grid,r,c) + checkLeft(grid,r,c) ;
                    } else if (c == grid[0].length - 1){ // right side
                        p = 1 + checkUp(grid,r,c) + checkLeft(grid,r,c) + checkDown(grid,r,c) ;
                    } else {
                        p = checkAll(grid,r,c);
                    }
                    sum += p;
                }
            }
        }
        return sum;
    }
    public int checkAll(int[][] grid, int r, int c){
        
        return checkRight(grid,r,c) + checkLeft(grid,r,c) + checkUp(grid,r,c) + checkDown(grid,r,c);
    }
    public int checkRight(int[][] grid, int r, int c){
        if (c == grid[0].length - 1) return 1;
        return grid[r][c + 1] == 0 ? 1 : 0;
    }
    public int checkLeft(int[][] grid, int r, int c){
        if (c == 0) return 1;
        return grid[r][c - 1] == 0 ? 1 : 0;
    }
    public int checkUp(int[][] grid, int r, int c){
        if (r == 0) return 1;
        return grid[r - 1][c] == 0 ? 1 : 0;
    }
    public int checkDown(int[][] grid, int r, int c){
        if (r == grid.length - 1) return 1;
        return grid[r + 1][c] == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        IslandPerimeter sol = new IslandPerimeter();
//        int[][] grid = { {0,1,0,0},
//                         {1,1,1,1},
//                         {0,1,0,0},
//                         {1,1,0,0},};
        int[][] grid = {{1}};
        System.out.println(sol.islandPerimeter(grid));
    }
    
}
