#include <iostream>
#define MAX 7

// Định nghĩa cấu trúc node
struct node {
    int a;          // Giá trị của node
    node* next;     // Con trỏ trỏ đến node tiếp theo
};

// Hàm khởi tạo bảng băm, gán tất cả các bucket ban đầu là NULL
void initHash(node* heads[]) {
    for (int i = 0; i < MAX; i++) {
        heads[i] = NULL;
    }
}

// Hàm tạo một node mới với giá trị x
node* create(int x) {
    node* p = new node;
    p->a = x;
    p->next = NULL;
    return p;
}

// Hàm băm (hash function), trả về chỉ mục bucket
int hash(int value) {
    return value % MAX; // Lấy phần dư của giá trị chia cho MAX
}

// Hàm thêm một node mới vào bảng băm (theo thứ tự tăng dần)
void insertNode(node* heads[], int x) {
    int key = hash(x);         // Tìm chỉ mục bucket bằng hàm băm
    node* head = heads[key];   // Lấy danh sách liên kết tại bucket này
    node* previous = NULL;     // Con trỏ lưu node trước đó (nếu có)
    
    // Duyệt danh sách liên kết để tìm vị trí thích hợp để chèn
    while (head != NULL && head->a < x) {
        previous = head;       // Lưu lại vị trí trước đó
        head = head->next;     // Di chuyển đến node tiếp theo
    }
    
    node* newNode = create(x); // Tạo node mới chứa giá trị x
    
    if (previous == NULL) {    // Nếu chèn vào đầu danh sách
        newNode->next = heads[key]; // Node mới trỏ đến node đầu tiên
        heads[key] = newNode;      // Cập nhật node đầu tiên là node mới
    } else {                   // Nếu chèn vào giữa hoặc cuối danh sách
        newNode->next = head;      // Node mới trỏ đến vị trí tiếp theo
        previous->next = newNode;  // Node trước đó trỏ đến node mới
    }
}

// Hàm hiển thị tất cả các bucket trong bảng băm
void displayHash(node* heads[]) {
    for (int i = 0; i < MAX; i++) {
        std::cout << "Bucket " << i << ": ";
        node* head = heads[i];
        while (head != NULL) {
            std::cout << head->a << " -> ";
            head = head->next;
        }
        std::cout << "NULL" << std::endl;
    }
}

int main() {
    // Khởi tạo bảng băm với các bucket ban đầu NULL
    node* hashTable[MAX];
    initHash(hashTable);

    // Thêm các giá trị vào bảng băm
    insertNode(hashTable, 10);
    insertNode(hashTable, 3);
    insertNode(hashTable, 17);
    insertNode(hashTable, 5);
    insertNode(hashTable, 22);
    insertNode(hashTable, 8);

    // Hiển thị nội dung bảng băm
    displayHash(hashTable);

    return 0;
}
