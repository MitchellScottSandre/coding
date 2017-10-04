/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author scott
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> it;
    private Integer currVal = null, nextVal = null;
    private int currIndex = 0, nextIndex = 0;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        it = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(currIndex == nextIndex){
            nextIndex++;
            currVal = nextVal;
            nextVal = it.next();
        }
        
        return nextVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (currIndex != nextIndex){
            currIndex++;
            return nextVal;
        } else {
            currIndex++;
            nextIndex++;
            return it.hasNext() ? it.next() : null;
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext() || currIndex != nextIndex;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3);
        PeekingIterator sol = new PeekingIterator(list.iterator());
        System.out.println(sol.peek());
        System.out.println(sol.peek());
        System.out.println(sol.next());
        System.out.println(sol.peek());
        System.out.println(sol.peek());
        System.out.println(sol.next());
        System.out.println(sol.peek());
        System.out.println(sol.hasNext());
    }

}
