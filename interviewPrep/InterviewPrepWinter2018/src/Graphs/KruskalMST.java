package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Sort all the edges in non-decreasing order of their weight.
 *
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree
 * formed so far. If cycle is not formed, include this edge. Else, discard it.  *
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 */
public class KruskalMST {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    public List<Vertex[]> path;

    private Map<Vertex, Vertex> parents;

    private int V;

    public KruskalMST(Graph graph) {
        this.V = graph.getVertexes().size();
        this.nodes = new ArrayList<>(graph.getVertexes());
        this.edges = new ArrayList<>(graph.getEdges());
        this.path = new ArrayList<>();
        parents = new HashMap<>();
        for (Vertex v : nodes) {
            parents.put(v, v);
        }
    }

    public void kruskal_mst() {
        //sort the edges
        Collections.sort(edges, new Comparator<Edge>() {

            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }

        });

        // until you have found the solution (there will be V - 1 edges in the solution path)
        while (path.size() != V - 1) {
            // get the smallest edge
            Edge smallestEdge = edges.remove(0);
            System.out.println("Smallest Edge: " + smallestEdge.getId() + " : " + smallestEdge.getWeight()
                    + " from " + smallestEdge.getSource().getName() + " to " + smallestEdge.getDestination().getName());

            // check which nodes the edge connects
            Vertex vertexA = smallestEdge.getSource();
            Vertex vertexB = smallestEdge.getDestination();
            
            Vertex parentA = getParent(vertexA);
            Vertex parentB = getParent(vertexB);

            // if the two parents arent the same (don't form a CYCLIC path (circle))
            if (!parentA.equals(parentB)) {
                // add them to the path
                Vertex[] pathSegment = {vertexA, vertexB};
                path.add(pathSegment);

                // join the two paths
                makeUnion(smallestEdge.getSource(), smallestEdge.getDestination());
            }

        }

    }

    // make a's parent becomes b's parent
    public void makeUnion(Vertex a, Vertex b) {
        parents.put(getParent(a), getParent(b));
    }

    public Vertex getParent(Vertex a) {
        Vertex parent = parents.get(a);
        if (parent.equals(a)) {
            return a;
        } else {
            return getParent(parent);
        }
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

        KruskalMST kruskal = new KruskalMST(graph);
        kruskal.kruskal_mst();

        List<Vertex[]> path = kruskal.path;
        for (Vertex[] step : path) {
            System.out.println(step[0].getName() + " ->" + step[1].getName());
        }

    }

    private static void addLane(List<Vertex> nodes, List<Edge> edges, String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

}
