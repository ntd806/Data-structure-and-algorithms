#include <iostream>
#define MAX 1000
using namespace std;

struct node
{
    int a;
    node* left;
    node* right;
};

void init(node* &root){
    root = NULL;
}

node* create(int x){
    node* newNode = new node;
    newNode->a = x;
    newNode->left = NULL;
    newNode->right = NULL;

    return newNode;
}

void lnr(node* root) {
    if (root == NULL) return;
    lnr(root->left);
    cout << root->a << " ";
    lnr(root->right);
}

void insert(node* &root, int x) {
    if (root == NULL) {
        root = create(x);
        return;
    }

    node* p = root;
    node* parent = NULL;
    while (p != NULL) {
        parent = p; // Update parent before moving
        if (p->a > x) {
            p = p->left;
        } else if (p->a < x) {
            p = p->right;
        } else {
            return; // Duplicate values are not allowed in BST
        }
    }

    // Create a new node and attach it to the parent
    node* newNode = create(x);
    if (parent->a > x) {
        parent->left = newNode;
    } else {
        parent->right = newNode;
    }
}

// int main() {
//     node* root;
//     init(root);

//     insert(root, 50);
//     insert(root, 30);
//     insert(root, 70);
//     insert(root, 20);
//     insert(root, 40);
//     insert(root, 60);
//     insert(root, 80);

//     cout << "In-order Traversal: ";
//     lnr(root);
//     return 0;
// }

int main() {
    node* root;
    init(root);

    insert(root, 25);
    insert(root, 15);
    insert(root, 35);
    insert(root, 15); // Duplicate
    insert(root, 25); // Duplicate

    cout << "In-order Traversal: ";
    lnr(root);
    return 0;
}
