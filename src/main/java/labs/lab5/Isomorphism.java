package labs.lab5;

import labs.Graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Isomorphism {
    public static boolean isIsomorphic(int[][] g1, int[][] g2) {
        int n = g1.length;
        for (int[] p : permutations(n)) {
            int[][] g1_perm = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g1_perm[i][j] = g1[p[i]][p[j]];
                }
            }
            if (Arrays.deepEquals(g1_perm, g2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[][] graph1 = Graph.getGraphMatrix("src/main/java/labs/lab5/l5_1.txt");
        int[][] graph2 = Graph.getGraphMatrix("src/main/java/labs/lab5/l5_2.txt");
        int[][] graph3 = Graph.getGraphMatrix("src/main/java/labs/lab5/l5_3.txt");

        System.out.println("1 and 2 isomorphic: " + isIsomorphic(graph1, graph2));
        System.out.println("1 and 3 isomorphic: " + isIsomorphic(graph1, graph3));
        System.out.println("2 and 3 isomorphic: " + isIsomorphic(graph2, graph3));
    }

    public static ArrayList<int[]> permutations(int n) {
        ArrayList<int[]> perms = new ArrayList<>();
        if (n == 1) {
            int[] perm = {0};
            perms.add(perm);
        } else {
            ArrayList<int[]> prev = permutations(n - 1);
            int[] perm = new int[n];
            for (int i = 0; i < prev.size(); i++) {
                for (int j = 0; j < n; j++) {
                    int[] p = prev.get(i);
                    for (int k = 0; k < n - 1; k++) {
                        if (k < j) {
                            perm[k] = p[k];
                        } else {
                            perm[k + 1] = p[k];
                        }
                    }
                    perm[j] = n - 1;
                    perms.add(perm.clone());
                }
            }
        }
        return perms;
    }
}