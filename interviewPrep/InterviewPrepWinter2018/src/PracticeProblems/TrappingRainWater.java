
package PracticeProblems;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/
public class TrappingRainWater {
    
    public static int trap(int[] height) {
        int sum = 0;
        
        for (int i = 0; i < height.length - 2; i++){
            int currHeight = height[i];
            int nextHeight = height[i + 1];
            // if next one is lower than current
            if (currHeight > nextHeight){
                //check if there exists a block of equal or greater height eventually
                int largestHeight = 0;
                int indexNextLargest = -1;
                for (int j = i + 2; j < height.length; j++){
                    
                    if (height[j] > nextHeight && height[j] > largestHeight){
                        largestHeight = height[j];
                        indexNextLargest = j;
                    }
                    
                    //only stop if you get to end of array, or you find height >= currHeight
                    if ((j == height.length - 1 && indexNextLargest != -1) || height[j] >= currHeight){
                        int total = Integer.min(currHeight, largestHeight) * (indexNextLargest - i - 1);
                        //now subtract from total height
                        for(int p = i + 1; p < indexNextLargest; p++){
                            total -= height[p];
                        }
                        sum += total;
                        
                        i = indexNextLargest - 1;
                        break;
                    }
                }
            }
        }
        
        return sum;
    }

    public static void main(String[] args) {
//        int data[] = {0,2,0,1,0,1,0,3,2,0,1,0,3,2,1,0};
        int data[] = {0,4,0,1,0,1,0,1,0,3,0,4};
//          int data[] = {4,9,4,5,3,2};
        System.out.println(trap(data));
    }
    
    /*
                        int largestHeight = -1; //largest height compared to nextHeight
                    int largestHeightIndex = -1;
                    if (height[j] > nextHeight && height[j] > largestHeight){
                        largestHeight = height[j];
                        largestHeightIndex = j;
                    }
                    
                    if (height[j] >= currHeight || j == height.length - 1){
                        //calculate distance between them and multiply by min of their heights (point i, point j)
                        int total = (largestHeightIndex - i - 1) * Integer.min(currHeight, largestHeight);
                        for (int p = i + 1; p < largestHeightIndex; p++){
                            total -= height[p];
                        }
                        sum += total;
                        i = largestHeightIndex - 1;
                        break;
                    }
    */
    
}
