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

        if (solidList.a[mid] == x) return mid;

        if (solidList.a[mid] < x) {
            left = mid + 1;
        } else {
            right = mid - 1;
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
// 1 phép hoán vị bằng 3 phép gán
void swap(int &a, int &b){
    int temp = a;
    a = b;
    b = temp;
}
// Độ phức tạp
//Trường hợp tốt nhất: Khi mảng đã được sắp xếp, thuật toán vẫn thực hiện tất cả các phép so sánh, nên độ phức tạp vẫn là O(n2).
//Trường hợp xấu nhất: Độ phức tạp vẫn là O(n2) khi mảng hoàn toàn ngẫu nhiên.
// không phát hiện được danh sách đã có thứ tự
void selectionSort (int a[], int n) {
  for (int i = 0; i < n - 1; i++) {
    int min_pos = i;  // Lưu vị trí của phần tử nhỏ nhất
    for (int j = i + 1; j < n; j++) {
        if (a[j] < a[min_pos]) {
            min_pos = j;  // Cập nhật vị trí của phần tử nhỏ nhất
        }
    }
    if (min_pos != i) {
      swap(a[i], a[min_pos]);  // Hoán đổi a[i] với a[min_pos]
    }
}

// insertion sort
void insertionSort(SolidList solidLis){
    for (int i=1; i<solidLis.n; i++) {
        int key = solidLis.a[i];
        int pos = i-1;

        while (pos >= 0 && a[pos] > key)
        {
            a[pos + 1] = a[pos];
            pos--;
        }
        a[pos+1] = key;
    }
}

// interchangeSort
void interchangeSort(int a[], int n) {
    for (int i = 0; i < n-1; i++)
    {
        for (int j = i+1; j < n; j--)
        {
            if (a[i] > a[j])
            {
               swap(a[i], a[j]);
            }
            
        }
        
    }
}

// bubble sort
void bubbleSort(int a[], int n) {
    for (int i = 0; i < n; i++)
    {
        for (int j = n-1; j > i; j--)
        {
            if (a[j] >  a[j-1])
            {
                swap(a[j], a[j-1]);
            }
            
        }
        
    }
}

// Quick sort
void quickSort(int a[], int left, int right) {
    int pivot = a[(left + right) / 2];
    int l = left, r = right;
    
    do {
        while (a[l] < pivot) l++;
        while (a[r] > pivot) r--;
        
        if (l <= r) {
            swap(a[l], a[r]);
            l++;
            r--;
        }
    } while (l <= r);

    if (left < r) quickSort(a, left, r);
    if (l < right) quickSort(a, l, right);
}
// heap sort

int main() {
    SolidList solidList;
    enter_solid_list(solidList);
    show_solid_list(solidList);
    return 0;
}
