#include <iostream>
#define MAX 20
using namespace std;

struct graph {
    int vertex;
    int matrix[MAX][MAX];
};

void initGraph(graph &g) {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            g.matrix[i][j] = 0;
        }
    }
}

int adjacent(graph g, int u, int v) {
    return g.matrix[u][v];
}

void addEdge(graph &g, int u, int v) {
    g.matrix[u][v] = 1;
    g.matrix[v][u] = 1;
}

void printGraph(graph g) {
    for (int i = 0; i < g.vertex; i++) {
        for (int j = 0; j < g.vertex; j++) {
            cout << g.matrix[i][j] << " ";
        }
        cout << endl;
    }
}

void createGraph(graph &g) {
    cout << "enter vertex" << endl;
    cin >> g.vertex;
    initGraph(g);
}

int main() {
    cout << "Hello, World!" << endl;
    graph g;
    createGraph(g);
    printGraph(g);
    return 0;
}