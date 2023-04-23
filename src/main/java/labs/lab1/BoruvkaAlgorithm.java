package labs.lab1;

import labs.Graph;

import java.io.FileNotFoundException;

public class BoruvkaAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = Graph.fromFileToGraph();
        System.out.println("Мінімальне остове дерево:");
        graph.boruvkaMST();
    }
}