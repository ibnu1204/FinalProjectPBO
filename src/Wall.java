import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wall {
    // Kelas ini manyediakan himpunan titik-titik dan konektivitasnya.
    // Wall ini menggunakan koordinat relatif (?) dan baru memetakannya ke default width
    private int width = 800; // default width
    private int height = 800;

    private int[][] nodes;
    private int[][][] edges;
    private int n_nodes = 0, n_edges = 0;

    private double[][] wallsUnitVector; // Menyimpan vektor satuan dari arah-arah dinding
    private int intersectedWallIndex; // index dinding yang dipotong oleh sebuah garis di method isIntersectingWall

    public Wall() {
    }

    public Wall (String path) {
        // Baca file level
        Scanner input = null;
        try {
            input = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Inisialisasi array nodes dan edges
        n_nodes = input.nextInt();
        n_edges = input.nextInt();

        nodes = new int[n_nodes][];
        edges = new int[n_edges][][];
        wallsUnitVector = new double[n_edges][];

        input.nextLine();

        // Isi nodes
        for (int i = 0; i < n_nodes; i++) {
            nodes[i] = new int[] {input.nextInt(), input.nextInt()};
            System.out.println(Arrays.toString(nodes[i]));
        }

        input.nextLine();

        // Isi edges dan wallUnitVector
        for (int i = 0; i < n_edges; i++) {
            int a = input.nextInt(), b = input.nextInt();
            int[][] temp = new int[][] {nodes[a], nodes[b]};

            // edges
            edges[i] = temp;

            // walls unit vector
            int dx, dy; double dist;
            dx = temp[0][0] - temp[1][0]; // n1.x - n2.x
            dy = temp[0][1] - temp[1][1]; // n1.y - n2.y
            dist = Math.pow(dx*dx + dy*dy, 0.5);

            wallsUnitVector[i] = new double[] {dx/dist, dy/dist};
            System.out.println("" + a + " " + b);
        }
        input.close();

    }

    private boolean ccw(int[] A, int[] B, int[] C) { // helper function untuk fungsi intersectionTest
        // A,B,C Adalah sebuah node
        return (C[1]-A[1]) * (B[0]-A[0]) >= (B[1]-A[1]) * (C[0] - A[0]);
    }

    public boolean intersectionTest(int[][] edge1, int[][] edge2) {
        //ccw(A,C,D) != ccw(B,C,D) and ccw(A,B,C) != ccw(A,B,D)
        // A = edge1[0] ; B = edge1[1]
        // C = edge2[0] ; D = edge2[1]
        return (ccw(edge1[0], edge2[0], edge2[1]) != ccw(edge1[1], edge2[0], edge2[1])) &&
                (ccw(edge1[0], edge1[1], edge2[0]) != ccw(edge1[0], edge1[1], edge2[1]));
    }

    public boolean isIntersectingWall(int[][] edge) {
        for (int i = 0 ; i < n_edges; i++) {
            if (intersectionTest(edges[i], edge)) {
                intersectedWallIndex = i;
                return true;
            }
        } return false;
    }

    public int getN_edges() {
        return n_edges;
    }

    public int getN_nodes() {
        return n_nodes;
    }

    public int[][] getEdge(int index) {
        return edges[index];
    }

    public int[] getNode(int index) {
        return nodes[index];
    }

    public double[] getWallUnitVector(int index) {
        return wallsUnitVector[index];
    }

    public int getIntersectedWallIndex() {
        return intersectedWallIndex;
    }
}
