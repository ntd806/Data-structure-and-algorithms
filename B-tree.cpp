#include <iostream>
#define MAX 1000
using namespace std;

// Definition of a tree node
struct node {
    int a;           // Value stored in the node
    node* left;      // Pointer to the left child
    node* right;     // Pointer to the right child
};

// Initialize a binary search tree
void init(node*& root) {
    root = NULL; // Initially, the tree is empty
}

// Create a new node with a given value
node* create(int x) {
    node* newnode = new node;
    newnode->a = x; // Set the value
    newnode->left = NULL;
    newnode->right = NULL;
    return newnode;
}

// Perform in-order traversal (LNR: Left-Node-Right)
void lnr(node* root) {
    if (root == NULL) return;
    lnr(root->left);             // Visit left subtree
    cout << root->a << " ";      // Print the value
    lnr(root->right);            // Visit right subtree
}

// Insert a value into the binary search tree
void insert(node*& root, int x) {
    if (root == NULL) {
        root = create(x); // Create a new root if the tree is empty
        return;
    }

    node* p = root;     // Start from the root
    node* parent = NULL; // Keep track of the parent node
    while (p != NULL) {
        parent = p;
        if (p->a > x) {
            p = p->left; // Move to the left subtree
        } else if (p->a < x) {
            p = p->right; // Move to the right subtree
        } else {
            // Handle duplicate values
            cout << "Duplicate value " << x << " not allowed in BST." << endl;
            return;
        }
    }

    // Attach the new node to the appropriate parent
    node* newnode = create(x);
    if (parent->a > x) {
        parent->left = newnode;
    } else {
        parent->right = newnode;
    }
}

// Search for a value in the binary search tree
node* search(node* root, int x) {
    node* p = root; // Start from the root
    while (p != NULL) {
        if (p->a > x) {
            p = p->left; // Move to the left subtree
        } else if (p->a < x) {
            p = p->right; // Move to the right subtree
        } else {
            return p; // Node found
        }
    }
    return NULL; // Node not found
}

// Delete a node with the given value
void deletenode(node*& root, int x) {
    if (root == NULL) return;

    node* parent = NULL;
    node* current = root;

    // Search for the node to delete
    while (current != NULL && current->a != x) {
        parent = current;
        if (x < current->a) {
            current = current->left;
        } else {
            current = current->right;
        }
    }

    if (current == NULL) return; // Node not found

    // Handle the case where the node has two children
    if (current->left != NULL && current->right != NULL) {
        // Find the inorder successor (smallest node in the right subtree)
        node* successorParent = current;
        node* successor = current->right;

        while (successor->left != NULL) {
            successorParent = successor;
            successor = successor->left;
        }

        // Replace the current node's value with the successor's value
        current->a = successor->a;

        // Delete the successor node
        current = successor;
        parent = successorParent;
    }

    // Handle the case where the node has one or no children
    node* child = (current->left != NULL) ? current->left : current->right;

    if (parent == NULL) {
        // If deleting the root
        root = child;
    } else if (parent->left == current) {
        parent->left = child;
    } else {
        parent->right = child;
    }

    delete current; // Free the memory
}

// Stack structure for iterative traversal or other purposes
struct Stack {
    node* a[MAX]; // Array to store pointers to tree nodes
    int top;      // Index of the top element in the stack
};

// Function to initialize the stack
void initStack(Stack& s) {
    s.top = -1; // Initially, the stack is empty
}

// Check if the stack is empty
bool isEmpty(Stack s) {
    return s.top < 0;
}

// Check if the stack is full
bool isFull(Stack s) {
    return s.top == MAX - 1;
}

// Push a node pointer onto the stack
void push(Stack& s, node* x) {
    if (isFull(s)) {
        cout << "Stack overflow! Cannot push node." << endl;
        return;
    }
    s.a[++s.top] = x; // Increment the top index and add the node
}

// Pop a node pointer from the stack
node* pop(Stack& s) {
    if (isEmpty(s)) {
        cout << "Stack underflow! Cannot pop from an empty stack." << endl;
        return NULL;
    }
    return s.a[s.top--]; // Return the top element and decrement the index
}

// Function to perform in-order traversal (LNR) of a binary tree using a stack
void lnrStack(node* head, Stack &s) {
    // Start traversal from the root of the tree
    node* current = head;

    // Continue until all nodes are processed (current is NULL and stack is empty)
    while (current != NULL || !isEmpty(s)) {
        // Traverse the left subtree
        while (current != NULL) {
            // Push the current node onto the stack
            push(s, current);
            // Move to the left child
            current = current->left;
        }

        // Pop the top node from the stack (backtrack to the most recent unprocessed node)
        current = pop(s);

        // Process the node (e.g., print its value)
        cout << current->a << " ";

        // Move to the right subtree of the current node
        current = current->right;
    }
}

// Function to count different types of nodes in a binary tree
void counter(node* root, int &n1, int &n2, int &n3) {
    // Base case: if the node is NULL, return
    if (root == NULL) {
        return;
    }

    // Recursive call to the left subtree
    counter(root->left, n1, n2, n3);

    // Check the type of node
    if (root->left == NULL && root->right == NULL) {
        // Leaf node: no children
        n1++;
    } else if (root->left != NULL || root->right != NULL) {
        // Internal node: at least one child
        n2++;
    } else {
        // This branch is redundant as it won't be reached. 
        // There are no nodes that are neither leaf nor internal.
        n3++;
    }

    // Recursive call to the right subtree
    counter(root->right, n1, n2, n3);
}

// Function to calculate the height of a binary tree
int calHeight(node* root) {
    // Base case: if the node is NULL, height is 0
    if (root != NULL) {
        // Recursive call to calculate height of left and right subtrees
        int left = calHeight(root->left) + 1;
        int right = calHeight(root->right) + 1;

        // Return the maximum of left and right subtree heights
        return left > right ? left : right;
    }

    return 0;
}

// Function to print all nodes at a specific depth `expected` in a binary tree
void showK(node* root, int k, int depth) {
    // Base case: if the node is NULL, return
    if (root == NULL) {
        return;
    }

    // Increment the current depth
    k++;

    // Traverse the left subtree
    showK(root->left, k, depth);

    // Check if the current depth matches the expected depth
    if (k == depth) {
        // Print the node's value if it is at the expected depth
        cout << root->a << " ";
    }

    // Traverse the right subtree
    showK(root->right, k, depth);
}

// Main function for testing
int main() {
    node* root;
    init(root); // Initialize the tree

    // Insert nodes into the tree
    insert(root, 25);
    insert(root, 15);
    insert(root, 35);
    insert(root, 15); // Duplicate
    insert(root, 25); // Duplicate

    cout << "In-order Traversal: ";
    lnr(root);
    cout << endl;

    // Search for a node
    node* result = search(root, 15);
    if (result != NULL) {
        cout << "Value found: " << result->a << endl;
    } else {
        cout << "Value not found in the tree." << endl;
    }

    return 0;
}
