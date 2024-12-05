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
    node* newnode = new node;
    newnode->a = x;
    newnode->left = NULL;
    newnode->right = NULL;

    return newnode;
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
    node* newnode = create(x);
    if (parent->a > x) {
        parent->left = newnode;
    } else {
        parent->right = newnode;
    }
}

node* search(node* root, int x) {
    if (root == NULL) {
        return NULL; // Return NULL if the tree is empty
    }

    node* p = root;
    while (p != NULL) {
        if (p->a > x) {
            p = p->left; // Traverse left if current node's value is greater than x
        } else if (p->a < x) {
            p = p->right; // Traverse right if current node's value is less than x
        } else {
            return p; // node found
        }
    }

    return NULL; // Return NULL if the value is not found
}

void deletenode(node*& root, int x) {
    if (root == NULL) return;

    node* parent = NULL;
    node* current = root;

    // Tìm kiếm node cần xóa và lưu lại node cha
    while (current != NULL && current->a != x) {
        parent = current;
        if (x < current->a)
            current = current->left;
        else
            current = current->right;
    }

    // Nếu không tìm thấy node
    if (current == NULL) return;

    // Xử lý trường hợp node có 2 con
    if (current->left != NULL && current->right != NULL) {
        // Tìm node nhỏ nhất ở cây con bên phải (thay thế cho node bị xóa)
        node* successorParent = current;
        node* successor = current->right;

        while (successor->left != NULL) {
            successorParent = successor;
            successor = successor->left;
        }

        // Gán giá trị của successor cho node hiện tại
        current->a = successor->a;

        // Tiếp tục xóa successor (là node không có hoặc chỉ có 1 con)
        current = successor;
        parent = successorParent;
    }

    // Xử lý trường hợp node có 0 hoặc 1 con
    node* child = (current->left != NULL) ? current->left : current->right;

    if (parent == NULL) {
        // Trường hợp xóa node gốc
        root = child;
    } else if (parent->left == current) {
        parent->left = child;
    } else {
        parent->right = child;
    }

    delete current;
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

    node* result = search(root, 15);
    if (result != NULL) {
        cout << "Value found: " << result->a << endl;
    } else {
        cout << "Value not found in the tree." << endl;
    }

    return 0;
}
