#include <iostream>
#include <queue>

// Hiển thị mảng
void show(int a[], int n) {
    for (int i = 0; i < n; i++) {
        std::cout << a[i] << " ";
    }
    std::cout << std::endl;
}

// Tìm số lớn nhất trong mảng
int maxNumber(int a[], int n) {
    int max = 0;  // Khởi tạo max là 0
    for (int i = 1; i < n; i++) {
        if (a[max] < a[i]) {
            max = i;
        }
    }
    return a[max];  // Trả về giá trị lớn nhất
}

// Tính số chữ số của một số
int countDigits(int number, int base) {
    int count = 0;
    while (number > 0) {
        number /= base;
        count++;
    }

    return count > 0 ? count : 1;  // Ít nhất trả về 1 chữ số
}

// Hàm tính lũy thừa
int power(int base, int exponent) {
    int result = 1;

    for (int i = 0; i < exponent; i++) {
        result *= base;
    }

    return result;
}

// Trích xuất chữ số theo vị trí và cơ số
int extractDigits(int number, int digit, int base) {
    return (number / power(base, digit)) % base;
}

// Thuật toán Radix Sort
int* radixSort(int a[], int n) {
    int maxVal = maxNumber(a, n);  // Lấy giá trị lớn nhất trong mảng
    int digitCount = countDigits(maxVal, 10);  // Tính số chữ số của giá trị lớn nhất trong cơ số 10

    std::queue<int> queueArray[10];  // 10 queue để sắp xếp theo cơ số 10

    for (int digit = 0; digit < digitCount; digit++) {
        // Phân phối các phần tử vào các queue theo chữ số hiện tại
        for (int i = 0; i < n; i++) {
            int digitValue = extractDigits(a[i], digit, 10);
            queueArray[digitValue].push(a[i]);
        }

        // Thu thập các phần tử từ các queue quay lại mảng
        int index = 0;
        for (int i = 0; i < 10; i++) {
            while (!queueArray[i].empty()) {
                a[index++] = queueArray[i].front();
                queueArray[i].pop();
            }
        }
    }

    return a;  // Trả về mảng đã sắp xếp
}

int main() {
    int a[] = {0, 16, 37, 18, 99, 100, 77};
    int n = sizeof(a) / sizeof(a[0]);
    
    radixSort(a, n);

    show(a, n);

    return 0;
}
