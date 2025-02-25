// Union-Find (Disjoint Set Union - DSU)
// 1. Cách hoạt động:

// Union-Find là một cấu trúc dữ liệu được sử dụng để quản lý một tập hợp các phần tử được chia thành các tập con không giao nhau.
// Nó hỗ trợ hai thao tác chính:

// Find(x): Tìm đại diện (root) của tập hợp chứa phần tử x.
// → Sử dụng kỹ thuật path compression để tối ưu, giúp rút ngắn đường đi từ một nút đến gốc.

// Union(x, y): Hợp nhất hai tập hợp chứa x và y thành một tập hợp duy nhất.
// → Thường sử dụng union by rank hoặc union by size để giữ cho cây cân bằng, giúp tối ưu hiệu suất.

// Tối ưu hiệu suất:

// Với hai kỹ thuật trên, thời gian thực hiện mỗi thao tác gần như là O(α(n)) (gần như hằng số),
// với α(n) là hàm đảo của hàm Ackermann – một hàm tăng cực chậm.
// 2. Ứng dụng:

// Kiểm tra chu trình trong đồ thị: Dùng để phát hiện chu trình trong đồ thị vô hướng khi thêm cạnh.

// Xác định thành phần liên thông: Giúp kiểm tra xem hai đỉnh thuộc cùng một thành phần liên thông hay không.

// Thuật toán Kruskal: Tìm cây khung nhỏ nhất (Minimum Spanning Tree - MST) trong đồ thị.

// Phân cụm (Clustering): Xác định các nhóm phần tử liên quan trong dữ liệu.

// Vấn đề mạng xã hội: Kiểm tra xem hai người dùng có kết nối trực tiếp hoặc gián tiếp với nhau không.

import java.util.*;

class UnionFind {
	int parent[];
	int rank[];

	UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	int find(int u) {
		if (parent[u] == u) {
			return u;
		}
		return parent[u] = find(parent[u]);
	}

	void union(int u, int v) {
		int pu = find(u);
		int pv = find(v);
		if (pu == pv) {
			return;
		}
		if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else if (rank[pu] > rank[pv]) {
			parent[pv] = pu;
		} else {
			parent[pu] = pv;
			rank[pv]++;
		}
	}
}

// 1. Ứng dụng của đồ thị (Graph Applications)
// Đồ thị là một cấu trúc dữ liệu mạnh mẽ và được sử dụng rộng rãi trong nhiều lĩnh vực khác nhau:

// Mạng máy tính (Computer Networks): Mô hình hóa mạng Internet, nơi các máy tính là đỉnh (vertex) và kết nối giữa chúng là cạnh (edge).

// Hệ thống giao thông (Traffic Networks): Đại diện cho các nút giao thông (đỉnh) và các con đường (cạnh) để tìm đường đi ngắn nhất hoặc phát hiện ùn tắc.

// Mạng xã hội (Social Networks): Mô hình hóa các mối quan hệ giữa người dùng, như bạn bè trên Facebook hoặc người theo dõi trên Twitter.

// Công cụ tìm kiếm (Search Engines): Thuật toán PageRank của Google sử dụng đồ thị để xếp hạng các trang web dựa trên liên kết giữa chúng.

// Tối ưu hóa dòng chảy (Flow Optimization): Giải quyết các bài toán như luồng cực đại (Maximum Flow) trong logistics hoặc quản lý tài nguyên.

// Lập lịch (Scheduling): Dùng trong việc lên lịch công việc hoặc tổ chức sự kiện, như sơ đồ Gantt hoặc đồ thị phụ thuộc công việc.

// 2. Các thuật toán cơ bản trong đồ thị (Basic Graph Algorithms)
// Thuật toán tìm kiếm (Traversal Algorithms):
// DFS (Depth-First Search - Tìm kiếm theo chiều sâu):
// Duyệt các đỉnh theo chiều sâu trước khi quay lại nhánh khác.
// Ứng dụng: Kiểm tra tính liên thông, phát hiện chu trình trong đồ thị.

// BFS (Breadth-First Search - Tìm kiếm theo chiều rộng):
// Duyệt các đỉnh theo từng lớp, từ gần đến xa.
// Ứng dụng: Tìm đường đi ngắn nhất trong đồ thị vô hướng không trọng số.

// Thuật toán tìm đường đi ngắn nhất (Shortest Path Algorithms):
// Dijkstra:
// Tìm đường đi ngắn nhất từ một đỉnh đến tất cả các đỉnh khác trong đồ thị có trọng số không âm.

