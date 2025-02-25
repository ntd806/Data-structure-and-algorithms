// Divide and Conquer là một chiến lược giải quyết bài toán bằng cách chia bài toán lớn thành các bài toán con nhỏ hơn,
// giải quyết từng bài toán con một cách độc lập, sau đó kết hợp các kết quả của bài toán con lại để tạo thành lời giải của bài toán ban đầu.

// ⚙️ Các bước chính trong Divide and Conquer:
// Chia (Divide):
// Chia bài toán ban đầu thành các bài toán con nhỏ hơn.
// Giải (Conquer):
// Giải quyết từng bài toán con một cách đệ quy.
// Nếu bài toán con đủ nhỏ (base case), giải trực tiếp mà không cần chia nhỏ thêm.
// Kết hợp (Combine):
// Kết hợp các kết quả của các bài toán con để tạo thành lời giải cho bài toán ban đầu.
// 📌 Ví dụ 1: Merge Sort
// Merge Sort là một thuật toán sắp xếp điển hình sử dụng phương pháp Chia để trị.

// Chia: Chia mảng cần sắp xếp thành hai nửa.
// Giải: Gọi đệ quy để sắp xếp từng nửa.
// Kết hợp: Hợp nhất hai nửa đã sắp xếp lại với nhau.
// Mã Java cho Merge Sort:

import java.util.Arrays;
public class MergeSort {
    // Hàm sắp xếp mảng bằng merge sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {  // Base case: mảng có 1 hoặc 0 phần tử
            return;
        }
        
        int mid = left + (right - left) / 2;
        // Chia mảng thành hai nửa và sắp xếp từng nửa
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        // Kết hợp hai nửa đã sắp xếp
        merge(arr, left, mid, right);
    }

    // Hàm hợp nhất hai mảng con đã sắp xếp
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Sao chép dữ liệu vào mảng tạm
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        // Hợp nhất các mảng tạm lại vào mảng chính
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        // Sao chép các phần tử còn lại (nếu có)
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Mảng sau khi sắp xếp (Merge Sort): " + Arrays.toString(arr));
    }
}
// 📌 Ví dụ 2: Quick Sort
// Quick Sort cũng là một thuật toán sắp xếp dựa trên chiến lược chia để trị.

// Chia: Chọn một phần tử làm pivot và sắp xếp lại mảng sao cho các phần tử nhỏ hơn pivot nằm bên trái, các phần tử lớn hơn nằm bên phải.
// Giải: Gọi đệ quy để sắp xếp hai nửa mảng.
// Kết hợp: Sau khi sắp xếp, mảng đã được phân chia và sắp xếp theo thứ tự tăng dần.
// Mã Java cho Quick Sort:
import java.util.Arrays;

public class QuickSort {
    // Hàm sắp xếp mảng bằng quick sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);  // Chọn pivot và phân chia mảng
            quickSort(arr, low, pivotIndex - 1);   // Sắp xếp nửa bên trái
            quickSort(arr, pivotIndex + 1, high);    // Sắp xếp nửa bên phải
        }
    }

    // Hàm phân chia mảng dựa trên pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Chọn phần tử cuối làm pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Đưa pivot về đúng vị trí của nó
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Mảng sau khi sắp xếp (Quick Sort): " + Arrays.toString(arr));
    }
}
// 🎯 Lợi ích của Divide and Conquer:
// Hiệu quả: Giúp giải quyết bài toán lớn bằng cách chia nhỏ thành các bài toán con dễ quản lý hơn.
// Đệ quy tự nhiên: Các bài toán có cấu trúc đệ quy (như sắp xếp, tìm kiếm) trở nên dễ dàng triển khai.
// Phân chia công việc: Phù hợp với các bài toán có thể thực hiện song song (parallelism) để tối ưu hóa thời gian chạy.
// Divide and Conquer là một phương pháp thiết yếu trong lập trình giải thuật, giúp tối ưu hóa thời gian và xử lý các bài toán phức tạp 
// thông qua việc chia nhỏ và kết hợp kết quả. Bạn có thể áp dụng chiến lược này trong nhiều bài toán khác như tìm kiếm, nhân ma trận, 
// và nhiều ứng dụng tối ưu khác.