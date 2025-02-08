#include <iostream>
#define MAX 20
using namespace std;

struct Graph {
    int vertex;
    int matrix[MAX][MAX];
};

struct List
{
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
// Initialize graph
void initGraph(Graph &g) {
    for (int i = 1; i <= g.vertex; i++) {  // Change from 1 to 0-based index
        for (int j = 1; j <= g.vertex; j++) {  // Change from 1 to 0-based index
            g.matrix[i][j] = 0;
        }
    }
}

// Check adjacency between vertices u and v
int adjacent(Graph g, int u, int v) {
    return g.matrix[u][v];
}

// Add an edge between vertices u and v
void addEdge(Graph &g, int u, int v) {
    g.matrix[u][v] = 1;
    g.matrix[v][u] = 1;
}

// Print the graph's adjacency matrix
void printGraph(Graph g) {
    for (int i = 1; i <= g.vertex; i++) {  // Change from 1 to 0-based index
        for (int j = 1; j <= g.vertex; j++) {  // Change from 1 to 0-based index
            cout << g.matrix[i][j] << " ";
        }
        cout << endl;
    }
}

// Create a graph by entering the number of vertices
void createGraph(Graph &g) {
    cout << "Enter number of vertices: ";
    cin >> g.vertex;
    initGraph(g);
}

// Calculate the degree of a vertex u
int degree(Graph g, int u) {
    int deg = 0;
    for (int i = 1; i <= g.vertex; i++) {  // Change from 1 to 0-based index
        if (g.matrix[u][i] == 1) {
            deg++;
        }
    }
    return deg;
}

// Read graph data from a file
void readGraph(string filename, Graph &g) {
    FILE *f = fopen(filename.c_str(), "r");
    if (f == NULL) {
        cout << "Can't open file" << endl;
        return;
    }
    fscanf(f, "%d", &g.vertex);  // Read number of vertices
    initGraph(g);
    int u, v;
    while (fscanf(f, "%d %d", &u, &v) != EOF) {
        addEdge(g, u, v);  // Add edges from the file
    }
    fclose(f);
}

List neighbors(Graph g, int u) {
    List l;
    initList(l);
    for (int i = 1; i <= g.vertex; i++) {  // Change from 1 to 0-based index
        if (g.matrix[u][i] == 1) {
            addList(l, i);
        }
    }
    
    return l;
}

int main() {
    cout << "Hello, World!" << endl;
    Graph g;
    createGraph(g);
    printGraph(g);

    // Example to add an edge manually
    addEdge(g, 0, 1);
    addEdge(g, 1, 2);

    cout << "Graph after adding edges manually:" << endl;
    printGraph(g);

    // Example to read graph from a file (assuming file exists)
    // readGraph("graph.txt", g);

    return 0;
}
