package labs.lab3;

import labs.Graph;

import java.io.FileNotFoundException;

public class SalesmanAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        int distance[][] = Graph.getGraphMatrix("src/main/java/labs/lab3/l3-3.txt");
        boolean[] visitCity = new boolean[distance.length];
        visitCity[0] = true;
        int hamiltonianCycle = Integer.MAX_VALUE;
        hamiltonianCycle = Graph.findHamiltonianCycle(distance, 6, visitCity, 0, 1, 0, hamiltonianCycle);
        System.out.println(hamiltonianCycle);
    }
}