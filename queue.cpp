#include <iostream>
#define MAX 1000
using namespace std;

struct Queue {
    int a[MAX]; // Mảng để lưu trữ các phần tử
    int front;  // Chỉ số phần tử đầu hàng đợi
    int rear;   // Chỉ số phần tử cuối hàng đợi
};

// Hàm khởi tạo hàng đợi
void init(Queue &q) {
    q.front = -1;
    q.rear = -1;
}

// Kiểm tra hàng đợi có rỗng không
bool isEmpty(Queue q) {
    return q.front == -1;
}

// Kiểm tra hàng đợi có đầy không
bool isFull(Queue q) {
    return (q.rear + 1) % MAX == q.front;
}

// Thêm phần tử vào hàng đợi
void enqueue(Queue &q, int x) {
    if (isFull(q)) {
        cout << "Queue overflow! Cannot enqueue " << x << endl;
        return;
    }
    if (isEmpty(q)) {
        q.front = 0; // Nếu hàng đợi rỗng, đặt front tại vị trí 0
    }
    q.rear = (q.rear + 1) % MAX; // Di chuyển rear vòng quanh mảng
    q.a[q.rear] = x;
}

// Xóa phần tử ở đầu hàng đợi
int dequeue(Queue &q) {
    if (isEmpty(q)) {
        cout << "Queue underflow! Cannot dequeue from an empty queue." << endl;
        return -1; // Giá trị đặc biệt báo lỗi
    }
    int x = q.a[q.front];
    if (q.front == q.rear) {
        // Nếu chỉ còn một phần tử, đặt hàng đợi về trạng thái rỗng
        q.front = q.rear = -1;
    } else {
        q.front = (q.front + 1) % MAX; // Di chuyển front vòng quanh mảng
    }
    return x;
}

// Hiển thị các phần tử trong hàng đợi
void display(Queue q) {
    if (isEmpty(q)) {
        cout << "Queue is empty!" << endl;
        return;
    }
    cout << "Queue elements: ";
    int i = q.front;
    while (true) {
        cout << q.a[i] << " ";
        if (i == q.rear) break; // Đã duyệt hết hàng đợi
        i = (i + 1) % MAX;      // Di chuyển vòng quanh mảng
    }
    cout << endl;
}

int main() {
    Queue q;
    init(q);

    // Thử nghiệm các thao tác
    enqueue(q, 10);
    enqueue(q, 20);
    enqueue(q, 30);
    display(q);

    cout << "Dequeued element: " << dequeue(q) << endl;
    display(q);

    enqueue(q, 40);
    display(q);

    return 0;
}
