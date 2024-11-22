#include <iostream>
using namespace std;

int maxNumber(int a[], int n) {
    int max = 0;  // Assume the first element is the largest
    for (int i = 1; i < n; i++) {
        if (a[max] < a[i]) {
            max = i;
        }
    }
    return a[max];  // Return the largest value
}

int* countingSort(int a[], int n) {
    int max = maxNumber(a, n);
    int* c = new int[max + 1]();  // Create and initialize count array to 0
    int* b = new int[n];          // Create output array

    // Count occurrences of each value
    for (int i = 0; i < n; i++) {
        c[a[i]]++;
    }

    // Accumulate counts
    for (int i = 1; i <= max; i++) {
        c[i] += c[i - 1];
    }

    // Build the sorted array
    for (int i = n - 1; i >= 0; i--) {
        b[c[a[i]] - 1] = a[i];
        c[a[i]]--;
    }

    // Free memory for the count array
    delete[] c;

    return b;  // Return sorted array
}

int main() {
    int a[] = {4, 2, 2, 8, 3, 3, 1};
    int n = sizeof(a) / sizeof(a[0]);

    int* sortedArray = countingSort(a, n);

    cout << "Sorted array: ";
    for (int i = 0; i < n; i++) {
        cout << sortedArray[i] << " ";
    }

    delete[] sortedArray;  // Free the memory for sorted array

    return 0;
}
