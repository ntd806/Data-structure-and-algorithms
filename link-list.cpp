#include <iostream>

struct node {
    int a;
    node* next;
};

void init(node* &head) {
    head = NULL;
}

node* create(int x) {
    node* newNode = new node;
    newNode->a = x;
    newNode->next = NULL;
    return newNode;
}

void addFirst(node* &head, int x) {
    node* newNode = create(x);
    newNode->next = head;
    head = newNode;
}

void printList(node* head) {
    node* current = head;
    while (current != NULL) {
        std::cout << current->a << " ";
        current = current->next;
    }
    std::cout << std::endl;
}

int main() {
    node* head = NULL;

    // Adding elements to the linked list
    addFirst(head, 10);
    addFirst(head, 20);
    addFirst(head, 30);
    addFirst(head, 40);

    // Printing the linked list
    printList(head);

    return 0;
}
