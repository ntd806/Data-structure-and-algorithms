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
// Ý tưởng
// Xét phần tử đầu tiên của dãy. Tìm phần tử nhỏ nhất trong các phần tử còn lại.
// Hoán đổi phần tử đầu tiên với phần tử nhỏ nhất này,
// ta được phần tử đầu tiên có vị trí đúng. Bỏ qua phần tử vừa được xét,
// tiếp tục xét đến phần tử kế tiếp và thực hiện đến hết dãy.
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

// Giải thích chi tiết các bước thực hiện:
// Bắt đầu từ phần tử thứ hai:
// Phần tử đầu tiên (a[0]) được coi là đã sắp xếp. Vì vậy, thuật toán bắt đầu với phần tử thứ hai (ở vị trí i = 1) và thực hiện chèn các phần tử từ a[1] đến a[n-1].
// Lưu giá trị cần chèn:
// Giá trị của phần tử tại chỉ số i (phần tử cần chèn) được lưu vào biến x.
// So sánh và dịch chuyển các phần tử lớn hơn x:
// Ta dùng biến j để di chuyển ngược về phía trước trong đoạn con đã sắp xếp (a[0] đến a[i-1]). Nếu a[j] > x, ta dịch chuyển a[j] sang phải (vị trí a[j+1]), tạo không gian cho x.
// Chèn phần tử vào vị trí đúng:
// Khi ta không còn tìm thấy phần tử nào lớn hơn x, hoặc đã di chuyển hết qua các phần tử đã sắp xếp, ta sẽ chèn x vào vị trí thích hợp tại a[j+1].
// Tiếp tục với phần tử tiếp theo:
// Thuật toán tiếp tục lặp lại các bước trên cho tất cả các phần tử còn lại trong mảng.
// Thời gian và không gian:
// Thời gian: Trong trường hợp tốt nhất (mảng đã sắp xếp), độ phức tạp là O(n). Trong trường hợp xấu nhất (mảng ngược), độ phức tạp là O(n²).
// Không gian: Độ phức tạp không gian là O(1) vì thuật toán thực hiện tại chỗ và không sử dụng bộ nhớ phụ.
void insertSort(int a[], int n) {
    // Bắt đầu từ phần tử thứ hai trong mảng (vì phần tử đầu tiên a[0] đã được coi là đã sắp xếp)
    for (int i = 1; i < n; i++) {
        // Lưu giá trị của phần tử tại vị trí i vào biến x
        // Đây là phần tử cần được chèn vào đoạn con đã sắp xếp (a[0], ..., a[i-1])
        int x = a[i]; 
        
        // j là chỉ số của phần tử cuối cùng trong đoạn con đã sắp xếp
        // ban đầu j = i - 1, tức là vị trí của phần tử liền kề với phần tử cần chèn
        int j = i - 1;
        
        // Tiến hành so sánh x với các phần tử trong đoạn con đã sắp xếp (từ a[j] đến a[0])
        // Nếu a[j] > x, dịch chuyển a[j] sang phải một vị trí để tạo chỗ cho x
        while (j >= 0 && a[j] > x) {
            // Dịch chuyển phần tử a[j] sang a[j + 1]
            // Điều này tạo ra một chỗ trống tại vị trí j để chèn x vào
            a[j + 1] = a[j];  
            
            // Tiếp tục kiểm tra phần tử kế tiếp trong đoạn con đã sắp xếp (giảm j)
            j--;  
        }
        
        // Sau khi dịch chuyển các phần tử lớn hơn x, chèn x vào vị trí j + 1
        // Tại thời điểm này, j là vị trí của phần tử lớn nhất nhỏ hơn x hoặc j = -1
        // Nên phần tử x sẽ được chèn vào vị trí j + 1
        a[j + 1] = x;
    }
}

