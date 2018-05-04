
package GooglePracticeProblems;

// Given a stream of integers and a window size, calculate 

import java.util.LinkedList;
import java.util.Queue;

// the moving average of all integers in the sliding window.
public class MovingAverage {
    Queue<Integer> q = new LinkedList<>();
    int maxSize;
    int currentSize = 0;
    double currentTotal = 0;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.maxSize = size;
    }
    
    public double next(int val) {
        if (currentSize < maxSize) {
            currentTotal += val;
            currentSize++;
        } else {
            int x = q.poll();
            currentTotal -= x;
            currentTotal += val;
        }
        
        q.offer(val);
        return (double) currentTotal / (double) currentSize;
    }
    
    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
    
}
