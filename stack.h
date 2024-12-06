#ifndef STACK_H
#define STACK_H

#define MAX 1000

struct Stack {
    int a[MAX];
    int top;
};

void init(Stack &s);
bool isEmpty(Stack s);
bool isFull(Stack s);
void push(Stack &s, int x);
int pop(Stack &s);
void display(Stack s);
void convertRadice(int x, int radice);

#endif
