/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GooglePracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) return list;
        int total = matrix.length * matrix[0].length;
        
        int row = 0;
        int col = 0;
        int count = 0;
        
        int topBarrier = 0;
        int rightBarrier = matrix[0].length - 1;
        int leftBarrier = 0;
        int bottomBarrier = matrix.length - 1;
        String direction = "RIGHT";
        
        while (count < total) {
            list.add(matrix[row][col]);
            
            // Change direction when row, col meets a corner
            if (row == topBarrier && col == rightBarrier && direction == "RIGHT") { // TOP RIGHT
                direction = "DOWN";
                topBarrier++;
            } else if (row == bottomBarrier && col == rightBarrier && direction == "DOWN") { // BOTTOM RIGHT
                direction = "LEFT";
                rightBarrier--;
            } else if (row == bottomBarrier && col == leftBarrier  && direction == "LEFT") { // BOTTOM LEFT
                direction = "UP";
                bottomBarrier--;
            } else if (row == topBarrier && col == leftBarrier && direction == "UP") { // TOP LEFT
                direction = "RIGHT";
                leftBarrier++;
            }
            
            switch(direction) {
                case "RIGHT": col++; break;
                case "LEFT": col--; break;
                case "DOWN": row++; break;
                case "UP": row--; break;
            }
            count++;
        }
        
        return list;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
