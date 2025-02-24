#include <iostream>
#include <sstream>
#include <string>
#include <queue>
#include <vector>
#include <climits>
#define MAX 20
#define MAX_VALUE 9999
using namespace std;
struct Queue
{
	int data[MAX];
	int head;
	int tail;
};

void initQueue(Queue &q)
{
	q.head = -1;
	q.tail = -1;
}

bool isEmptyQueue(Queue q)
{
	return q.head == -1;
}

bool isFullQueue(Queue q)
{
	return q.tail == MAX - 1;
}

void enqueue(Queue &q, int u)
{
	if (isFullQueue(q))
	{
		cout << "Queue is full!" << endl;
		return;
	}

	if (isEmptyQueue(q)) {
		q.head = 0;
	}
	q.tail = (q.tail + 1) % MAX;
	q.data[q.tail] = u;
}

int dequeue(Queue &q)
{
	if(isEmptyQueue(q)) {
		cout << "Queue is empty!" << endl;
		return -1;
	}

	int x = q.data[q.head];
	if (q.head == q.tail) {
		q.head = q.tail = -1;
	} else {
		q.head = (q.head + 1) % MAX;
	}
	return x;
}

struct Stack {
	int data[MAX];
	int size;
};

void init(Stack &s) {
	s.size = -1;
}

void push(Stack &s, int u) {
	s.data[++s.size] = u;  // Increment size first
}

int pop(Stack &s) {
	return s.data[s.size--];  // Return the value and then decrement size
}

int top(Stack s) {
	return s.data[s.size];
}

bool isEmpty(Stack s) {
	return s.size == -1;
}

bool isFull(Stack s) {
	return s.size == MAX - 1;
}

struct List {
	int data[MAX];
	int size;
};

void initList(List &l) {
	l.size = 0;
}

List initVisited(List &l, int vertexCount) {
	l.size = vertexCount;
	for (int i = 0; i < vertexCount; i++) {
		l.data[i] = 0;  // Initialize visited as false (0)
	}
	return l;
}

void addList(List &l, int u) {
	l.data[l.size++] = u;
}

void addVisited(List &l, int u) {
	l.data[u] = 1;  // Mark the node as visited
}

void printList(List l) {
	for (int i = 0; i < l.size; i++) {
		cout << l.data[i] << " ";
	}
	cout << endl;
}

int popList(List &l) {
	int u = l.data[l.size - 1];
	l.size--;
	return u;
}

int getVisitedStatus(List l, int u) {
	return l.data[u];  // Get the visited status
}

struct Graph {
	int vertex;
	int matrix[MAX][MAX];
};

void initGraph(Graph &g) {
	for (int i = 0; i < g.vertex; i++) {
		for (int j = 0; j < g.vertex; j++) {
			g.matrix[i][j] = 0;  // Initialize all edges to 0 (no connection)
		}
	}
}

void initWeightedGraph(Graph &g) {
	for (int i = 0; i < g.vertex; i++) {
		for (int j = 0; j < g.vertex; j++) {
			g.matrix[i][j] = -1;  // Initialize all edges to -1 (no connection)
		}
	}
}

int adjacent(Graph g, int u, int v) {
	return g.matrix[u][v];
}

void addEdge(Graph &g, int u, int v) {
	g.matrix[u][v] = 1;
	g.matrix[v][u] = 1;  // Add undirected edge
}

void printGraph(Graph g) {
	for (int i = 0; i < g.vertex; i++) {
		for (int j = 0; j < g.vertex; j++) {
			cout << g.matrix[i][j] << " ";
		}
		cout << endl;
	}
}

void createGraph(Graph &g) {
	cout << "Enter number of vertices: ";
	cin >> g.vertex;
	initGraph(g);
}

int degree(Graph g, int u) {
	int deg = 0;
	for (int i = 0; i < g.vertex; i++) {
		if (g.matrix[u][i] == 1) {
			deg++;
		}
	}
	return deg;
}

// Read graph data from a string
void readGraphFromString(const string &graphData, Graph &g) {
	istringstream stream(graphData);
	stream >> g.vertex;  // Read number of vertices
	initGraph(g);

	int u, v;
	while (stream >> u >> v) {
		addEdge(g, u, v);  // Add edges from the string input
	}
}

void readWeightedGraphFromString(const string &graphData, Graph &g) {
	istringstream stream(graphData);
	stream >> g.vertex;  // Read number of vertices
	initWeightedGraph(g);

	int u, v, w;
	while (stream >> u >> v >> w) {
		g.matrix[u][v] = w;  // Add weighted edges from the string input
	}
}