// Bellman-Ford:
// Xử lý được cả trọng số âm, nhưng chậm hơn Dijkstra.

// Floyd-Warshall:
// Tìm đường đi ngắn nhất giữa tất cả các cặp đỉnh trong đồ thị (sử dụng lập trình động).

// Thuật toán tìm cây khung nhỏ nhất (Minimum Spanning Tree - MST):
// Kruskal:
// Sắp xếp cạnh theo trọng số và thêm vào cây nếu không tạo chu trình.

// Prim:
// Mở rộng cây từ một đỉnh bằng cách thêm các cạnh nhỏ nhất kết nối với cây hiện tại.

// Thuật toán luồng cực đại (Maximum Flow):
// Ford-Fulkerson:
// Tìm luồng cực đại từ nguồn đến đích bằng cách tăng dần luồng theo các đường tăng cường.

// Edmonds-Karp:
// Là cải tiến của Ford-Fulkerson, sử dụng BFS để tìm đường tăng cường ngắn nhất.

// Các thuật toán phát hiện chu trình (Cycle Detection):
// Union-Find:
// Kiểm tra xem có chu trình trong đồ thị hay không, chủ yếu áp dụng cho đồ thị vô hướng.

// DFS:
// Dùng trong đồ thị có hướng để phát hiện chu trình dựa trên việc đánh dấu đỉnh.

// Thuật toán tô màu đồ thị (Graph Coloring):
// Gán màu cho các đỉnh sao cho không có hai đỉnh kề nhau cùng màu.
// Ứng dụng: Lập lịch thi, phân bổ tần số vô tuyến.

// 3. Phân loại đồ thị (Graph Types):
// Đồ thị vô hướng (Undirected Graph): Cạnh không có hướng, ví dụ: mạng xã hội.
// Đồ thị có hướng (Directed Graph - DAG): Cạnh có hướng, ví dụ: đồ thị phụ thuộc công việc.
// Đồ thị có trọng số (Weighted Graph): Các cạnh mang trọng số, ví dụ: bản đồ giao thông với độ dài quãng đường.
// Đồ thị đầy đủ (Complete Graph): Mỗi cặp đỉnh đều được nối với nhau.
// Đồ thị không chu trình (Acyclic Graph): Không chứa chu trình, ví dụ: cây, DAG.
// Đồ thị liên thông (Connected Graph): Mỗi đỉnh đều kết nối với ít nhất một đỉnh khác.
// Đồ thị không liên thông (Disconnected Graph): Có ít nhất một đỉnh không kết nối với đỉnh nào khác.

class Graph {
	static final int MAX = 20;
	static final int MAX_VALUE = 9999;  // Represents no connection (infinity)
	int vertex;
	int[][] matrix = new int[MAX][MAX];

	Graph(int[][] matrix, int vertex) {
		this.vertex = vertex;
		this.matrix = matrix;
	}

	// Initialize a graph with no connections
	void initGraph() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				matrix[i][j] = MAX_VALUE; // -1 indicates no edge
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
	void addEdge(int u, int v, int w, boolean directed) {
		matrix[u][v] = w;
		if (!directed) {
			matrix[v][u] = w; // Add undirected edge
		}
	}

	// Print the graph's adjacency matrix
	void printGraph() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				if (matrix[i][j] == MAX_VALUE) {
					System.out.print("\u221E "); // Symbol for unreachable
				} else {
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	void printGraphData(String graphData) {
		System.out.println("Graph data:");
		System.out.println(graphData);
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
			addEdge(u, v, w, false);
		}
	}

