#include <iostream>
#define MAX 1000
using namespace std;

// Cây AVL là một loại cây nhị phân tìm kiếm (Binary Search Tree - BST) có cân bằng chiều cao.
// Điều này có nghĩa là, đối với mỗi nút trong cây AVL, sự khác biệt chiều cao giữa cây con bên trái và cây con bên phải không được vượt quá 1. 
// Điều này đảm bảo rằng các thao tác trên cây AVL, như tìm kiếm, thêm và xóa, có độ phức tạp là O(log⁡n)O(logn).
// Các trường hợp mất cân bằng trong cây AVL
// Hệ số cây cân bằng 
// (Balance Factor) của một nút trong cây AVL được xác định bằng hiệu chiều cao của cây con bên trái và cây con bên phải của nút đó.
// Hệ số cân bằng của một nút có thể là -1, 0 hoặc 1.

// Khi thêm hoặc xóa một phần tử, cây AVL có thể bị mất cân bằng.
// Có 4 trường hợp mất cân bằng chính, và mỗi trường hợp yêu cầu thực hiện các phép quay (rotations) để cân bằng lại cây:

//     Trường hợp Left-Left (LL):
//         Xảy ra khi một nút được thêm vào cây con trái của cây con trái của một nút.
//         Cách xử lý: Thực hiện một phép quay phải (Right Rotation) tại nút mất cân bằng.

//     Trường hợp Right-Right (RR):
//         Xảy ra khi một nút được thêm vào cây con phải của cây con phải của một nút.
//         Cách xử lý: Thực hiện một phép quay trái (Left Rotation) tại nút mất cân bằng.

//     Trường hợp Left-Right (LR):
//         Xảy ra khi một nút được thêm vào cây con phải của cây con trái của một nút.
//         Cách xử lý: Thực hiện quay trái tại cây con trái, sau đó quay phải tại nút mất cân bằng.

//     Trường hợp Right-Left (RL):
//         Xảy ra khi một nút được thêm vào cây con trái của cây con phải của một nút.
//         Cách xử lý: Thực hiện quay phải tại cây con phải, sau đó quay trái tại nút mất cân bằng.

// phần định nghĩa cấu trúc cây AVL
struct Node {
    int data;
    Node *left, *right;
    int height;
};

void init (Node *node) {
    node->left = node->right = NULL;
    node->height = 1;
}

Node* createNode (int data) {
    Node* node = new Node;
    node->data = data;
    init(node);
    return node;
}

int height (Node *node) {
    if (node == NULL) return 0;

    return node->height;
}

int Balance (Node* node) {
    if (node == NULL) return 0;

    return height(node->left) - height(node->right);
}

void updateHeight (Node* node) {
    node->height = max(height(node->left), height(node->right)) + 1;
}

//phần định nghĩa stack để lưu trữ các nút trong quá trình thêm hoặc xóa
struct Stack {
    int top;
    Node* stack[MAX];
};

void initStack (Stack* &stack) {
    stack = new Stack;
    stack->top = -1;
}

void push(Node* node, Stack* &stack) {
    stack->stack[++stack->top] = node;
}

Node* pop(Stack* stack) {
    return stack->stack[stack->top--];
}

//phần thực thị các phép quay và tìm kiếm cây AVL
Node* search(Node* root, int data) {
    while (root != NULL) {
        // Nếu tìm thấy giá trị
        if (root->data == data) {
            return root;
        }

        // Nếu giá trị cần tìm nhỏ hơn, di chuyển sang cây con bên trái
        if (data < root->data) {
            root = root->left;
        }
        // Nếu giá trị cần tìm lớn hơn, di chuyển sang cây con bên phải
        else {
            root = root->right;
        }
    }

    // Nếu không tìm thấy, trả về NULL
    return NULL;
}

// ở vị trí k2 là cây con
void rightRotate(Node* &k2) {
    Node* k1 = k2->left;      // Lấy cây con trái của k2 làm gốc
    k2->left = k1->right;     // Gán cây con phải của k1 làm cây con trái của k2
    k1->right = k2;           // Gán k2 làm cây con phải của k1

    // Cập nhật chiều cao của k2 và k1
    updateHeight(k2);
    updateHeight(k1);

    // Nâng k1 lên làm gốc mới
    k2 = k1;
}

