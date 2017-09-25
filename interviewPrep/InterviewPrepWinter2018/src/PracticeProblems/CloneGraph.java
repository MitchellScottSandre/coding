/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UndirectedGraphNode {

    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

public class CloneGraph {
    Set<UndirectedGraphNode> visited = new HashSet<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }
    
    public UndirectedGraphNode clone(UndirectedGraphNode node){
        if (visited.contains(node)) return node;
        visited.add(node);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label); // clone it
        for(UndirectedGraphNode n : clone.neighbors){ // depth first search
            clone(n);
        }
        return clone;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
