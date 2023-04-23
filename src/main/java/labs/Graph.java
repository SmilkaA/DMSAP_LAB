package labs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Graph {

    private final int V;
    private final List<Edge>[] adj;

    private static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private static class Subset {
        int parent;
        int rank;
    }

    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edge e = new Edge(src, dest, weight);
        adj[src].add(e);
        adj[dest].add(e);
    }


    private int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public void boruvkaMST() {
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        ArrayList<Edge> mst = new ArrayList<>();
        int numTrees = V;
        while (numTrees > 1) {
            Edge[] cheapest = new Edge[V];
            Arrays.fill(cheapest, null);

            for (int v = 0; v < V; v++) {
                for (Edge e : adj[v]) {
                    int set1 = find(subsets, e.src);
                    int set2 = find(subsets, e.dest);
                    if (set1 == set2) {
                        continue;
                    } else {
                        if (cheapest[set1] == null || e.weight < cheapest[set1].weight) {
                            cheapest[set1] = e;
                        }
                        if (cheapest[set2] == null || e.weight < cheapest[set2].weight) {
                            cheapest[set2] = e;
                        }
                    }
                }
            }

            for (int i = 0; i < V; i++) {
                Edge e = cheapest[i];
                if (e != null) {
                    int set1 = find(subsets, e.src);
                    int set2 = find(subsets, e.dest);
                    if (set1 != set2) {
                        mst.add(e);
                        union(subsets, set1, set2);
                        numTrees--;
                    }
                }
            }
        }
        for (Edge e : mst) {
            System.out.println(e.src + " - " + e.dest + ": " + e.weight);
        }
    }

    public static Graph fromFileToGraph(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)));
        int size = scanner.nextInt();
        Graph graph = new Graph(size);
        int[][] data = new int[size][size];
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            for (int i = 0; i < data.length; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    data[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] != 0) {
                    graph.addEdge(i, j, data[i][j]);
                }
            }
        }
        return graph;
    }
}
