/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
package PracticeProblems;

public class ZigZagConversion {
    
    public static String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        int length = s.length();
        int numCols = 0;
        char NULL = '\u0000';

        // find out number of cols
        while (true){
            //vertical
            numCols++;
            length -= numRows;
            
            if (length <= 0) break;
            
            //diagonal
            for (int i = 0; i < numRows - 2; i++){
                numCols++;
                length--;
                if (length <= 0) break;
            }
            
            if (length <= 0) break;
        }
        
        // create 2d table
        char[][] table = new char[numRows][numCols];
        
        boolean goDown = true;
        int r = -1, c = 0;
        for(int i = 0; i < s.length(); i++){
            // move in proper direction
            if (goDown){
                r++;
            } else {
                r--;
                c++;
            }
            
            // place the character
            table[r][c] = s.charAt(i);
            
            // check direction
            if (goDown && r == numRows - 1){
                goDown = false;
            } else if (!goDown && r == 0){
                goDown = true;
            }
        }
        
        // iterate through table, when not NULL add to string
        String answer = "";
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                if (table[i][j] != NULL){
                    answer += table[i][j];
                }
            }
        }
        
        
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 2));
    }
    
}
