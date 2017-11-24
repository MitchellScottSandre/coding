
package Graphs;

import java.util.ArrayList;
import java.util.List;

// Minimum Spanning Tree
// pick a node
// get unvisited neighbours and add those edges to a list of all not-used edges
// go to lowest weight next node (could be from a different source node) (the SHORTET EDGE)
// remove it from possible edges
// prim_mst // while visited.size() != nodes.size()
//      List<Edge> possibleEdges // add to this,

public class PrimMST {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private List<Vertex> visited;
    private List<Vertex[]> path;
    
    public PrimMST(Graph graph){
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
        this.path = new ArrayList<>();
        this.visited = new ArrayList<>();
    }
    
    public void prim_mst(Vertex start){
        visited.add(start);
        Vertex curr = start;
        List<Edge> possibleEdges = new ArrayList<>();
        while (visited.size() != nodes.size()){
            int shortestPath = Integer.MAX_VALUE;
            Edge shortestEdge = null;
            
            // get neighbours
            List<Vertex> neighbours = getNeighbours(curr);
            
            // add to possible edges
            for(Vertex v : neighbours){
                if (!visited.contains(v)){
                    possibleEdges.add(getEdge(curr, v));
                }
            }
            
            // find min in possible edges to any unvisited node
            for (Edge e : possibleEdges){
                if (!visited.contains(e.getDestination()) && e.getWeight() < shortestPath){
                    shortestEdge = e;
                    shortestPath = e.getWeight();
                }
            }
            System.out.println("curr is: " + shortestEdge.getSource().getName() + "-->" + shortestEdge.getDestination().getName() + ", weight: " + shortestEdge.getWeight());
            
            // add info to path (shortest edge source -> destination)
            Vertex info[] = {shortestEdge.getSource(), shortestEdge.getDestination()};
            path.add(info);
            
            // Go to next unvisited node
            curr = shortestEdge.getDestination();
            possibleEdges.remove(shortestEdge);
            visited.add(curr);
        }
    }
    
    public Edge getEdge(Vertex a, Vertex b){
        for (Edge e : edges){
            if (e.getSource().equals(a) && e.getDestination().equals(b)){
                return e;
            }
        }
        throw new RuntimeException("These vertexes aren't connected");
    }
    
    public List<Vertex> getNeighbours(Vertex n){
        List<Vertex> neighbours = new ArrayList<>();
        for (Edge edge : edges){
            if (edge.getSource().equals(n)){
                neighbours.add(edge.getDestination());
            }
        }
        return neighbours;
    }
    
    public static void main(String[] args) {
        List<Vertex> nodes;
        List<Edge> edges;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        
        for (int i = 0; i < 9; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        
        addLane(nodes, edges, "Edge_0", 0, 1, 10);
        addLane(nodes, edges, "Edge_0", 0, 4, 11);
        addLane(nodes, edges, "Edge_0", 0, 5, 12);
        addLane(nodes, edges, "Edge_0", 0, 6, 7);
        addLane(nodes, edges, "Edge_0", 1, 5, 14);
        addLane(nodes, edges, "Edge_0", 1, 4, 9);
        addLane(nodes, edges, "Edge_0", 1, 3, 15);
        addLane(nodes, edges, "Edge_0", 1, 2, 8);
        addLane(nodes, edges, "Edge_0", 2, 3, 2);
        addLane(nodes, edges, "Edge_0", 2, 4, 2);
        addLane(nodes, edges, "Edge_0", 3, 8, 4);
        addLane(nodes, edges, "Edge_0", 3, 4, 1);
        addLane(nodes, edges, "Edge_0", 7, 8, 3);
        addLane(nodes, edges, "Edge_0", 5, 7, 100);
        addLane(nodes, edges, "Edge_0", 4, 5, 13);
        addLane(nodes, edges, "Edge_0", 5, 6, 7);

        
        Graph graph = new Graph(nodes, edges);
        
        PrimMST prim = new PrimMST(graph);
        prim.prim_mst(nodes.get(0));
        
        List<Vertex[]> path = prim.path;
        for(Vertex[] step : path){
            System.out.println(step[0].getName() + " ->" + step[1].getName());
        }
    }
    
    private static void addLane(List<Vertex> nodes, List<Edge> edges, String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
        Edge lane2 = new Edge(laneId, nodes.get(destLocNo), nodes.get(sourceLocNo), duration );
        edges.add(lane2);
    }
    
}
