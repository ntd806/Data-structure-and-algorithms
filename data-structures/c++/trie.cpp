#include <iostream>
#define MAX 1000
using namespace std;

struct trieNode {
    trieNode* children[26]; // Array to store pointers to child nodes
    bool isEnd;             // Flag to indicate the end of a word
};

// Initialize a trie node with all children set to NULL
trieNode* init() {
    trieNode* node = new trieNode;
    for (int i = 0; i < 26; i++) {
        node->children[i] = NULL;
    }
    node->isEnd = false;
    return node;
}

struct Trie {
    trieNode* root; // Pointer to the root node of the trie
};

// Initialize a trie
Trie* create() {
    Trie* t = new Trie;
    t->root = init();
    return t;
}

bool insert(Trie* &t, const string word) {
    trieNode* currentNode = t->root; // Keep track of current node while traversing
    for(char c : word) {
        int index = c - 'a';
        if(currentNode->children[index] == NULL) {
            currentNode->children[index] = init();
        }
        currentNode = currentNode->children[index]; // Move to the next node
    }

    currentNode->isEnd = true; // Mark the end of the word
    return true;
}

bool search(Trie* &t, const string word) {
    trieNode* currentNode = t->root; // Keep track of current node while traversing
    for(char c : word) {
        int index = c - 'a';
        if(currentNode->children[index] == NULL) {
            return false; // If the character is not found, return false
        }
        currentNode = currentNode->children[index]; // Move to the next node
    }

    return currentNode->isEnd; // If we reached the end of the word, return true
}

int main() {
    Trie* t = create();

    // Test cases
    insert(t, "apple");
    insert(t, "app");
    insert(t, "banana");

    cout << "Search for 'apple': " << (search(t, "apple") ? "Found" : "Not Found") << endl;
    cout << "Search for 'app': " << (search(t, "app") ? "Found" : "Not Found") << endl;
    cout << "Search for 'banana': " << (search(t, "banana") ? "Found" : "Not Found") << endl;
    cout << "Search for 'orange': " << (search(t, "orange") ? "Found" : "Not Found") << endl;

    return 0;
}
