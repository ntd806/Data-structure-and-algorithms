#include <iostream>
#include <sstream>
#include <string>
#define MAX 20
using namespace std;

struct Graph {
    int vertex;
    int matrix[MAX][MAX];
};

struct List {
    int data[MAX];
    int size;
};

void initList(List &l) {
    l.size = 0;
}

void addList(List &l, int u) {
    l.data[l.size] = u;
    l.size++;
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

void initGraph(Graph &g) {
    for (int i = 1; i <= g.vertex; i++) {  // Change to 0-based index
        for (int j = 1; j <= g.vertex; j++) {  // Change to 0-based index
            g.matrix[i][j] = 0;
        }
    }
}

int adjacent(Graph g, int u, int v) {
    return g.matrix[u][v];
}

void addEdge(Graph &g, int u, int v) {
    g.matrix[u][v] = 1;
    g.matrix[v][u] = 1;
}

void printGraph(Graph g) {
    for (int i = 1; i <= g.vertex; i++) {  // Change to 0-based index
        for (int j = 1; j <= g.vertex; j++) {  // Change to 0-based index
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
    for (int i = 1; i <= g.vertex; i++) {  // Change to 0-based index
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
    for (int i = 1; i <= g.vertex; i++) {  // Change to 0-based index
        if (g.matrix[u][i] == 1) {
            addList(l, i);
        }
    }
    return l;
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

    return 0;
}