void leftRotate(Node* &k1) {
   Node* k2 = k1->right;      // Lấy cây con phải của k1 làm gốc
   k1->right = k2->left; // Gán cây con trái của k2 làm cây con phải của k1
   k2->left = k1;  // Gán k1 làm cây con trái của k

   updateHeight(k1);          // Cập nhật chiều cao của k1 trước
   updateHeight(k2);
   k1 = k2; // Nâng k2 lên làm gốc mới
}

void leftRightRotate(Node* &k3) {
    // Bước 1: Quay trái tại cây con trái của k3 (k1)
    leftRotate(k3->left);

    // Bước 2: Quay phải tại chính k3
    rightRotate(k3);
}

void rightLeftRotate(Node* &k3) {
    // Bước 1: Quay phải tại cây con phải của k3 (k1)
    rightRotate(k3->right);

    // Bước 2: Quay trái tại chính k3
    leftRotate(k3);
}

// Trường hợp	Điều kiện Balance Factor của nút k	Điều kiện của cây con
// LL	Balance Factor(k) > 1	&& Balance Factor(k->left) >= 0
// RR	Balance Factor(k) < -1	&& Balance Factor(k->right) <= 0
// LR	Balance Factor(k) > 1	&& Balance Factor(k->left) < 0
// RL	Balance Factor(k) < -1	&& Balance Factor(k->right) > 0

void insert(Node* &root, Stack* &stack, int x) {
    if (root == NULL) {
        root = createNode(x);
        return;
    }

    Node* cur = root;
    Node* parent = NULL;

    // Tìm vị trí chèn và lưu đường đi vào stack
    while (cur != NULL) {
        if (cur->data == x) return; // Giá trị đã tồn tại, không cần chèn
        parent = cur;
        push(cur, stack);

        if (x < cur->data) {
            cur = cur->left;
        } else {
            cur = cur->right;
        }
    }

    // Thêm nút mới vào cây
    if (x < parent->data) {
        parent->left = createNode(x);
    } else {
        parent->right = createNode(x);
    }

    // Quay trở lại từ stack và cập nhật cân bằng
    Node* node = NULL;
    while (stack->top >= 0) {
        node = pop(stack);
        updateHeight(node); // Cập nhật chiều cao của nút

        int balance = Balance(node); // Tính hệ số cân bằng

        // Kiểm tra và xử lý mất cân bằng
        if (balance > 1 && Balance(node->left) >= 0) {
            // Trường hợp LL
            rightRotate(node);
        } else if (balance < -1 && Balance(node->right) <= 0) {
            // Trường hợp RR
            leftRotate(node);
        } else if (balance > 1 && Balance(node->left) < 0) {
            // Trường hợp LR
            leftRightRotate(node);
        } else if (balance < -1 && Balance(node->right) > 0) {
            // Trường hợp RL
            rightLeftRotate(node);
        }

        // Nếu stack còn phần tử, cập nhật liên kết cha - con
        if (stack->top >= 0) {
            Node* parent = stack->stack[stack->top];
            if (parent->left == node || (x < parent->data && parent->right != node)) {
                parent->left = node;
            } else {
                parent->right = node;
            }
        } else {
            root = node; // Cập nhật root nếu stack rỗng
        }
    }
}

void LNR(Node* node) {
    if (node != NULL) {
        LNR(node->left);
        cout << "Node: " << node->data << ", height: " << node->height << ", balance: "<< Balance(node)<< endl;
        LNR(node->right);
    }
}

int main() {
    Stack* stack;
    initStack(stack);
    Node* root = NULL;

    int data[] = {10, 20, 30, 40, 50, 25, 60, 70, 80, 90, 100, 110};
    for (int x : data) {
        insert(root, stack, x);
    }

    cout << "LNR traversal of AVL tree: ";
    LNR(root);
    cout << endl;

    delete stack;
    return 0;
}