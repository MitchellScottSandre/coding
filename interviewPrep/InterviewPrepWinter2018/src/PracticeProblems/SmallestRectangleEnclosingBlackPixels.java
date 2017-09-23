package PracticeProblems;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a
 * black pixel. The black pixels are connected, i.e., there is only one black
 * region. Pixels are connected horizontally and vertically. Given the location
 * (x, y) of one of the black pixels, return the area of the smallest
 * (axis-aligned) rectangle that encloses all black pixels.
 *
 * For example, given the following image:
 *
 * [
 * "0010", "0110", "0100" ] and x = 0, y = 2, Return 6.
 */
public class SmallestRectangleEnclosingBlackPixels {

    //don't visit the same one again (set it to 2)
    //search for all of them
    //keep track of bounds (min and max x and y)
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = -1;
    int maxY = -1;
    
    
    final char VISITED = '2';
    final char BLACK = '1';

    public int minArea(char[][] image, int x, int y) {
        image[x][y] = VISITED;
        if (x < minX){
            minX = x;
        }
        if (x > maxX){
            maxX = x;
        }
        if (y < minY){
            minY = y;
        }
        if (y > maxY){
            maxY = y;
        }
        
        if (x > 0 && image[x - 1][y] == BLACK){ //search up
            minArea(image, x - 1, y);
        }
        
        if (x < image.length - 1 && image[x + 1][y] == BLACK){ // search down
            minArea(image, x + 1, y);
        }
        
        if (y > 0 && image[x][y - 1] == BLACK){ // search left
            minArea(image, x, y - 1);
        }
        
        if (y < image[0].length - 1 && image[x][y + 1] == BLACK){ // search right
            minArea(image, x, y + 1);
        }
        
        return (maxX - minX + 1) * (maxY - minY + 1); 
         
    }

    public static void main(String[] args) {
        SmallestRectangleEnclosingBlackPixels sol = new SmallestRectangleEnclosingBlackPixels();
        char[][] image;
        String r1 = "0000000000000000000000001100111111110000000000000000000000000000000000000000000";
        String r2 = "0000000000000000000000000111111111110000000000000000000000000000000000000000000";
        String r3 = "0000000000000000000000000001111110110000000000000000000000000000000000000000000";
        String r4 = "0000000000000000000000000011111110100000000000000000000000000000000000000000000";
        String r5 = "0000000000000000000000001111111110000000000000000000000000000000000000000000000";
        String[] rows = {r1,r2,r3,r4,r5};
        image = new char[5][r1.length()];
        for (int i = 0; i < rows.length; i++){
            for(int j = 0; j < rows[0].length(); j++){
                image[i][j] = rows[i].charAt(j);
            }
        }
        
        sol.minArea(image, 3, 34);
        
        for (int i = 0; i < rows.length; i++){
            System.out.print("\n");
            for(int j = 0; j < rows[0].length(); j++){
                System.out.print(image[i][j]);
            }
        }
        
    }
    

}
