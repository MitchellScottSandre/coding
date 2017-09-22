package Graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private final List<Vertex> vertexes;
    private final List<Edge> edges;
    public LinkedList<Integer> adjList[];
    public int numV;

    public Graph(int v) { //number of vertices
        this.numV = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }

        vertexes = null;
        edges = null;
    }
    
    // Depth First Search

    public void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.println(v + " ");

        Iterator<Integer> i = adjList[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    public void DFS(int startIndex) {
        boolean visited[] = new boolean[numV];
        DFSUtil(startIndex, visited);

    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal "
                + "(starting from vertex 2)");

        g.DFS(0);
    }
}