	void BFS(int start) {
		boolean[] visited = new boolean[vertex];
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int u = queue.poll();
			System.out.print(u + " ");
			for (int v = 0; v < vertex; v++) {
				if (matrix[u][v] != MAX_VALUE && !visited[v]) {
					visited[v] = true;
					queue.add(v);
				}
			}
		}
	}

	void DFS(int start) {
		boolean[] visited = new boolean[vertex];
		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int u = stack.pop();
			if (!visited[u]) {
				visited[u] = true;
				System.out.print(u + " ");
				for (int v = 0; v < vertex; v++) {
					if (matrix[u][v] != MAX_VALUE && !visited[v]) {
						stack.push(v);
					}
				}
			}
		}
	}

	// Dijkstra algorithm to find the shortest path from source vertex
	void dijkstra(int source) {
		int[] dist = new int[vertex];
		Arrays.fill(dist, MAX_VALUE);
		dist[source] = 0;

		int[] path = new int[vertex];
		for (int i = 0; i < vertex; i++) {
			path[i] = -1;
		}
		path[source] = source;

		boolean[] visited = new boolean[vertex];
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.add(new int[] {source, 0}); // Start with the source vertex

		while (!pq.isEmpty()) {
			int u = pq.poll()[0];
			if (visited[u]) continue;
			visited[u] = true;

			for (int v = 0; v < vertex; v++) {
				if (matrix[u][v] != MAX_VALUE && !visited[v]) {
					int newDist = dist[u] + matrix[u][v];
					if (newDist < dist[v]) {
						dist[v] = newDist;
						pq.add(new int[] {v, dist[v]});
						path[v] = u;
					}
				}
			}
		}

		System.out.println("Shortest paths from vertex " + source + ":");
		for (int i = 0; i < vertex; i++) {
			if (dist[i] != MAX_VALUE) {
				System.out.print("Path to " + i + ": ");
				printPath(path, i);
				System.out.println(" with distance: " + dist[i]);
			}
		}
	}

	void bellmanFord(int source) {
		int[] dist = new int[vertex];
		Arrays.fill(dist, MAX_VALUE);
		dist[source] = 0;

		int[] path = new int[vertex];
		for (int i = 0; i < vertex; i++) {
			path[i] = -1;
		}
		path[source] = source;

		for (int i = 0; i < vertex - 1; i++) {
			for(int u = 0; u < vertex; u++) {
				for(int v = 0; v < vertex; v++) {
					if(matrix[u][v] != MAX_VALUE) {
						int newDist = dist[u] + matrix[u][v];
						if(newDist < dist[v]) {
							dist[v] = newDist;
							path[v] = u;
						}
					}
				}
			}
		}

		for(int u = 0; u < vertex; u++) {
			for(int v = 0; v < vertex; v++) {
				if(matrix[u][v] != MAX_VALUE) {
					int newDist = dist[u] + matrix[u][v];
					if(newDist < dist[v]) {
						System.out.println("Graph contains negative cycle");
						return;
					}
				}
			}
		}
	}

	// Helper method to print the path
	private void printPath(int[] path, int vertex) {
		if (vertex == -1) {
			System.out.print("No path"); // If the path is invalid (unreachable)
			return;
		}
		if (path[vertex] == vertex) {
			System.out.print(vertex + " "); // Base case: if vertex is the source itself
			return;
		}
		printPath(path, path[vertex]); // Recursively print the path
		System.out.print(vertex + " ");
	}

	void printMST(int parent[], int graph[][])
	{
		System.out.println("Edge \tWeight");
		for (int i = 1; i < vertex; i++)
			System.out.println(parent[i] + " - " + i + "\t"
			                   + graph[parent[i]][i]);
	}

	void prim(int source) {
		int parent[] = new int [vertex];
		int key[] = new int [vertex];
		Arrays.fill(key, MAX_VALUE);
		key[source] = 0;
		parent[source] = -1;
		boolean[] mstSet = new boolean[vertex];

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.add(new int[] {source, 0});

		while(!pq.isEmpty()) {
			int u = pq.poll()[0];
			mstSet[u] = true;

			for(int v = 0; v < vertex; v++) {
				int weight = matrix[u][v];

				if(weight != MAX_VALUE && !mstSet[v] && weight < key[v]) {
					key[v] = weight;
					parent[v] = u;
					pq.add(new int[] {v, key[v]});
				}
			}
		}

		printMST(parent, matrix);
	}

    // Hàm lấy danh sách các cạnh mà không trùng và sắp xếp theo trọng số tăng dần
    List<int[]> getEgde() {
        Set<String> visited = new HashSet<>();  // Sử dụng Set để đảm bảo không trùng
        List<int[]> edges = new ArrayList<>();  // Lưu các cặp cạnh

        for (int i = 0; i < vertex; i++) {
            for (int j = i + 1; j < vertex; j++) {  // Chỉ kiểm tra một chiều (i, j), không kiểm tra lại (j, i)
                if (matrix[i][j] != MAX_VALUE && matrix[i][j] != -1) {
                    String edgeKey = i + "-" + j;
                    if (!visited.contains(edgeKey)) {
                        visited.add(edgeKey);  // Đánh dấu cặp cạnh đã được duyệt
                        edges.add(new int[]{i, j, matrix[i][j]});
                    }
                }
            }
        }
        
        // Sắp xếp các cạnh theo trọng số tăng dần
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] edge1, int[] edge2) {
                return Integer.compare(edge1[2], edge2[2]); // So sánh trọng số của 2 cạnh
            }
        });

        return edges;
    }

    // In các cạnh ra màn hình
    void prinEgde(List<int[]> edges) {
        for (int[] edge : edges) {
            System.out.println(edge[0] + " " + edge[1] + " " + edge[2]);
        }
    }

    void kruskal() {
        List<int[]> edges = getEgde();
        UnionFind uf = new UnionFind(vertex);
        List<int[]> mst = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (uf.find(u) != uf.find(v)) {
                mst.add(new int[]{u, v, w});
                uf.union(u, v);
            }
        }

        System.out.println("MST:");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " " + edge[1] + " " + edge[2]);
        }
    }

	// Topological Sorting by Kahn's algorithm
	// Topological Sorting (sắp xếp topo) là một kỹ thuật sắp xếp các đỉnh của đồ thị có hướng sao cho với mỗi cạnh có hướng 𝑢→𝑣
	// đỉnh 𝑢 sẽ xuất hiện trước đỉnh 𝑣 rong thứ tự sắp xếp. Đây là một công cụ quan trọng để xử lý các bài toán phụ thuộc thứ tự, chẳng hạn như:
	// Quản lý lịch trình công việc: Sắp xếp các tác vụ theo thứ tự mà một số tác vụ phải được hoàn thành trước tác vụ khác.
	// Biên dịch mã nguồn: Xác định thứ tự biên dịch các module có phụ thuộc lẫn nhau.
	// Giải quyết bài toán phụ thuộc học phần: Xác định thứ tự học các môn học sao cho các môn yêu cầu tiên quyết được học trước.
	void topologicalKahn() {
		// Bước 1: Tính độ vào (in-degree) của mỗi đỉnh trong đồ thị, tức số lượng cạnh hướng vào mỗi đỉnh.
		int indegree[] = new int[vertex];
		for(int i = 0; i < vertex; i++) {
			for(int j = 0; j < vertex; j++) {
				if(matrix[i][j] != MAX_VALUE) {
					indegree[j]++;
				}
			}
		}
		// Bước 2: Tìm tất cả các đỉnh có độ vào bằng 0 (không có đỉnh nào trỏ đến chúng). Đây là những đỉnh có thể được xếp đầu tiên.
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < vertex; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}

		// Bước 3: Thêm các đỉnh này vào một hàng đợi (queue) và vào danh sách kết quả.
		int count = 0;
		List<Integer> topOrder = new ArrayList<>();
		while(!queue.isEmpty()) {
			int u = queue.poll();
			topOrder.add(u);

			// Bước 4: Trong khi hàng đợi không rỗng, thực hiện:
			// Lấy một đỉnh ra khỏi hàng đợi.
			// Giảm độ vào của tất cả các đỉnh kề với đỉnh vừa lấy ra.
			// Nếu sau khi giảm, một đỉnh nào đó có độ vào bằng 0, thêm đỉnh đó vào hàng đợi.
			for(int v = 0; v < vertex; v++) {
				if(matrix[u][v] != MAX_VALUE) {
					if(--indegree[v] == 0) {
						queue.add(v);
					}
				}
			}
			count++;
		}

		// Bước 5: Quá trình kết thúc khi hàng đợi rỗng.
		// Nếu danh sách kết quả chứa tất cả các đỉnh của đồ thị, ta đã có thứ tự topo hợp lệ;
		// nếu không, đồ thị chứa chu trình và không thể sắp xếp topo.
		if(count != vertex) {
			System.out.println("There exists a cycle in the graph");
			return;
		}

		System.out.println("Topological order:");
		for(int i : topOrder) {
			System.out.print(i + " ");
		}
	}
}

public class Main {
	public static void main(String[] args) {
		int graph[][] = {
			{ 9999, 2, 9999, 6, 9999 },
			{ 2, 9999, 3, 8, 5 },
			{ 9999, 3, 9999, 9999, 7 },
			{ 6, 8, 9999, 9999, 9 },
			{ 9999, 5, 7, 9, 9999 }
		};
		Graph g = new Graph(graph, graph.length);
		g.printGraph();
		g.prinEgde(g.getEgde());
	}
}
