#include <iostream>
#define MAX 1000
using namespace std;

void enter_solid_list(int a[], int &n) {
    cout << "Enter n = ";
    cin >> n;
    for (int i = 0; i < n; i++) {
        cout << "Enter element " << i + 1 << ": ";
        cin >> a[i];
    }
}

void show_solid_list(const int a[], const int n) {
    cout << "Elements in the list are: ";
    for (int i = 0; i < n; i++) {
        cout << a[i] << " ";
    }
    cout << endl;
}

// bài tập 
// tìm giá trị x ở vị trí đầu tiên
int timViTriDau (const int a[], const int n, const int x) {
    int vitri = -1;
    for (int i=0; i<n; i++) {
        if (a[i] == x) {
            vitri = a[i];
            break;
        }
    }

    return vitri;
}

// tìm giá  trị x ở vị trí cuối cùng >> có 2 cách duyệt 
// xoá giá trị x ở vị trí thứ i
void xoaViTriI (int a[], int &n, int i) {
    // tại vì mảng bắt đầu từ 0 đến n-1 nên phải có điều kiện này để kiểm tra
    if (i < 0 || i >= n) {
        cout << "Vị trí không hợp lệ.\n";
        return;
    }

    for (int j = i; j < n-1; j++) {
        // lấy ở vị trí đằng sau chèn vào vị trí cần xoá và tăng dần lên
        a[j] = a[j + 1];
    }
    // giảm đi chiều dài của n một đơn vị
    n--;
}

int main() {
    int n;
    int a[MAX]; // Array size is now set correctly
    enter_solid_list(a, n);
    show_solid_list(a, n); // Call to display the elements
    return 0;
}





