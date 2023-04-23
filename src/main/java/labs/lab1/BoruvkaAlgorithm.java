package labs.lab1;

import labs.Graph;

import java.io.FileNotFoundException;

public class BoruvkaAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = Graph.fromFileToGraph("src/main/java/labs/lab1/l1_3.txt");
        System.out.println("Мінімальне остове дерево:");
        graph.boruvkaMST();
    }
}