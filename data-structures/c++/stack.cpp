#include <iostream>
#define MAX 1000
using namespace std;

// Stack structure
struct Stack {
    int a[MAX];  // Array to hold stack elements
    int top;     // Index of the top element
};

// Function to initialize the stack
void init(Stack& s) {
    s.top = -1; // Initially, the stack is empty
}

// Check if the stack is empty
bool isEmpty(Stack s) {
    return s.top < 0; // Stack is empty when top < 0
}

// Check if the stack is full
bool isFull(Stack s) {
    return s.top == MAX - 1; // Stack is full when top equals MAX-1
}

// Push an element onto the stack
void push(Stack& s, int x) {
    if (isFull(s)) {
        cout << "Stack overflow! Cannot push " << x << endl;
        return;
    }
    s.a[++s.top] = x; // Increment top and add the element
}

// Pop an element from the stack and return it
int pop(Stack& s) {
    if (isEmpty(s)) {
        cout << "Stack underflow! Cannot pop from an empty stack." << endl;
        return -1; // Return a sentinel value to indicate failure
    }
    return s.a[s.top--]; // Return the top element and decrement the index
}

// Display all elements in the stack
void display(Stack s) {
    if (isEmpty(s)) {
        cout << "Stack is empty!" << endl;
        return;
    }
    cout << "Stack elements: ";
    for (int i = s.top; i >= 0; i--) { // Traverse from top to bottom
        cout << s.a[i] << " ";
    }
    cout << endl;
}

// Convert a decimal number to another base and display the result
void convertRadice(int x, int radice) {
    if (radice < 2 || radice > 36) {
        cout << "Invalid base! Please choose a base between 2 and 36." << endl;
        return;
    }

    Stack s;
    init(s); // Initialize a new stack for the conversion

    // Compute the remainders and push them onto the stack
    while (x != 0) {
        int r = x % radice; // Compute the remainder
        push(s, r); // Push the remainder onto the stack
        x /= radice; // Divide the number by the base
    }

    // Display the result in reverse order (from top of the stack)
    cout << "Number in base " << radice << ": ";
    while (!isEmpty(s)) {
        int digit = pop(s);
        // Handle digits greater than 9 (e.g., A, B, C for hexadecimal)
        if (digit >= 10) {
            cout << char('A' + (digit - 10)); // Convert to character representation
        } else {
            cout << digit;
        }
    }
    cout << endl;
}

int main() {
    Stack s;
    init(s);

    // Test basic stack operations
    cout << "Pushing elements onto the stack..." << endl;
    push(s, 10);
    push(s, 20);
    push(s, 30);
    display(s);

    cout << "Popping element: " << pop(s) << endl;
    display(s);

    // Test the conversion function
    cout << "\nDecimal to Binary conversion of 25:" << endl;
    convertRadice(25, 2); // Convert 25 to binary

    cout << "\nDecimal to Hexadecimal conversion of 255:" << endl;
    convertRadice(255, 16); // Convert 255 to hexadecimal

    cout << "\nDecimal to Base-3 conversion of 45:" << endl;
    convertRadice(45, 3); // Convert 45 to base-3

    return 0;
}
