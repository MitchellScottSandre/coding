/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * execute(startNode)
 * updateDistancesToNeighbours(Vertex current)
 * distanceBetweenTwoNodes(Vertex source, Vertex dest)
 * getClosestUnvisitedNode(Vertex prev)
 * getShortestDistanceTo(Vertex node)
 * getNeighbours(Vertex node)
 */
public class DijkstraAlgorithm2 {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private List<Vertex> unvisited;
    public Map<Vertex, Integer> distances;
    private Map<Vertex, Vertex> predecessors;
    
    
    public DijkstraAlgorithm2(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
        this.unvisited = this.nodes;
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
    }
    
    public void execute(Vertex start){
        unvisited.remove(start);
        distances.put(start, 0);
        Vertex prev = null;
        Vertex current = start;
        while(unvisited.size() > 0){
            current = prev == null ? start : getClosestUnvisitedNode(prev);
            unvisited.remove(current);
            prev = current;
            
            updateDistancesToNeighbours(current);
        }
        
    }
    
    public void updateDistancesToNeighbours(Vertex current){
        List<Vertex> neighbours = getNeighbours(current);
        for(Vertex neighbour: neighbours){
            if (getShortestDistanceTo(current) + distanceBetweenTwoNodes(current, neighbour) < getShortestDistanceTo(neighbour)){
                distances.put(neighbour, getShortestDistanceTo(current) + distanceBetweenTwoNodes(current, neighbour));
                predecessors.put(neighbour, current);
            }
        }
    }
    
    public Integer distanceBetweenTwoNodes(Vertex source, Vertex dest){
        for (Edge e : this.edges){
            if (e.getSource().equals(source) && e.getDestination().equals(dest)){
                return e.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }
    
    public Vertex getClosestUnvisitedNode(Vertex prev){
        Vertex closest = null;
        for (Vertex v : this.unvisited){
            if (closest == null){
                closest = v;
            } else {
                if (getShortestDistanceTo(v) < getShortestDistanceTo(closest)){
                    closest = v;
                }
            }
        }
        return closest;
    }
    
    public Integer getShortestDistanceTo(Vertex node){
        Integer d = this.distances.get(node);
        if (d == null){
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }
    
    public List<Vertex> getNeighbours(Vertex node){
        List<Vertex> n = new ArrayList<>();
        for (Edge e : this.edges){
            if (e.getSource().equals(node) && unvisited.contains(e.getDestination())){
                n.add(e.getDestination());
            }
        }
        return n;
    }
    
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args) {
        List<Vertex> nodes;
        List<Edge> edges;
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        
        for (int i = 0; i < 6; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        
        addLane(nodes, edges, "Edge_0", 0, 1, 10);
        addLane(nodes, edges, "Edge_1", 0, 2, 5);
        addLane(nodes, edges, "Edge_2", 0, 3, 20);
        addLane(nodes, edges, "Edge_3", 1, 5, 100);
        addLane(nodes, edges, "Edge_4", 1, 4, 50);
        addLane(nodes, edges, "Edge_4", 1, 3, 5);
        addLane(nodes, edges, "Edge_4", 1, 2, 30);
        addLane(nodes, edges, "Edge_4", 3, 4, 20);
        addLane(nodes, edges, "Edge_4", 4, 5, 20);
        
        Graph graph = new Graph(nodes, edges);
        
        DijkstraAlgorithm2 sol = new DijkstraAlgorithm2(graph);
        sol.execute(nodes.get(0));
        
        Map<Vertex, Integer> distances = sol.distances;
        distances.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        });
        
        //get solution, shortest path from 0 to 5
        LinkedList<Vertex> path = sol.getPath(nodes.get(5));
         
        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }
    
    private static void addLane(List<Vertex> nodes, List<Edge> edges, String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
    
}
