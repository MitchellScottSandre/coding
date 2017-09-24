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
public class CountIslands {

    public char DISCOVERED = '2';
    public char LAND = '1';
        
    public int numIslands(char[][] grid) {
        int sum = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] == LAND){
                    search(grid, r, c);
                    sum++;
                }
            }
        }
        return sum;
    }
    
    public void search(char[][] grid, int r, int c){ //discover entire island
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
        if (grid[r][c] != LAND) return;
        grid[r][c] = DISCOVERED;
        search(grid, r - 1, c); //search up
        search(grid, r + 1, c); //search down
        search(grid, r, c + 1); //search right
        search(grid, r, c - 1); //search left
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
