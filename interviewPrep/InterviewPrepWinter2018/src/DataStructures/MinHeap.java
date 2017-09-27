
package DataStructures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class MinHeap {
    
    // Tree where each node has at most 2 children and each child is larger than its parent
    // Therefore, root is the smallest value
    // To implement, use an ARRAY where 
    //  - index 0 is BAD VALUE
    //  - root is at index 1
    //  - x has a parent at x / 2
    //  - y has a left child at 2y
    //  - y has a right child at 2y + 1
    // To insert, insert at BOTTOM and then just keep switching with parent as needed
    // To remove (min value, at node), remove the node.
    //      Then, replace it with the last value (at the last index, which also == size since first index is empty)
    //      Then, buble down. --> if not a leafNode && val is larger than left or right, then swap it with min(leftChild, rightChild)
    
    private int[] heap;
    private int maxSize;
    private int size;
    public static int FRONT = 1;
    
    public MinHeap(int size){
        this.maxSize = size;
        this.size = 0;
        this.heap = new int[maxSize + 1];
        heap[0] = Integer.MIN_VALUE;
    }
    
    public void insert(int val){
        // insert at bottom
        heap[++size] = val;
        
        // bubble up as needed
        int current = size;
        while (heap[current] < heap[parent(current)]){
            swap(current, parent(current));
            current = parent(current);
        }
    }
    
    public int remove(){
        int popped = heap[FRONT];
        heap[FRONT] = heap[size--]; // replace the top node the last node
        bubbleDown(FRONT); // bubble down to ensure you place that node in the right spot
        return popped;
    }
    
    private void bubbleDown(int index){
        int currIndex = index;
        int leftIndex = left(index);
        int rightIndex = right(index);
        
        if (!isLeaf(currIndex)){
            if (heap[currIndex] > heap[leftIndex] || heap[currIndex] > heap[rightIndex] ){ // if current index (parent) is bigger than one of its children
                if (heap[leftIndex] < heap[rightIndex]){ // replace it with smallest child
                    swap(currIndex, leftIndex);
                    bubbleDown(leftIndex);
                } else {
                    swap(currIndex, rightIndex);
                    bubbleDown(rightIndex);
                }
            }
        }
        
    }
    
    private boolean isLeaf(int index){
        if (index >= size/2 ){
            return true;
        }
        return false;
    }
    
    private void swap(int x, int y){
        int temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }
    
    private int parent(int x){
        return x / 2;
    }
    
    private int left(int x){
        return 2*x;
    }
    
    private int right(int x){
        return 2*x + 1;
    }
    
    public void print(){
        for (int i = 1; i < this.size / 2; i++){
            System.out.println("Parent: " + heap[i] + " --> leftChild: " + heap[left(i)] + ", --> rightChild: " + heap[right(i)]);
        }
    }
    
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        for (int i = 10; i > 0; i--){
            heap.insert(i);
        }
        heap.print();
        
        System.out.println("min: " + heap.remove());
        heap.print();
        System.out.println("min: " + heap.remove());
    }
    
}
