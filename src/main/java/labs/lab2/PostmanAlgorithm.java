package labs.lab2;

import labs.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PostmanAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] graph = Graph.fromDataMatrixToIncendentMatrix(Graph.getGraphMatrix("src/main/java/labs/lab2/l2_3.txt"));
        PostmanProblem pp = new PostmanProblem(graph);
        pp.findEulerPath();
        pp.printPath();
    }
}

class PostmanProblem {

    private int[][] graph;
    private int[] degrees;
    private List<Integer> path;

    public PostmanProblem(int[][] graph) {
        this.graph = graph;
        int n = graph.length;
        this.degrees = new int[n];
        this.path = new ArrayList<Integer>();
    }

    public void findEulerPath() {
        int n = graph.length;
        int oddCount = 0;
        int startVertex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] > 0) {
                    degrees[i]++;
                }
            }
            if (degrees[i] % 2 != 0) {
                oddCount++;
                startVertex = i;
            }
        }
        if (oddCount == 2) {
            findEulerPath(startVertex);
        } else {
            findEulerPath(0);
        }
    }

    private void findEulerPath(int u) {
        path.add(u);
        int n = graph.length;
        for (int v = 0; v < n; v++) {
            if (graph[u][v] > 0) {
                graph[u][v]--;
                graph[v][u]--;
                findEulerPath(v);
                break;
            }
        }
    }

    public void printPath() {
        System.out.print("Euler Path: ");
        for (Integer integer : path) {
            System.out.print(1+integer + " ");
        }
    }
}