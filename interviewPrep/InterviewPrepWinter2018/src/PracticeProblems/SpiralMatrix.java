
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix.length == 0) return list;
        int level = 0;
        int top = matrix[0].length; 
        int right = matrix.length - 1;
        int bottom = top - 1;
        int left = right - 1;
        int row = 0;
        int col = 0;
        
        while(true){
            for(int i = 0; i < top; i++){
                list.add(matrix[row][col]);
                col++;
            }
            col--;
            row++;
            if (list.size() == matrix.length * matrix[0].length) break;
            for(int i = 0; i < right; i++){
                list.add(matrix[row][col]);
                row++;
            }
            row--;
            col--;
            if (list.size() == matrix.length * matrix[0].length) break;
            for(int i = 0; i < bottom; i++){
                list.add(matrix[row][col]);
                col--;
            }
            col++;
            row--;
            if (list.size() == matrix.length * matrix[0].length) break;
            for(int i = 0; i < left; i++){
                list.add(matrix[row][col]);
                row--;
            }
            col++;
            row++;
            top-= 2;
            right -=2;
            bottom -=2;
            left -=2;
        }
        return list;
    }

    public static void main(String[] args) {
        SpiralMatrix sol = new SpiralMatrix();
        int[][] data = 
        {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12}
        };
        List<Integer> l = sol.spiralOrder(data);
        for (int x : l){
            System.out.println(x);
        }
    }
    
}
