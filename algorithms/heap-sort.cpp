// Nguyên lý hoạt động của Thuật toán Heap Sort
// Heap Sort là một thuật toán sắp xếp sử dụng cấu trúc dữ liệu heap (cây nhị phân) để sắp xếp một dãy số theo thứ tự tăng dần hoặc giảm dần. 
// Đặc biệt, Heap Sort sử dụng max-heap (cây nhị phân, trong đó mỗi nút cha có giá trị lớn hơn hoặc bằng các nút con của nó) để thực hiện quá trình sắp xếp.

// Các bước chính trong thuật toán Heap Sort:
// Xây dựng max-heap (Cấu trúc cây max-heap):

// Bắt đầu từ nửa cây, tức là bắt đầu từ các nút lá, di chuyển ngược lên để duy trì tính chất max-heap.
// Trong hàm maxHeap, ta kiểm tra hai con của nút (trái và phải), nếu giá trị của con nào lớn hơn giá trị của nút hiện tại, ta hoán đổi chúng. 
// Sau đó, tiếp tục kiểm tra lại với các con của nó cho đến khi đạt được cấu trúc max-heap.
// Sắp xếp dần dần:

// Sau khi tạo được max-heap, phần tử lớn nhất (tại gốc cây) sẽ là phần tử đầu tiên trong mảng. 
// Ta hoán đổi nó với phần tử cuối cùng trong mảng (phần tử chưa sắp xếp).
// Tiếp theo, ta giảm kích thước của heap (bỏ qua phần tử đã được sắp xếp) và tái cấu trúc lại max-heap.
// Lặp lại quá trình trên cho đến khi toàn bộ mảng được sắp xếp.
// Hoàn tất:

// Mảng cuối cùng sẽ được sắp xếp theo thứ tự tăng dần.
// Đoạn mã phân tích:
// Hàm swap: Hoán đổi giá trị của hai phần tử trong mảng.
// Hàm maxHeap: Xây dựng max-heap từ một mảng con. Duy trì tính chất heap của cây (nút cha lớn hơn các nút con).
// Hàm heapSort: Thực hiện thuật toán Heap Sort bằng cách gọi hàm maxHeap và hoán đổi phần tử đầu tiên với phần tử cuối cùng của mảng chưa được sắp xếp.
// Hàm display: In mảng sau khi sắp xếp.
// Các bước trong mã:
// Mảng ban đầu: {4, 10, 3, 5, 1}.
// heapSort sẽ thực hiện xây dựng max-heap cho mảng và sau đó hoán đổi gốc của cây với phần tử cuối cùng.
// Quá trình tái cấu trúc heap và hoán đổi sẽ tiếp tục cho đến khi mảng được sắp xếp hoàn chỉnh.
// Ứng dụng của Thuật toán Heap Sort
// Sắp xếp mảng:

// Heap Sort là một thuật toán sắp xếp phổ biến và hiệu quả với độ phức tạp thời gian là O(n log n) trong mọi trường hợp.
//  Nó không bị ảnh hưởng bởi thứ tự ban đầu của dữ liệu (khác với Quick Sort).
// Quản lý bộ nhớ:

// Heap được sử dụng trong các hệ thống quản lý bộ nhớ để phân phối và giải phóng bộ nhớ. 
// Ví dụ, heap được sử dụng trong việc quản lý phân bổ bộ nhớ động (dynamic memory allocation).
// Cấu trúc dữ liệu ưu tiên:

// Heap là nền tảng cho việc triển khai hàng đợi ưu tiên (priority queue), 
// nơi các phần tử được lấy ra theo thứ tự ưu tiên (phần tử có độ ưu tiên cao nhất được lấy ra đầu tiên). 
// Đặc biệt, max-heap thường được sử dụng để triển khai hàng đợi ưu tiên theo thứ tự giảm dần.
// Thuật toán tìm kiếm tối ưu (Huffman Coding):

// Heap Sort và cấu trúc heap có thể được sử dụng trong các thuật toán như Mã hóa Huffman (Huffman Coding), 
// thuật toán nén dữ liệu, nơi mà các phần tử cần được xử lý theo một thứ tự ưu tiên.
// Sắp xếp trong môi trường có bộ nhớ hạn chế:

// Heap Sort không yêu cầu bộ nhớ ngoài mảng gốc, điều này làm cho nó hữu ích trong các hệ thống với bộ nhớ hạn chế.

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
