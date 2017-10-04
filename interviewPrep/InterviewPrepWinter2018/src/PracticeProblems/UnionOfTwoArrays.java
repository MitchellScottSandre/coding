/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author scott
 */
public class UnionOfTwoArrays {

    public static int[] union1(int[] a, int[] b){
        HashSet<Integer> uniqueNums = new HashSet<>();
        
        for (int x : a){
            uniqueNums.add(x);
        }
        for(int y : b ){
            uniqueNums.add(y);
        }
        
        int[] answer = new int[uniqueNums.size()];
        Iterator<Integer> it  = uniqueNums.iterator();
        int counter = 0;
        while (it.hasNext()){
            answer[counter] = it.next();
            counter++;
        }
        return answer;
    }
    public static void main(String[] args) {
        int[] a = {3,1,9,2,7};
        int[] b = {4,6,2,2,2,2,1,};
        int[] answer = union1(a, b);
        for(int x : answer){
            System.out.println(x);
        }
    }
    
}
