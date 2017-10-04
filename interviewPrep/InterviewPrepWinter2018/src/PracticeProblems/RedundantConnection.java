/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

/**
 *
 * @author scott
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];
        int[] answer = new int[2];
        
        for (int i = 1; i < parents.length; i++){ // set each node's parent to be itself
            parents[i] = i;
        }
        
        for (int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            
            int parentA = findParent(parents, a);
            int parentB = findParent(parents, b);

            if (parentA == parentB){
                answer[0] = a;
                answer[1] = b;
                // keep searching, we want the right-most answer in the array of edges
            } else {
                parents[parentB] = parentA; //make a becomes b's parent
            }
        }
        
        return answer;
    }
    
    public int findParent(int[] parents, int nodeVal){
        if (parents[nodeVal] == nodeVal) return nodeVal;
        
        return findParent(parents, parents[nodeVal]);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
