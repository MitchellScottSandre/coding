/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// void execute(Vertex source) (iterate through unSettledNodes)
// void findMinimalDistances(Node node) (add to distances map, check neighbors only)
// int getDistance(Vertex a, Vertex b) (distance between vertecies)
// List<Vertex> getNeighbors(Vertex a)
// Vertex getMinimum(Set<Vertex> nodes) (gets passed unsettled nodes)
// int getShortedDistance(Vertex destination) (returns value in destination map, or MAX_INT)

public class DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distances;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distances = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>(); //destinationNode, sourceNode --> keeps track of how you get to different nodes
        distances.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes); // get closest node
            System.out.println("-> settledNode : add " + node.getName());
            settledNodes.add(node);
            System.out.println("-> unSettledNodes : remove " + node.getName());
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                System.out.println("New shortest distance to " + target.getName() + 
                        " : " + (getShortestDistance(node) + getDistance(node, target)) + " < " + getShortestDistance(target));
                distances.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    // returns closest node 
    private Vertex getMinimum(Set<Vertex> vertexes) { // only gets passed Unsettled Nodes
        Vertex minimum = null; // closest vertex
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        System.out.println("getMinimum: " + minimum.getName() + ", d: " + getShortestDistance(minimum));
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distances.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
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
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        
        //get solution, shortest path from 0 to 5
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(5));
         
        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
    }
    
    private static void addLane(List<Vertex> nodes, List<Edge> edges, String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }

}
