#include <iostream>
#define MAX 1000
using namespace std;

struct Stack {
    int a[MAX];
    int top;
};

void init(Stack &s) {
    s.top = -1;
}

bool isEmpty(Stack s){
    return s.top < 0;
}

bool isFull(Stack s) {
    return s.top == MAX - 1; // Sửa điều kiện kiểm tra đầy
}

void push(Stack &s, int x){
    if (isFull(s)) {
        cout << "Stack overflow! Cannot push " << x << endl;
        return;
    }
    s.a[++s.top] = x;
}

int pop(Stack &s) {
    if (isEmpty(s)) {
        cout << "Stack underflow! Cannot pop from an empty stack." << endl;
        return -1; // Giá trị đặc biệt báo lỗi
    }
    return s.a[s.top--];
}

void display(Stack s) {
    if (isEmpty(s)) {
        cout << "Stack is empty!" << endl;
        return;
    }
    cout << "Stack elements: ";
    for (int i = s.top; i <= 0; i--) {
        cout << s.a[i] << " ";
    }
    cout << endl;
}

void convertRadice(int x, int radice) {
    Stack s;
    init(s);

    while (x != 0)
    {
       int r = x % radice;
       push(s, r);
       x /= radice;
    }
    int n = s.top;

    display(s);
}

int main() {
    Stack s;
    init(s);
    // Test các phương thức
    push(s, 10);
    push(s, 20);
    push(s, 30);
    display(s);

    cout << "Popped element: " << pop(s) << endl;
    display(s);

    return 0;
}
