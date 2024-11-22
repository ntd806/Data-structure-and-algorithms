#include <iostream>
void show(int a[], int n){
 for (int i = 0; i < n; i++) {
        std::cout << a[i] << " ";  // In kết quả đã sắp xếp
    }
    std::cout << std::endl;
}

int maxNumber(int a[], int n) {
    int max = 0;  // Khởi tạo max là 0
    for (int i = 1; i < n; i++) {
        if (a[max] < a[i]) {
            max = i;
        }
    }
    return a[max];  // Trả về giá trị lớn nhất
}

int countDigits(int number, int base) {
    int count = 0;
    while (number > 0) {
        number /= base;
        count++;
    }

    return count > 0 ? count : 1;
}

int power(int base, int exponent) {
    int result = 1;

    // Nếu số mũ âm
    if (exponent < 0) {
        base = 1 / base;   // Đảo ngược cơ số
        exponent = -exponent; // Chuyển số mũ thành dương
    }

    for (int i = 0; i < exponent; i++) {
        result *= base;
    }

    return result;
}

int extractDigits(int number, int digit, int base) {
    return (number / power(base, digit)) % base;
}

int* radixSort(int a[], int n) {
    int maxVal = maxNumber(a, n);  // Lấy giá trị lớn nhất trong mảng
    int digitCount = countDigits(maxVal, 10);  // Tính số chữ số của giá trị lớn nhất trong cơ số 10

    int* output = new int[n];  // Mảng phụ lưu trữ kết quả sau khi sắp xếp theo từng chữ số

    for (int digit = 0; digit < digitCount; digit++) {
        int count[10][2];  // Mảng 2 chiều lưu chữ số và số lượng xuất hiện của nó
        
        // Khởi tạo mảng count
        for (int i = 0; i < 10; i++) {
            count[i][0] = i;       // Lưu chữ số từ 0 đến 9
            count[i][1] = 0;       // Khởi tạo số lượng xuất hiện bằng 0
        }

        // Đếm số lượng xuất hiện của mỗi chữ số trong mảng
        for (int i = 0; i < n; i++) {
            int d = extractDigits(a[i], digit, 10);
            count[d][1]++;  // Tăng số lượng xuất hiện của chữ số d
        }

        // Cộng dồn để xác định vị trí cuối cùng của mỗi chữ số
        for (int i = 1; i < 10; i++) {
            count[i][1] += count[i - 1][1];
        }

        // Xây dựng mảng output theo thứ tự sắp xếp của chữ số hiện tại
        for (int i = n - 1; i >= 0; i--) {
            int d = extractDigits(a[i], digit, 10);
            output[count[d][1] - 1] = a[i];
            count[d][1]--;  // Giảm số lượng vị trí của chữ số d
        }

        // Sao chép kết quả từ mảng output vào mảng a
        for (int i = 0; i < n; i++) {
            a[i] = output[i];
        }
    }

    delete[] output;  // Giải phóng mảng phụ

    return a;  // Trả về mảng đã sắp xếp
}

int main() {
    int a[] = {0, 16, 37, 18, 99, 100, 77};
    int n = sizeof(a) / sizeof(a[0]);
    
    radixSort(a, n);

    show(a,n);

    return 0;
}
