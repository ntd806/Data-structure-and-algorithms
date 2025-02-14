import java.util.*;

class Graph {
    static final int MAX = 20;
    static final int MAX_VALUE = 9999;
    int vertex;
    int[][] matrix = new int[MAX][MAX];

    // Initialize a graph with no connections
    void initGraph() {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                matrix[i][j] = 0; // Initialize all edges to 0 (no connection)
            }
        }
    }

    // Initialize weighted graph with maximum values (indicating no connection)
    void initWeightedGraph() {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                matrix[i][j] = MAX_VALUE; // Initialize all edges to MAX_VALUE (no connection)
            }
        }
    }

    // Add an edge to the graph
    void addEdge(int u, int v, int w) {
        matrix[u][v] = w;
        matrix[v][u] = w; // Add undirected edge
    }

    // Print the graph's adjacency matrix
    void printGraph() {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j] == MAX_VALUE) {
                    System.out.print("\u221E "); // ∞ symbol for unreachable
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Get all neighbors of a given vertex
    List<Integer> neighbors(int u) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            if (matrix[u][i] < MAX_VALUE) {
                list.add(i); // Add neighbors to the list
            }
        }
        return list;
    }

    // Get the degree of a vertex (number of edges connected to it)
    int degree(int u) {
        int deg = 0;
        for (int i = 0; i < vertex; i++) {
            if (matrix[u][i] < MAX_VALUE) {
                deg++;
            }
        }
        return deg;
    }

    // Read graph data from a string
    void readWeightedGraphFromString(String graphData) {
        Scanner scanner = new Scanner(graphData);
        vertex = scanner.nextInt();
        initWeightedGraph();

        while (scanner.hasNext()) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            addEdge(u, v, w);
        }
    }

    // Dijkstra algorithm to find the shortest path from source vertex
    void dijkstra(int source) {
        int[] dist = new int[vertex];
        Arrays.fill(dist, MAX_VALUE);
        dist[source] = 0;

        boolean[] visited = new boolean[vertex];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0}); // Start with the source vertex

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < vertex; v++) {
                if (matrix[u][v] != MAX_VALUE && !visited[v]) {
                    int newDist = dist[u] + matrix[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new int[]{v, dist[v]});
                    }
                }
            }
        }

        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < vertex; i++) {
            if (dist[i] == MAX_VALUE)
                System.out.println("Vertex " + i + " is unreachable");
            else
                System.out.println("Distance to vertex " + i + " is " + dist[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String graphData = "5\n0 1 10\n0 2 20\n1 2 5\n1 3 50\n2 3 10\n3 4 2"; // Example graph input
        Graph g = new Graph();
        g.readWeightedGraphFromString(graphData);

        int source = 0; // Source vertex
        g.dijkstra(source);
    }
}
