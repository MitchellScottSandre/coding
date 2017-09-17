
package PracticeProblems;

public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        
        int i = 0, j = 0; // i is nums1 index, j is nums2 index
        boolean addMiddleTwo = totalLength % 2 == 0;
        int lastNumber = -1, secondLastNumber = -1;
        
        for (int z = 0; z <= totalLength / 2; z++){
            secondLastNumber = lastNumber;
            
            if (i < nums1.length && (j >= nums2.length || nums1[i] <= nums2[j])){
                lastNumber = nums1[i];
                i++;
            } else if (j < nums2.length && (i >= nums1.length || nums2[j] < nums1[i])){
                lastNumber = nums2[j];
                j++;
            }
        }
        
        if (addMiddleTwo){
            return ((double) lastNumber + (double) secondLastNumber) / 2.0;
        } else {
            return (double) lastNumber;
        }
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
    
}
