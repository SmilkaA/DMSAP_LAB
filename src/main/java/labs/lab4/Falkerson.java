package labs.lab4;

import labs.Graph;

import java.io.FileNotFoundException;
import java.util.*;

public class Falkerson {
    private int[][] graph;
    private int ROW;

    public Falkerson(int[][] graph) {
        this.graph = graph;
        this.ROW = graph.length;
    }

    public boolean bestWay(int s, int t, int[] parent) {
        boolean[] visited = new boolean[ROW];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < ROW; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    queue.add(v);
                    visited[v] = true;
                    parent[v] = u;
                    if (v == t) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int falkerson(int source, int sink) {
        int[] parent = new int[ROW];
        int maxFlow = 0;

        while (bestWay(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            int s = sink;
            while (s != source) {
                pathFlow = Math.min(pathFlow, graph[parent[s]][s]);
                s = parent[s];
            }

            maxFlow += pathFlow;
            int v = sink;
            while (v != source) {
                int u = parent[v];
                graph[u][v] -= pathFlow;
                graph[v][u] += pathFlow;
                v = parent[v];
            }
        }

        return maxFlow;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int data[][] = Graph.getGraphMatrix("src/main/java/labs/lab4/l4_2.txt");

        Falkerson f = new Falkerson(data);
        int source = 0;
        int sink = data.length - 1;
        System.out.println("Max flow is " + f.falkerson(source, sink));
    }
}