import java.util.*;

public class Main {

    static final int MAX = 20;
    static final int MAX_VALUE = 9999;

    // Queue implementation
    static class Queue {
        int[] data = new int[MAX];
        int head = -1;
        int tail = -1;

        void initQueue() {
            head = -1;
            tail = -1;
        }

        boolean isEmpty() {
            return head == -1;
        }

        boolean isFull() {
            return tail == MAX - 1;
        }

        void enqueue(int u) {
            if (isFull()) {
                System.out.println("Queue is full!");
                return;
            }

            if (isEmpty()) {
                head = 0;
            }
            tail = (tail + 1) % MAX;
            data[tail] = u;
        }

        int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty!");
                return -1;
            }

            int x = data[head];
            if (head == tail) {
                head = tail = -1;
            } else {
                head = (head + 1) % MAX;
            }
            return x;
        }
    }

    // Stack implementation
    static class Stack {
        int[] data = new int[MAX];
        int size = -1;

        void push(int u) {
            data[++size] = u; // Increment size first
        }

        int pop() {
            return data[size--]; // Return the value and then decrement size
        }

        int top() {
            return data[size];
        }

        boolean isEmpty() {
            return size == -1;
        }

        boolean isFull() {
            return size == MAX - 1;
        }
    }

    // List implementation
    static class List {
        int[] data = new int[MAX];
        int size = 0;

        void initList() {
            size = 0;
        }

        void add(int u) {
            data[size++] = u;
        }

        int pop() {
            int u = data[size - 1];
            size--;
            return u;
        }

        void printList() {
            for (int i = 0; i < size; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        boolean getVisitedStatus(int u) {
            return data[u] == 1;
        }

        void addVisited(int u) {
            data[u] = 1;
        }
    }

    // Graph implementation
    static class Graph {
        int vertex;
        int[][] matrix = new int[MAX][MAX];

        void initGraph() {
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    matrix[i][j] = 0; // Initialize all edges to 0 (no connection)
                }
            }
        }

        void initWeightedGraph() {
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    matrix[i][j] = MAX_VALUE; // Initialize all edges to -1 (no connection)
                }
            }
        }

        void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1; // Add undirected edge
        }

        void printGraph() {
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        List neighbors(int u) {
            List list = new List();
            for (int i = 0; i < vertex; i++) {
                if (matrix[u][i] >= 1) {
                    list.add(i); // Add neighbors to the list
                }
            }
            return list;
        }

        int degree(int u) {
            int deg = 0;
            for (int i = 0; i < vertex; i++) {
                if (matrix[u][i] == 1) {
                    deg++;
                }
            }
            return deg;
        }

        void readWeightedGraphFromString(String graphData) {
            Scanner scanner = new Scanner(graphData);
            vertex = scanner.nextInt();
            initWeightedGraph();

            while (scanner.hasNext()) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                matrix[u][v] = w;
            }
        }
    }

    // Dijkstra algorithm
    // Ý tưởng của thuật toán Dijkstra
    // Thuật toán Dijkstra là một thuật toán tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh khác trong đồ thị có trọng số không âm. Ý tưởng chính của thuật toán là sử dụng một hàng đợi ưu tiên để luôn chọn đỉnh có khoảng cách ngắn nhất hiện tại, sau đó cập nhật khoảng cách đến các đỉnh kề của nó.

    // Giải thích cách hoạt động của code
    // 1. Khởi tạo
    // dist: Một mảng lưu trữ khoảng cách ngắn nhất từ đỉnh nguồn đến từng đỉnh khác. Ban đầu, tất cả giá trị đều là MAX_VALUE (vô cực) ngoại trừ đỉnh nguồn có giá trị bằng 0.
    // visited: Một mảng boolean để đánh dấu các đỉnh đã được xử lý.
    // pq (Priority Queue - Hàng đợi ưu tiên): Lưu trữ các cặp {đỉnh, khoảng cách} và ưu tiên lấy ra đỉnh có khoảng cách nhỏ nhất.
    // 2. Chạy vòng lặp chính
    // Lặp qua hàng đợi ưu tiên:
    // Lấy ra đỉnh có khoảng cách nhỏ nhất (u).
    // Nếu đỉnh này đã được thăm rồi, bỏ qua (if (visited[u]) continue;).
    // Đánh dấu u là đã thăm.
    // Duyệt qua tất cả các đỉnh v kề với u:
    // Nếu có đường đi (g.matrix[u][v] != -1) và v chưa thăm (!visited[v]):
    // Tính khoảng cách mới newDist = dist[u] + g.matrix[u][v].
    // Nếu newDist nhỏ hơn dist[v], cập nhật dist[v] và thêm {v, dist[v]} vào hàng đợi ưu tiên.
    // 3. In kết quả
    // Duyệt qua mảng dist, nếu giá trị bằng MAX_VALUE thì in ra "Vertex X is unreachable", ngược lại in ra khoảng cách tương ứng.
    // Tóm tắt cách hoạt động
    // Khởi tạo mảng dist với giá trị vô cực và gán dist[source] = 0.
    // Dùng hàng đợi ưu tiên để lấy ra đỉnh có khoảng cách nhỏ nhất chưa được xử lý.
    // Cập nhật khoảng cách ngắn nhất cho các đỉnh kề của đỉnh đang xét.
    // Lặp lại bước 2 và 3 cho đến khi hàng đợi rỗng.
    // In ra khoảng cách từ đỉnh nguồn đến tất cả các đỉnh khác.
    // ⏳ Độ phức tạp: Với cách triển khai bằng hàng đợi ưu tiên (PriorityQueue), thuật toán có độ phức tạp O(V log V + E log V) ≈ O(E log V), trong đó:

    // V là số đỉnh,
    // E là số cạnh.
    // ✨ Ưu điểm: Tìm đường đi ngắn nhất hiệu quả cho đồ thị có trọng số dương.
    static void dijkstra(Graph g, int source) {
        int[] dist = new int[g.vertex];
        Arrays.fill(dist, MAX_VALUE);
        dist[source] = 0;

        boolean[] visited = new boolean[g.vertex];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0}); // Start with the source vertex

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < g.vertex; v++) {
                if (g.matrix[u][v] != -1 && !visited[v]) {
                    int newDist = dist[u] + g.matrix[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new int[]{v, dist[v]});
                    }
                }
            }
        }

        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < g.vertex; i++) {
            if (dist[i] == MAX_VALUE)
                System.out.println("Vertex " + i + " is unreachable");
            else
                System.out.println("Distance to vertex " + i + " is " + dist[i]);
        }
    }

    public void BellmanFord(Graph g, int source){
        int dist[] = new int[g.vertex];
        Arrays.fill(dist, MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < g.vertex - 1; i++) {
            for (int u = 0; u < g.vertex; u++) {
                for (int v = 0; v < g.vertex; v++) {
                    if (g.matrix[u][v] != MAX_VALUE) {
                        if (dist[u] + g.matrix[u][v] < dist[v]) {
                            dist[v] = dist[u] + g.matrix[u][v];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String graphData = "5\n0 1 10\n0 2 20\n1 2 5\n1 3 50\n2 3 10\n3 4 2"; // Example graph input
        Graph g = new Graph();
        g.readWeightedGraphFromString(graphData);

        int source = 0; // Source vertex
        dijkstra(g, source);
    }
}
