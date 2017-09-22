
package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class TopologicalSort {

    // For a Directed Acyclic Graph (DAG)
    // Pick starting Node
    // Go to each of its neighbours
    // For each node, try to go to its unvisited neighbour nodes
    // Once you have no more unvisited neighbours, add current to stack, return to node that called current
    // If completely no more unvisited neighbours, pick a new node from unvisited ndoes and repeat
    // Once all out of unvisited nodes (and you had added the last node to the stack), then the stack is the topological order
    
    private List<Vertex> visited;
    private List<Vertex> nodes;
    private List<Edge> edges;
    public Stack<Vertex> stack;
    
    public TopologicalSort(Graph g){
        this.edges = g.getEdges();
        this.nodes = g.getVertexes();
        this.visited = new ArrayList<>();
        this.stack = new Stack<>();
    }
    
    public void topological_sort(){
        // once all the ndoes have been added to the stack, we are done!
        while(stack.size() != nodes.size()){
            // find next unvisited node to call
            for (Vertex v : nodes){
                if (!visited.contains(v)){
                    topological_sort(v);
                    break;
                }
            }
        }
    }
    
    public void topological_sort(Vertex v){
        if(visited.contains(v)) return;
        
        // add this node to visited
        visited.add(v);
        
        //find neighbours
        List<Vertex> neighbours = getNeighbours(v);
        
        
        if (neighbours.size() == 0){
            // if no neighbours, add to stack and return
            stack.add(v);
            return;
        } else {
            // for each neighbour, go and call topological_sort for that node
            for(Vertex neighbour : neighbours){
                if (!visited.contains(neighbour)){
                    topological_sort(neighbour);
                }
            }
            
            // once you have called all of the neighbours, add this node to stack and return
            stack.add(v);
            return;
        }
    }
    
    public List<Vertex> getNeighbours(Vertex v){
        List<Vertex> neighbours = new ArrayList<>();
        for(Edge e : this.edges){
            if (e.getSource().equals(v)){
                neighbours.add(e.getDestination());
            }
        }
        return neighbours;
    }

    public static void main(String[] args) {
        List<Vertex> nodes;
        List<Edge> edges;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        
        for (int i = 0; i < 10; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        
        addLane(nodes, edges, "Edge_0", 0, 1, 5); //AB
        addLane(nodes, edges, "Edge_0", 0, 5, 5); //AF
        addLane(nodes, edges, "Edge_0", 1, 7, 5); //BH
        addLane(nodes, edges, "Edge_0", 3, 2, 5); //DC
        addLane(nodes, edges, "Edge_0", 3, 8, 5); //DI
        addLane(nodes, edges, "Edge_0", 3, 4, 5); //DE
        addLane(nodes, edges, "Edge_0", 4, 8, 5); //EI
        addLane(nodes, edges, "Edge_0", 6, 0, 5); //GA
        addLane(nodes, edges, "Edge_0", 6, 1, 5); //GB
        addLane(nodes, edges, "Edge_0", 6, 2, 5); //GC
        addLane(nodes, edges, "Edge_0", 8, 2, 5); //IC
        addLane(nodes, edges, "Edge_0", 9, 4, 5); //JE

        Graph graph = new Graph(nodes, edges);
        
        TopologicalSort sort = new TopologicalSort(graph);
        sort.topological_sort();
        
        while(sort.stack.isEmpty() == false){
            System.out.println(sort.stack.pop().getName());
        }
    }
    
    private static void addLane(List<Vertex> nodes, List<Edge> edges, String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
    
}
