
package PracticeProblems;


public class ContainerWithMostWater {
    
    public static int determineArea(int[] h, int left, int right){
        return Integer.min(h[left], h[right]) * (right - left);
    }
    
    public static int indexOfNextTallest(int[] h, int left, int right, boolean moveRight){
        if (moveRight){
            for (int i = left + 1; i < right; i++){
                if (h[i] > h[left]){
                    return i;
                }
            }
        } else {
            for (int i = right - 1; i > left; i--){
                if (h[i] > h[right]){
                    return i;
                }
            }
        }
        return -1;
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = determineArea(height, left, right);
        boolean MOVE_RIGHT = true;
        boolean MOVE_LEFT = false;
        
        while (left < right){
            if (height[left] < height[right]){
                left = indexOfNextTallest(height, left, right, MOVE_RIGHT);
                if (left == -1) break;
            } else {
                right = indexOfNextTallest(height, left, right, MOVE_LEFT);
                if (right == -1) break;
            }
            int area = determineArea(height, left, right);
            if (area > maxArea){
                maxArea = area;
            }
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1,2,3,4,2,1};
        System.out.println(maxArea(height));
    }
    
}
