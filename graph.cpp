#include <iostream>
#include <sstream>
#include <string>
#define MAX 20
using namespace std;

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

List neighbors(Graph g, int u) {
    List l;
    initList(l);
    for (int i = 0; i < g.vertex; i++) {
        if (g.matrix[u][i] == 1) {
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

int main() {
    cout << "Hello, World!" << endl;
    Graph g;

    // Test case string (graphData)
    string graphData = "4\n0 1\n0 2\n1 2\n2 3\n";

    // Read graph from string
    readGraphFromString(graphData, g);

    // Print the graph's adjacency matrix
    cout << "Graph adjacency matrix:" << endl;
    printGraph(g);

    // Example to print the neighbors of vertex 2
    cout << "Neighbors of vertex 2: ";
    List neighborsList = neighbors(g, 2);
    printList(neighborsList);

    // Example to calculate the degree of vertex 2
    cout << "Degree of vertex 2: " << degree(g, 2) << endl;

    // Example to run DFS from vertex 0
    cout << "DFS starting from vertex 0: ";
    List dfsResult = DFS(g, 0);
    printList(dfsResult);

    return 0;
}
