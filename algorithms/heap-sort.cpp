#include <iostream>
using namespace std;

void swap(int &a, int &b) {
	int temp = a;
	a = b;
	b = temp;
}

void maxHeap(int a[], int n) {
	int largest = (n - 2)/2;
	while (largest >= 0)
	{
		int left = 2 * largest + 1;
		int right = 2 * largest + 2;

		if (left < n && a[largest] < a[left])  {
			swap(a[largest], a[left]);
		}

		if (right < n && a[largest] < a[right]) {
			swap(a[largest], a[right]);
		}

		largest--;
	}
}

void heapSort(int a[], int n) {
	for (int i = n; i > 0; i--) {
		maxHeap(a, i);
		swap(a[0], a[i - 1]);
	}
}

void display(int a[], int n) {
	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
	cout << endl;
}

int main() {
	int a[] = {4, 10, 3, 5, 1};
	int n = sizeof(a) / sizeof(a[0]);
	heapSort(a, n);
	display(a, n);
	return 0;
}
