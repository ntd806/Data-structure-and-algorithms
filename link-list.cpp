#include <iostream>

struct Node {
    int a;
    Node* next;
};

// Khởi tạo danh sách
void init(Node*& head) {
    head = nullptr;
}

// Kiểm tra danh sách rỗng
bool isEmpty(Node* head) {
    return (head == nullptr);
}

// Tạo một node mới
Node* create(int x) {
    Node* newNode = new Node;
    newNode->a = x;
    newNode->next = nullptr;
    return newNode;
}

// Thêm một phần tử vào đầu danh sách
void addFirst(Node*& head, int x) {
    Node* newNode = create(x);
    newNode->next = head;
    head = newNode;
}

// Thêm một phần tử vào cuối danh sách
void addLast(Node*& head, int x) {
    if (isEmpty(head)) {
        addFirst(head, x);
    } else {
        Node* newNode = create(x);
        Node* last = head;
        while (last->next != nullptr)
            last = last->next;
        last->next = newNode;
    }
}

// Thêm một phần tử sau giá trị cụ thể
void addAfter(Node*& head, int x, int v) {
    if (isEmpty(head)) {
        std::cout << "Danh sách rỗng, không thể thêm sau.\n";
        return;
    }

    Node* newNode = create(x);
    Node* q = head;

    while (q != nullptr && q->a != v) {
        q = q->next;
    }

    if (q != nullptr) {
        newNode->next = q->next;
        q->next = newNode;
    } else {
        std::cout << "Không tìm thấy giá trị " << v << " trong danh sách.\n";
        delete newNode;
    }
}

// In danh sách liên kết
void printList(Node* head) {
    if (isEmpty(head)) {
        std::cout << "Danh sách rỗng.\n";
        return;
    }
    Node* current = head;
    while (current != nullptr) {
        std::cout << current->a << " ";
        current = current->next;
    }
    std::cout << std::endl;
}

// Xóa phần tử đầu tiên
bool deleteFirst(Node*& head) {
    if (isEmpty(head))
        return false;

    Node* temp = head;
    head = temp->next;
    delete temp;
    return true;
}

// Xóa phần tử cuối cùng
bool deleteLast(Node*& head) {
    if (isEmpty(head))
        return false;

    if (head->next == nullptr) {
        return deleteFirst(head);
    }

    Node* previous = nullptr;
    Node* last = head;

    while (last->next != nullptr) {
        previous = last;
        last = last->next;
    }

    previous->next = nullptr;
    delete last;
    return true;
}

// Xóa phần tử theo giá trị
bool deleteNode(Node*& head, int v) {
    if (isEmpty(head))
        return false;

    if (head->a == v) {
        return deleteFirst(head);
    }

    Node* current = head;
    Node* previous = nullptr;

    while (current != nullptr && current->a != v) {
        previous = current;
        current = current->next;
    }

    if (current != nullptr) {
        previous->next = current->next;
        delete current;
        return true;
    }

    std::cout << "Không tìm thấy giá trị " << v << " để xóa.\n";
    return false;
}

// Giải phóng bộ nhớ
void clean(Node*& head) {
    while (head != nullptr) {
        deleteFirst(head);
    }
}

int main() {
    Node* head = nullptr;

    // Thêm phần tử vào danh sách
    addFirst(head, 10);
    addLast(head, 20);
    addLast(head, 30);
    addLast(head, 40);
    addAfter(head, 12, 30);

    // In danh sách
    std::cout << "Danh sách ban đầu: ";
    printList(head);

    // Xóa phần tử đầu tiên
    if (deleteFirst(head)) {
        std::cout << "Đã xóa phần tử đầu tiên.\n";
    } else {
        std::cout << "Danh sách rỗng, không thể xóa.\n";
    }

    // In danh sách sau khi xóa
    std::cout << "Danh sách sau khi xóa phần tử đầu: ";
    printList(head);

    // Xóa toàn bộ danh sách
    clean(head);
    std::cout << "Danh sách đã được giải phóng.\n";

    return 0;
}
