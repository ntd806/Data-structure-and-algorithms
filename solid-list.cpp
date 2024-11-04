#include <iostream>
#define MAX 1000
using namespace std;

struct SolidList {
    int a[MAX];
    int n;
};

// Function to input elements into the SolidList
void enter_solid_list(SolidList &solidList) {
    cout << "Enter n = ";
    cin >> solidList.n;
    for (int i = 0; i < solidList.n; i++) {
        cout << "Enter element " << i + 1 << ": ";
        cin >> solidList.a[i];
    }
}

// Function to display elements in the SolidList
void show_solid_list(const SolidList &solidList) {
    cout << "Elements in the list are: ";
    for (int i = 0; i < solidList.n; i++) {
        cout << solidList.a[i] << " ";
    }
    cout << endl;
}

// Function to find the first occurrence of x
int timViTriDau(const SolidList solidList, const int x) {
    for (int i = 0; i < solidList.n; i++) {
        if (solidList.a[i] == x) {
            return i;
        }
    }
    return -1;
}

// Function to find the last occurrence of x by iterating from the start
int timViTriCuoiC1(const SolidList solidList, const int x) {
    int vitri = -1;
    for (int i = 0; i < solidList.n; i++) {
        if (solidList.a[i] == x) {
            vitri = i;
        }
    }
    return vitri;
}

// Function to find the last occurrence of x by iterating from the end
int timViTriCuoiC2(const SolidList solidList, const int x) {
    for (int i = solidList.n - 1; i >= 0; i--) {
        if (solidList.a[i] == x) {
            return i;
        }
    }
    return -1;
}

// Function to remove the element at index i
bool xoaViTri(SolidList &solidList, const int i) {
    if (i < 0 || i >= solidList.n) {
        return false;
    }
    for (int j = i; j < solidList.n - 1; j++) {
        solidList.a[j] = solidList.a[j + 1];
    }
    solidList.n--;
    return true;
}

// Function to insert element x at index i
bool themViTri(SolidList &solidList, int i, int x) {
    if (i < 0 || i > solidList.n || solidList.n >= MAX) {
        return false;
    }
    for (int j = solidList.n; j > i; j--) {
        solidList.a[j] = solidList.a[j - 1];
    }
    solidList.a[i] = x;
    solidList.n++;
    return true;
}

// Function to perform binary search on a sorted array
// O1 trường hợp tốt nhất là lần đầu tiên phần tử cần tìm ngay vị trí ở giữa x == mid
// tệ nhất là phần tử không có trong danh sách n = 2^k => k = Olog2 n
// k là số lần cắt ra thành cách đoạn con
// trường hợp trung bình nằm bất kì đâu Ologn/2
int binarySearch(const SolidList solidList, const int x) {
    int left = 0, right = solidList.n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2; // Prevents potential overflow

        if (solidList.a[mid] == x) {
            return mid; // Element found, return index
        } else if (solidList.a[mid] < x) {
            left = mid + 1; // Search in the right half
        } else {
            right = mid - 1; // Search in the left half
        }
    }
    return -1; // Element not found
}

// O1 trường hợp tốt nhất là lần đầu tiên phần tử cần tìm ngay vị trí ở đầu
// tệ nhất là phần tử không có trong danh sách On
int linearSearch (const SolidList solidList, const int x) {
    for (int i=0; i<solidList.n; i++) {
        if (solidList.a[i] == x) return i;
    }

    return -1;
}

int main() {
    SolidList solidList;
    enter_solid_list(solidList);
    show_solid_list(solidList);
    return 0;
}