List neighbors(Graph g, int u) {
	List l;
	initList(l);
	for (int i = 0; i < g.vertex; i++) {
		if (g.matrix[u][i] >= 1) {
			addList(l, i);  // Add neighbors to the list
		}
	}
	return l;
}

List DFS(Graph g, int u) {
	List result;
	initList(result);
	List visited;
	initVisited(visited, g.vertex);
	Stack s;
	init(s);
	push(s, u);

	while (!isEmpty(s)) {
		int u = pop(s);
		if (getVisitedStatus(visited, u) == 0) {  // Check if node is unvisited
			addVisited(visited, u);
			addList(result, u);  // Add the node to the result list
			List neighborsList = neighbors(g, u);
			for (int i = 0; i < neighborsList.size; i++) {
				int v = neighborsList.data[i];
				if (getVisitedStatus(visited, v) == 0) {  // If neighbor is unvisited
					push(s, v);
				}
			}
		}
	}

	return result;
}

List dfsRecursiveHelper(Graph g, int u, List &visited, List &result) {
	// Mark the node as visited
	addVisited(visited, u);
	// Add the node to the result list
	addList(result, u);
	// Explore the neighbors
	List neighborsList = neighbors(g, u);

	for (int i = 0; i < neighborsList.size; i++) {
		int v = neighborsList.data[i];
		if (getVisitedStatus(visited, v) == 0) {  // If the neighbor hasn't been visited
			dfsRecursiveHelper(g, v, visited, result);  // Recursively explore the neighbor
		}
	}

	return result;
}

List dfsRecursive(Graph g, int u) {
	List visited;
	initVisited(visited, g.vertex);
	List result;
	initList(result);
	return dfsRecursiveHelper(g, u, visited, result);  // Call the helper function
}

List BFS(Graph g, int u) {
	List result;
	initList(result);
	List visited;
	initVisited(visited, g.vertex);
	Queue q;
	initQueue(q);
	enqueue(q, u);

	while(!isEmptyQueue(q)) {  // Change condition to while(!isEmptyQueue(q))
		int u = dequeue(q);
		if(getVisitedStatus(visited, u) == 0) {
			addVisited(visited, u);
			addList(result, u);
			List neighborsList = neighbors(g, u);
			for(int i = 0; i < neighborsList.size; i++) {
				int v = neighborsList.data[i];
				if(getVisitedStatus(visited, v) == 0) {
					enqueue(q, v);
				}
			}
		}
	}

	return result;
}

// Stack DijKstra(Graph g, int u, int v) {
//     // Mảng lưu khoảng cách từ u đến các đỉnh còn lại, khởi tạo bằng giá trị vô cùng
//     std::vector<int> dist(g.vertex, MAX_VALUE);
//     dist[u] = 0;  // Khoảng cách từ u đến chính nó là 0

//     // Mảng visited để kiểm tra các đỉnh đã được duyệt
//     std::vector<bool> visited(g.vertex, false);

//     // Mảng parent để lưu lại đỉnh trước đó khi đi qua
//     std::vector<int> parent(g.vertex, -1);

//     Stack stack;
//     init(stack);

//     // Duyệt qua tất cả các đỉnh trong đồ thị
//     for (int i = 0; i < g.vertex; i++) {
//         // Tìm đỉnh chưa được duyệt và có khoảng cách ngắn nhất
//         int minDist = MAX_VALUE;
//         int current = -1;
//         for (int j = 0; j < g.vertex; j++) {
//             if (!visited[j] && dist[j] < minDist) {
//                 minDist = dist[j];
//                 current = j;
//             }
//         }

//         // Nếu không còn đỉnh nào để duyệt, dừng
//         if (current == -1) {
//             break;
//         }

//         // Đánh dấu đỉnh này là đã được duyệt
//         visited[current] = true;

//         // Cập nhật khoảng cách cho các đỉnh kề
//         for (int neighbor = 0; neighbor < g.vertex; neighbor++) {
//             // Kiểm tra nếu có cạnh giữa current và neighbor (trọng số khác -1)
//             if (g.matrix[current][neighbor] != -1 && !visited[neighbor]) {
//                 int edgeWeight = g.matrix[current][neighbor];
//                 int newDist = dist[current] + edgeWeight;
//                 if (newDist < dist[neighbor]) {
//                     dist[neighbor] = newDist;
//                     parent[neighbor] = current;  // Cập nhật đường đi
//                 }
//             }
//         }
//     }

