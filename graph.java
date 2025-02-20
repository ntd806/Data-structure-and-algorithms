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
				matrix[i][j] = -1; // -1 indicates no edge
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
				if (matrix[u][v] != -1 && !visited[v]) {
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
					if (matrix[u][v] != -1 && !visited[v]) {
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
