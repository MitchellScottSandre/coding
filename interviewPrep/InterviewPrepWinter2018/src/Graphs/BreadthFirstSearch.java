package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private Queue<Node> queue;
    private static ArrayList<Node> nodes = new ArrayList<>();

    public BreadthFirstSearch() {
        queue = new LinkedList<>();
    }

    public void bfs(int adjacencyMatrix[][], Node node) {
        queue.add(node);
        node.visited = true;
        while (queue.isEmpty() == false) {
            Node curr = queue.remove();
            System.out.println(curr.data + "\t");
            ArrayList<Node> neighbours = findNeighbours(adjacencyMatrix, curr);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && n.visited == false) {
                    queue.add(n);
                    n.visited = true;
                }
            }
        }
    }

    public ArrayList<Node> findNeighbours(int adjacencyMatrix[][], Node x) {
        int nodeIndex = -1;
        ArrayList<Node> neighbours = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) == x) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacencyMatrix[nodeIndex].length; j++) {
                if (adjacencyMatrix[nodeIndex][j] == 1) {
                    neighbours.add(nodes.get(j));
                }
            }
        }

        return neighbours;
    }

    public static void main(String[] args) {
        Node node40 = new Node(40); // A
        Node node10 = new Node(10); // B
        Node node20 = new Node(20); // C
        Node node30 = new Node(30); // D
        Node node60 = new Node(60); // E
        Node node50 = new Node(50); // F
        Node node70 = new Node(70); // G

        nodes.add(node40);
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node60);
        nodes.add(node50);
        nodes.add(node70);
        
        int adjacency_matrix[][] = {
            {0, 1, 1, 0, 0, 0, 0}, // Node 1: 40
            {0, 0, 0, 1, 0, 0, 0}, // Node 2: 10
            {0, 1, 0, 1, 1, 1, 0}, // Node 3: 20
            {0, 0, 0, 0, 1, 0, 0}, // Node 4: 30
            {0, 0, 0, 0, 0, 0, 1}, // Node 5: 60
            {0, 0, 0, 0, 0, 0, 1}, // Node 6: 50
            {0, 0, 0, 0, 0, 0, 0}, // Node 7: 70
        };
        
        System.out.println("The BFS traversal of the graph is ");
        BreadthFirstSearch bfsExample = new BreadthFirstSearch();
        bfsExample.bfs(adjacency_matrix, node40);
    }

}
