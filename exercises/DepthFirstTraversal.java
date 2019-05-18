/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepthFirstTraversal {

    public static void DepthFirstTraversal(Graph graph, int v, boolean[] discovered) {
        discovered[v] = true;

        System.out.print(v + " ");

        for (int u : graph.adjList.get(v)) {
            if (!discovered[u]) {
                DepthFirstTraversal(graph, u, discovered);
            }
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2), new Edge(1, 7), new Edge(1, 8),
                new Edge(2, 3), new Edge(2, 6), new Edge(3, 4),
                new Edge(3, 5), new Edge(8, 9), new Edge(8, 12),
                new Edge(9, 10), new Edge(9, 11)
        );
        
        
        // add 2 because 0 node is unconnected.
        int size = edges.size() + 2;

        Graph graph = new Graph(edges, size);

        boolean[] discovered = new boolean[size];

        for (int i = 0; i < size; i++) {
            if (!discovered[i]) {
                DepthFirstTraversal(graph, i, discovered);
            }
        }
    }
}

class Edge {

    int source, dest;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
};

class Graph {

    List<List<Integer>> adjList = null;

    Graph(List<Edge> edges, int N) {
        adjList = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.size(); i++) {
            int src = edges.get(i).source;
            int dest = edges.get(i).dest;

            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}
