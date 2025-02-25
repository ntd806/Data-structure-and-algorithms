// Kỹ thuật Top K Elements được sử dụng để tìm K phần tử lớn nhất hoặc nhỏ nhất trong một tập dữ liệu lớn mà không cần phải sắp xếp toàn bộ dãy.
// Ý tưởng chính:
// Sử dụng cấu trúc dữ liệu Heap:
// Min-Heap: Để tìm K phần tử lớn nhất (giữ phần tử nhỏ nhất trên đỉnh).
// Max-Heap: Để tìm K phần tử nhỏ nhất (giữ phần tử lớn nhất trên đỉnh).
// Độ phức tạp:
// Thời gian: (NlogK) — với N là số phần tử trong mảng.
// Không gian: O(K) — lưu trữ heap với K phần tử.
// 📌 Ví dụ 1: Tìm K phần tử lớn nhất bằng Min-Heap
// Bài toán: Tìm 3 phần tử lớn nhất trong mảng [4, 1, 7, 3, 8, 5, 9].

// Ý tưởng:
// Duy trì một Min-Heap kích thước K.
// Nếu phần tử hiện tại lớn hơn phần tử nhỏ nhất trên heap, thay thế nó.
// Kết quả cuối cùng là các phần tử trong heap.
// Code (Java):
import java.util.PriorityQueue;

public class TopKElements {
    public static int[] findTopKElements(int[] nums, int k) {
        // Tạo Min-Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();  // Xóa phần tử nhỏ nhất
                minHeap.offer(num);  // Thêm phần tử lớn hơn
            }
        }

        // Chuyển kết quả sang mảng
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 7, 3, 8, 5, 9};
        int k = 3;
        int[] topK = findTopKElements(nums, k);
        System.out.print("Top " + k + " phần tử lớn nhất là: ");
        for (int num : topK) {
            System.out.print(num + " ");
        }
    }
}
// Giải thích:
// minHeap.peek(): Trả về phần tử nhỏ nhất trong heap.
// Nếu phần tử mới lớn hơn phần tử nhỏ nhất, ta thay thế nó.
// Kết quả là các phần tử lớn nhất nằm trong heap.
// 📌 Ví dụ 2: Tìm K phần tử nhỏ nhất bằng Max-Heap
// Bài toán: Tìm 3 phần tử nhỏ nhất trong mảng [4, 1, 7, 3, 8, 5, 9].

// Ý tưởng:
// Duy trì một Max-Heap kích thước K.
// Nếu phần tử hiện tại nhỏ hơn phần tử lớn nhất trên heap, thay thế nó.
// Code (Java):

import java.util.Collections;
import java.util.PriorityQueue;

public class TopKSmallestElements {
    public static int[] findTopKSmallest(int[] nums, int k) {
        // Tạo Max-Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();  // Xóa phần tử lớn nhất
                maxHeap.offer(num);  // Thêm phần tử nhỏ hơn
            }
        }

        // Chuyển kết quả sang mảng
        int[] result = new int[k];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            result[i++] = maxHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 7, 3, 8, 5, 9};
        int k = 3;
        int[] topK = findTopKSmallest(nums, k);
        System.out.print("Top " + k + " phần tử nhỏ nhất là: ");
        for (int num : topK) {
            System.out.print(num + " ");
        }
    }
}
// Giải thích:
// maxHeap.peek(): Trả về phần tử lớn nhất trong heap.
// Thay thế phần tử lớn nhất nếu phần tử mới nhỏ hơn.
// 📈 So sánh với cách sắp xếp thông thường:
// Sắp xếp toàn bộ mảng có độ phức tạp O(NlogN).
// Sử dụng Heap giúp giảm độ phức tạp xuống còn O(NlogK), tốt hơn khi K≪N.
// 🚀 Ứng dụng của Top K Elements:
// Gợi ý sản phẩm trong thương mại điện tử (Top K sản phẩm phổ biến nhất).
// Gợi ý từ khóa tìm kiếm phổ biến nhất.
// Tìm các bài viết được tương tác nhiều nhất trên mạng xã hội.
