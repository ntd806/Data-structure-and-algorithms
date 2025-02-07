#include <iostream>
using namespace std;

// Cấu trúc của nút B‑Tree
struct BTree {
    int *keys;          // Mảng các khóa, kích thước: order - 1
    BTree **child_ptr;  // Mảng các con trỏ, kích thước: order
    bool flag;          // true nếu nút là lá
    int n;              // Số lượng khóa hiện có trong nút
};

// Hàm khởi tạo một nút với kích thước dựa vào order của B‑Tree
BTree* init(int order) {
    BTree* newTree = new BTree();
    newTree->keys = new int[order - 1];     // Mỗi nút có tối đa order - 1 khóa
    newTree->child_ptr = new BTree*[order];   // Mỗi nút có tối đa order con trỏ
    newTree->flag = true;                     // Mặc định tạo nút lá
    newTree->n = 0;
    for (int i = 0; i < order; i++) {
        newTree->child_ptr[i] = NULL;
    }
    return newTree;
}

// Biến toàn cục: order của B‑Tree và con trỏ tới gốc của cây
int ORDER;      // Ví dụ: ORDER = 4, 6, ... tùy theo yêu cầu
BTree* root = NULL;

// Hàm tách con của nút parent tại vị trí i.
// Giả sử node y = parent->child_ptr[i] đã đầy (n == ORDER - 1)
void splitChild(BTree* parent, int i) {
    BTree* y = parent->child_ptr[i];
    BTree* z = init(ORDER);
    z->flag = y->flag;   // z có cùng tính chất (lá hay không) với y

    // Tính minimum degree t: t = ceil(ORDER/2)
    int t = (ORDER + 1) / 2;  

    // z sẽ nhận t - 1 khóa từ y (các khóa từ chỉ số t đến ORDER - 2)
    z->n = t - 1;
    for (int j = 0; j < t - 1; j++) {
        z->keys[j] = y->keys[j + t];
    }
    // Nếu y không phải là lá, chuyển cả t con trỏ sau khóa trung vị sang z
    if (!y->flag) {
        for (int j = 0; j < t; j++) {
            z->child_ptr[j] = y->child_ptr[j + t];
        }
    }
    // Sau khi chuyển, số khóa của y giảm còn t - 1
    y->n = t - 1;

    // Chèn z vào mảng con của parent, dịch chuyển các con sau i sang phải
    for (int j = parent->n; j >= i + 1; j--) {
        parent->child_ptr[j + 1] = parent->child_ptr[j];
    }
    parent->child_ptr[i + 1] = z;

    // Dịch chuyển các khóa của parent để chừa chỗ cho khóa trung vị của y
    for (int j = parent->n - 1; j >= i; j--) {
        parent->keys[j + 1] = parent->keys[j];
    }
    // Đưa khóa trung vị từ y (ở vị trí t - 1) lên parent
    parent->keys[i] = y->keys[t - 1];
    parent->n++;
}

// Hàm chèn khóa key vào một nút không đầy (node->n < ORDER - 1)
void insertNonFull(BTree* node, int key) {
    int i = node->n - 1;
    if (node->flag) { // Nếu node là lá
        // Dịch chuyển các khóa về bên phải để chỗ cho khóa mới
        while (i >= 0 && key < node->keys[i]) {
            node->keys[i + 1] = node->keys[i];
            i--;
        }
        node->keys[i + 1] = key;
        node->n++;
    } else { // Nếu node không phải là lá
        // Tìm vị trí con phù hợp để đi xuống
        while (i >= 0 && key < node->keys[i])
            i--;
        i++;  // Chỉ số của child_ptr cần đi xuống

        // Nếu con này đã đầy thì tách nó trước
        if (node->child_ptr[i]->n == ORDER - 1) {
            splitChild(node, i);
            // Sau khi tách, nếu khóa mới lớn hơn khóa trung vị vừa được đưa lên thì chuyển sang con bên phải
            if (key > node->keys[i])
                i++;
        }
        insertNonFull(node->child_ptr[i], key);
    }
}

// Hàm insert: chèn khóa a vào B‑Tree
void insert(int a) {
    // Nếu cây rỗng, tạo nút gốc mới và chèn khóa
    if (root == NULL) {
        root = init(ORDER);
        root->keys[0] = a;
        root->n = 1;
        return;
    }
    // Nếu nút gốc đầy, ta cần tách gốc
    if (root->n == ORDER - 1) {
        BTree* s = init(ORDER);
        s->flag = false;       // s sẽ trở thành nút không lá
        s->child_ptr[0] = root;
        splitChild(s, 0);
        // Xác định con nào của s sẽ nhận khóa a
        int i = 0;
        if (s->keys[0] < a)
            i++;
        insertNonFull(s->child_ptr[i], a);
        root = s;  // Cập nhật lại gốc
    } else {
        insertNonFull(root, a);
    }
}

// Ví dụ hàm duyệt (in thứ tự trung thứ tự) để kiểm tra kết quả
void traverse(BTree* node) {
    if (node != NULL) {
        int i;
        for (i = 0; i < node->n; i++) {
            if (!node->flag) {
                traverse(node->child_ptr[i]);
            }
            cout << node->keys[i] << " ";
        }
        if (!node->flag) {
            traverse(node->child_ptr[i]);
        }
    }
}

int main() {
    // Khai báo order của B‑Tree (ví dụ: order = 4)
    cout << "Nhap order cua B-Tree: ";
    cin >> ORDER;
    
    int n, key;
    cout << "Nhap so luong phan tu can chen: ";
    cin >> n;
    for (int i = 0; i < n; i++) {
        cout << "Nhap phan tu: ";
        cin >> key;
        insert(key);
    }
    
    cout << "\nCac phan tu cua B-Tree (duyet theo thu tu trung thu tu):\n";
    traverse(root);
    cout << endl;
    return 0;
}
