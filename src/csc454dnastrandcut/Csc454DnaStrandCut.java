package csc454dnastrandcut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Gabriel H. Costa
 */
public class Csc454DnaStrandCut {

    private static int[][] M;
    static int[] Array;
    static int cost;
    static int[][] K;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("L: ");
        int l = input.nextInt();
        System.out.println("#Cuts:");
        int c = input.nextInt();

        Array = new int[c + 2];
        for (int i = 1; i < Array.length - 1; i++) {
            System.out.println("Enter each cut:");
            Array[i] = input.nextInt();
        }

        Array[0] = 0;
        Array[Array.length - 1] = l;
        populateArray(Array.length);
        K = new int[Array.length][Array.length];

        int bestCut = cost(0, Array.length - 1);
        System.out.println("Lowest cost = " + bestCut);
        System.out.println("");
        printM();
        System.out.println("");
        printK();
        printCuts();
    }

    public static void populateArray(int size) {
        M = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                M[i][j] = Integer.MAX_VALUE;
            }

        }
    }

    public static int cost(int start, int finish) {
        if (M[start][finish] != Integer.MAX_VALUE) {
            return M[start][finish];
        } else {
            int minimumCost = Integer.MAX_VALUE;
            int ith = Integer.MAX_VALUE;
            for (int i = start + 1; i < finish; i++) {
                int l = Array[finish] - Array[start];
                int currCost = l + cost(start, i) + cost(i, finish);
                if (currCost < minimumCost) {
                    ith = i;
                    minimumCost = currCost;
                }
            }

            if (minimumCost == Integer.MAX_VALUE) {
                minimumCost = 0;
            }
            K[start][finish] = ith;
            M[start][finish] = minimumCost;
            return minimumCost;
        }
    }

    public static void printM() {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == Integer.MAX_VALUE) {
                    System.out.print("\u221E" + " ");
                } else {
                    System.out.print(M[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public static void printK() {

        for (int i = 0; i < K.length; i++) {
            for (int j = 0; j < K.length; j++) {
                if (K[i][j] == Integer.MAX_VALUE) {
                    System.out.print("\u221E" + " ");
                } else {
                    System.out.print(K[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public static void printCuts() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < K.length - 1; i++) {
            for (int j = K[0].length - 1; j > 0; j--) {
                if (K[i][j] != 0 && K[i][j] != Integer.MAX_VALUE) {
                    if (!list.contains(Array[K[i][j]])) {
                        list.add(Array[K[i][j]]);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

}