// Thuật toán Bubble Sort (sắp xếp nổi bọt) cũng là một thuật toán sắp xếp đơn giản. Nó hoạt động bằng cách liên tục so sánh cặp phần tử liền kề và đổi chỗ chúng nếu không đúng thứ tự. Quá trình này lặp lại cho đến khi danh sách được sắp xếp.
// Cách hoạt động:
//     Duyệt qua danh sách từ đầu đến cuối.
//     So sánh từng cặp phần tử liền kề:
//         Nếu phần tử sau nhỏ hơn phần tử trước (nếu sắp xếp tăng dần), đổi chỗ chúng.
//     Sau mỗi lần duyệt qua danh sách, phần tử lớn nhất (hoặc nhỏ nhất) sẽ được đẩy (nổi) lên đúng vị trí.
//     Giảm phạm vi duyệt bằng cách bỏ qua phần tử cuối cùng đã được sắp xếp.
//     Lặp lại quá trình cho đến khi không còn cặp phần tử nào cần đổi chỗ.
// Ví dụ minh họa:
// Giả sử bạn có danh sách: [5, 3, 8, 6]
//     Lượt 1:
//         So sánh 5 và 3, đổi chỗ → [3, 5, 8, 6].
//         So sánh 5 và 8, không đổi chỗ.
//         So sánh 8 và 6, đổi chỗ → [3, 5, 6, 8]. Phần tử lớn nhất 8 đã "nổi lên" cuối danh sách.
//     Lượt 2:
//         So sánh 3 và 5, không đổi chỗ.
//         So sánh 5 và 6, không đổi chỗ. Phần tử lớn thứ hai 6 đã "nổi lên".
//     Lượt 3:
//         So sánh 3 và 5, không đổi chỗ. Danh sách đã sắp xếp: [3, 5, 6, 8].
// Đặc điểm:
//     Độ phức tạp thời gian:
//         Trường hợp tốt nhất (danh sách đã sắp xếp): O(n) (nếu có tối ưu dừng khi không đổi chỗ nào).
//         Trường hợp xấu nhất: O(n²) (nếu danh sách ngược).
//     Độ phức tạp không gian: O(1) (thuật toán tại chỗ).
//     Ứng dụng: Ít được dùng trong thực tế do hiệu suất kém, nhưng dễ cài đặt và hữu ích để học thuật toán cơ bản.
void bubbleSort(int a[], int n) {
    // Duyệt qua từng phần tử của mảng, từ đầu đến cuối
    for (int i = 0; i < n; i++) { 
        // Duyệt ngược từ cuối mảng về vị trí i (phần đã được sắp xếp)
        for (int j = n - 1; j > i; j--) { 
            // So sánh hai phần tử liền kề và hoán đổi nếu không đúng thứ tự
            if (a[j] < a[j - 1]) { 
                swap(a[j], a[j - 1]); 
            }
        }
    }
}

// Thuật toán Quick Sort (sắp xếp nhanh) là một trong những thuật toán sắp xếp hiệu quả nhất. 
// Nó hoạt động theo nguyên tắc "chia để trị", chia mảng thành các phần nhỏ hơn và sắp xếp từng phần độc lập.
// Cách hoạt động:
//     Chọn một phần tử làm "chốt" (pivot), thường là phần tử ở giữa mảng hoặc bất kỳ phần tử nào tùy ý.
//     Phân chia mảng:
//         Duyệt từ trái qua phải để tìm phần tử lớn hơn pivot.
//         Duyệt từ phải qua trái để tìm phần tử nhỏ hơn pivot.
//         Nếu tìm thấy, hoán đổi hai phần tử để đưa các phần tử nhỏ hơn pivot về bên trái, lớn hơn pivot về bên phải.
//     Sau khi phân chia xong:
//         Pivot nằm ở đúng vị trí trong mảng đã sắp xếp.
//         Tiếp tục thực hiện đệ quy để sắp xếp các phần còn lại ở bên trái và bên phải của pivot.
// Ví dụ minh họa:
// Giả sử bạn có danh sách: [7, 2, 1, 6, 8, 5, 3, 4]
//     Chọn pivot = 6 (phần tử giữa).
//     Lượt 1:
//         Phân chia: Duyệt từ trái và phải để tìm phần tử cần hoán đổi:
//         Kết quả sau hoán đổi: [5, 2, 1, 4, 3, 6, 8, 7]
//         Pivot 6 đã nằm đúng vị trí.
//     Đệ quy:
//         Sắp xếp mảng bên trái [5, 2, 1, 4, 3] và bên phải [8, 7].
//         Tiếp tục chia nhỏ và sắp xếp cho đến khi toàn bộ mảng được sắp xếp: [1, 2, 3, 4, 5, 6, 7, 8].
// Đặc điểm:
//     Độ phức tạp thời gian:
//         Trường hợp tốt nhất: O(n log n) (chia đều mảng ở mỗi bước).
//         Trường hợp xấu nhất: O(n²) (nếu pivot không tối ưu, mảng chia không cân bằng).
//     Độ phức tạp không gian: O(log n) (do sử dụng ngăn xếp để lưu trạng thái đệ quy).
//     Ứng dụng: Hiệu quả với mảng lớn, phổ biến trong thực tế do tốc độ nhanh và khả năng tùy chỉnh.
void quickSort(int a[], int left, int right) {
    // Chọn pivot là phần tử giữa mảng
    int pivot = a[(left + right) / 2]; 
    // Lấy chỉ số bắt đầu từ hai phía
    int l = left, r = right; 
    
    // Phân chia mảng thành hai phần
    do {
        // Tìm phần tử lớn hơn pivot ở bên trái
        while (a[l] < pivot) l++; 
        // Tìm phần tử nhỏ hơn pivot ở bên phải
        while (a[r] > pivot) r--; 
        
        // Nếu tìm được, hoán đổi và tiếp tục
        if (l <= r) { 
            swap(a[l], a[r]);
            l++;
            r--;
        }
    } while (l <= r);

    // Gọi đệ quy để sắp xếp hai phần đã phân chia
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
