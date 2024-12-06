#include <iostream>
#include "stack.h" // Bao gồm tệp tiêu đề chứa định nghĩa cấu trúc Stack và các hàm liên quan
using namespace std;

// Hàm khởi tạo stack
void init(Stack &s) {
    s.top = -1; // Ban đầu, stack rỗng với chỉ số đỉnh là -1
}

// Kiểm tra xem stack có rỗng không
bool isEmpty(Stack s) {
    return s.top < 0; // Nếu chỉ số đỉnh nhỏ hơn 0, stack rỗng
}

// Kiểm tra xem stack có đầy không
bool isFull(Stack s) {
    return s.top == MAX - 1; // Stack đầy khi chỉ số đỉnh đạt MAX-1
}

// Thêm một phần tử vào stack
void push(Stack &s, int x) {
    if (isFull(s)) { // Nếu stack đầy, không thể thêm phần tử
        cout << "Stack overflow! Cannot push " << x << endl;
        return;
    }
    s.a[++s.top] = x; // Tăng chỉ số đỉnh và thêm phần tử
}

// Loại bỏ và trả về phần tử ở đỉnh stack
int pop(Stack &s) {
    if (isEmpty(s)) { // Nếu stack rỗng, không thể loại bỏ phần tử
        cout << "Stack underflow! Cannot pop from an empty stack." << endl;
        return -1; // Giá trị đặc biệt để báo lỗi
    }
    return s.a[s.top--]; // Trả về phần tử đỉnh và giảm chỉ số đỉnh
}

// Hiển thị các phần tử trong stack
void display(Stack s) {
    if (isEmpty(s)) { // Nếu stack rỗng, không có gì để hiển thị
        cout << "Stack is empty!" << endl;
        return;
    }
    cout << "Stack elements: ";
    for (int i = s.top; i >= 0; i--) { // Lặp qua các phần tử từ đỉnh đến đáy
        cout << s.a[i] << " ";
    }
    cout << endl;
}

// Chuyển đổi một số từ hệ thập phân sang hệ cơ số khác
void convertRadice(int x, int radice) {
    Stack s;
    init(s); // Khởi tạo stack mới để lưu các phần dư

    // Tính phần dư và thêm vào stack
    while (x != 0) {
        int r = x % radice; // Tính phần dư
        push(s, r); // Lưu phần dư vào stack
        x /= radice; // Chia số cho cơ số
    }

    // Hiển thị các phần dư từ stack (theo thứ tự ngược lại để tạo thành số mới)
    display(s);
}

// int main() {
//     Stack s;
//     init(s);
//     // Test các phương thức cơ bản của stack
//     push(s, 10);
//     push(s, 20);
//     push(s, 30);
//     display(s);

//     cout << "Popped element: " << pop(s) << endl;
//     display(s);

//     // Kết thúc chương trình
//     return 0;
// }
