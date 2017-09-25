/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class MovingAverage {

    int maxSize;
    int currentTotal;
    List<Integer> nums;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        nums = new ArrayList<>();
        maxSize = size;
        currentTotal = 0;
    }
    
    public double next(int val) {
        if (nums.size() == maxSize){
            System.out.println("remove: " + nums.get(0));
            currentTotal -= nums.get(0);
            nums.remove(0);
            currentTotal += val;
            nums.add(val);
            System.out.println("adding: " + val);
        } else {
            System.out.println("adding...: " + val);
            nums.add(val);
            currentTotal += val;
        }
        return (double)(currentTotal) / (double)(nums.size());
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
