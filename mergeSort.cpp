#include <iostream>

// Hàm trộn hai mảng đã sắp xếp
int* merge(const int arr1[], int size1, const int arr2[], int size2) {
    int* mergedArray = new int[size1 + size2];
    int i = 0, j = 0, k = 0;

    while (i < size1 && j < size2) {
        if (arr1[i] <= arr2[j]) {
            mergedArray[k++] = arr1[i++];
        } else {
            mergedArray[k++] = arr2[j++];
        }
    }

    while (i < size1) {
        mergedArray[k++] = arr1[i++];
    }

    while (j < size2) {
        mergedArray[k++] = arr2[j++];
    }

    return mergedArray;
}

// Hàm mergeSort trả về con trỏ đến mảng đã sắp xếp
int* mergeSort(int arr[], int size) {
    if (size <= 1) {
        int* sortedArray = new int[size];
        if (size == 1) {
            sortedArray[0] = arr[0];
        }
        return sortedArray;
    }

    int mid = size / 2;
    int* left = new int[mid];
    int* right = new int[size - mid];

    // Sao chép phần tử vào mảng con trái và phải
    for (int i = 0; i < mid; ++i) {
        left[i] = arr[i];
    }
    for (int i = mid; i < size; ++i) {
        right[i - mid] = arr[i];
    }

    // Đệ quy sắp xếp hai nửa
    int* sortedLeft = mergeSort(left, mid);
    int* sortedRight = mergeSort(right, size - mid);

    // Hợp nhất hai nửa đã sắp xếp
    int* sortedArray = merge(sortedLeft, mid, sortedRight, size - mid);

    // Giải phóng bộ nhớ tạm
    delete[] left;
    delete[] right;
    delete[] sortedLeft;
    delete[] sortedRight;

    return sortedArray;
}

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
}

int main() {
    int arr[] = {38, 27, 43, 3, 9, 82, 10};
    int size = sizeof(arr) / sizeof(arr[0]);

    int* sortedArray = mergeSort(arr, size);

    // In mảng đã sắp xếp
    printArray(sortedArray, size);

    // Giải phóng bộ nhớ của mảng đã sắp xếp
    delete[] sortedArray;

    return 0;
}
