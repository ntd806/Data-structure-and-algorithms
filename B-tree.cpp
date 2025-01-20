#include <iostream>
#define MAX 1000
using namespace std;

struct BTree {
    int *keys;              // Con trỏ tới mảng các khóa (keys).
    BTree **child_ptr;   // Con trỏ tới mảng các con trỏ (chỉ đến các nút con của nút hiện tại).
    bool flag;              // Cờ (flag) để kiểm tra xem nút này có phải là nút lá hay không.
    int n;               // Số lượng khóa hiện có trong nút.
};

BTree* init(int order) {
    // Cấp phát bộ nhớ cho một nút mới của cây B-Tree
    BTree* newTree = new BTree();
    // Cấp phát mảng các khóa (d) với kích thước order - 1
    // Vì mỗi nút có tối đa order - 1 khóa
    newTree->keys = new int[order - 1];
    // Cấp phát mảng con trỏ đến các nút con (child_ptr)
    // Mỗi nút có tối đa order con trỏ con, bao gồm cả nút lá và nút không lá
    newTree->child_ptr = new BTree *[order];
    // Đặt cờ l = true, chỉ ra rằng đây là một nút lá (leaf node)
    newTree->flag = true;
    // Đặt số lượng khóa trong nút là 0 (chưa có khóa)
    newTree->n = 0;
    // Khởi tạo tất cả các con trỏ trong mảng child_ptr là NULL
    // Các con trỏ này trỏ đến các nút con và sẽ được gán giá trị khi cần thiết
    for (int i = 0; i < order; i++) {
        newTree->child_ptr[i] = NULL;
    }
    // Trả về con trỏ đến nút vừa khởi tạo
    return newTree;
}

bool checkChildCount(BTree* node, int order) {
    if(node != NULL) return true;

    int minChild = order;
    int maxChild = 2 * order;
    bool valid = node->n >= minChild && node->n <= maxChild;

    if(valid) return true;

    return false;
}

struct Stack {
    BTree **a;  // Con trỏ động đến mảng con trỏ đến các nút
    int top;    // Vị trí đỉnh của stack
};

void init(Stack* &s) {
    s->a = new BTree *[MAX];
    s->top = -1;
}

void push(Stack* &s, BTree *x) {
    s->a[++s->top] = x;
}

BTree* pop(Stack* &s) {
    return s->a[s->top--];
}

void insert(BTree* &root, int x, Stack* &s ) {
    if (root == NULL ) return;
}

int main() {
    return 0;
}