//     // Kiểm tra xem có đường đi từ u đến v không
//     if (dist[v] == MAX_VALUE) {
//         cout << "No path from " << u << " to " << v << endl;
//         return stack;  // Nếu không có đường đi, trả về stack rỗng
//     }

//     // Tạo đường đi từ u đến v (phục hồi đường đi sử dụng parent[])
//     int current = v;
//     while (current != -1) {
//         push(stack, current);  // Đưa đỉnh vào stack
//         current = parent[current];  // Đi đến đỉnh trước đó
//     }

//     return stack;
// }

// Dijkstra algorithm to find the shortest path from source to all other vertices
void dijkstra(Graph &g, int source) {
    vector<int> dist(g.vertex, INT_MAX); // distance array
    dist[source] = 0;  // distance to the source is 0
    
    vector<bool> visited(g.vertex, false); // visited array
    
    // Min-heap to store the vertex with the smallest distance
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, source});  // Start with the source vertex

    while (!pq.empty()) {
        int u = pq.top().second;  // Get the vertex with the smallest distance
        pq.pop();
        
        if (visited[u]) continue;  // Skip if the vertex is already processed
        visited[u] = true;

        // Explore the neighbors of vertex u
        for (int v = 0; v < g.vertex; v++) {
            if (g.matrix[u][v] != -1 && !visited[v]) {  // There is a valid edge and v is not visited
                int newDist = dist[u] + g.matrix[u][v];
                if (newDist < dist[v]) {  // Relax the edge if we found a shorter path
                    dist[v] = newDist;
                    pq.push({dist[v], v});
                }
            }
        }
    }
    
    // Output the shortest distances from source to all other vertices
    cout << "Shortest distances from vertex " << source << ":" << endl;
    for (int i = 0; i < g.vertex; i++) {
        if (dist[i] == INT_MAX)
            cout << "Vertex " << i << " is unreachable" << endl;
        else
            cout << "Distance to vertex " << i << " is " << dist[i] << endl;
    }
}

int main() {
    Graph g;
    string graphData = "5\n0 1 10\n0 2 20\n1 2 5\n1 3 50\n2 3 10\n3 4 2"; // Example graph input
    readWeightedGraphFromString(graphData, g);
    
    int source = 0;  // Source vertex
    dijkstra(g, source);
    
    return 0;
}

// int main() {
//     Graph g;
//     string graphData = "4\n0 1 2\n0 2 4\n1 2 1\n1 3 7\n2 3 3\n";  // Đồ thị có trọng số
//     readWeightedGraphFromString(graphData, g);

//     // In đồ thị trọng số
//     cout << "Weighted Graph adjacency matrix:" << endl;
//     printGraph(g);

//     // Chạy Dijkstra từ đỉnh 0 đến đỉnh 3
//     Stack path = DijKstra(g, 0, 3);

//     // In đường đi từ 0 đến 3
//     cout << "Path from 0 to 3: ";
//     while (!isEmpty(path)) {
//         cout << pop(path) << " ";
//     }
//     cout << endl;

//     return 0;
// }

// int main() {
// 	cout << "Hello, World!" << endl;
// 	Graph g;

// 	// Test case string (graphData)
// 	string graphData = "4\n0 1\n0 2\n1 2\n2 3\n";
// 	string WeightedGraph = "4\n0 1 2\n0 2 3\n1 2 4\n2 3 5\n";

// 	// Read graph from string
// 	readGraphFromString(graphData, g);

// 	// Print the graph's adjacency matrix
// 	cout << "Graph adjacency matrix:" << endl;
// 	printGraph(g);

// 	// Example to print the neighbors of vertex 2
// 	cout << "Neighbors of vertex 2: ";
// 	List neighborsList = neighbors(g, 2);
// 	printList(neighborsList);

// 	// Example to calculate the degree of vertex 2
// 	cout << "Degree of vertex 2: " << degree(g, 2) << endl;

// 	// Example to run DFS from vertex 0
// 	cout << "DFS starting from vertex 0: ";
// 	List dfsResult = DFS(g, 0);
// 	printList(dfsResult);

// 	cout << "DFS starting from vertex 0 by Recursive: ";
// 	List dfs = dfsRecursive(g, 0);
// 	printList(dfs);

// 	cout << "BFS starting from vertex 0: ";
// 	List bfs = BFS(g, 0);
// 	printList(bfs);

// 	return 0;
// }

