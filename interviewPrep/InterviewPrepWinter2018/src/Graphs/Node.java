
package Graphs;

import java.util.ArrayList;
import java.util.List;


public class Node {
    int data;
    List<Node> neighbours; //neighbouring nodes that this node points to
    boolean visited = false;
    
    public Node(){
        neighbours = new ArrayList<>();
    }
    
    public Node(int data){
        this.data = data;
    }
    
    public Node(List<Node> neighbours){
        this.neighbours = neighbours;
    }
    
}
