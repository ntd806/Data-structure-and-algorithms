// Sliding Window
// là một kỹ thuật tối ưu thường được sử dụng để xử lý các bài toán liên quan đến dãy con liên tiếp (subarray) hoặc chuỗi con liên tục (substring).
// Thay vì kiểm tra từng dãy con một cách thủ công (dùng vòng lặp lồng nhau với độ phức tạp O(n2),
// Sliding Window cho phép giảm độ phức tạp xuống còn O(n) bằng cách duyệt qua mảng hoặc chuỗi chỉ một lần.

// ⚙️ Cách hoạt động:
// Khởi tạo một "cửa sổ" (window) bắt đầu từ một phạm vi con nhỏ nhất (thường là một phần tử hoặc không chứa phần tử nào).
// Mở rộng cửa sổ: Thêm phần tử mới vào cửa sổ (di chuyển một con trỏ bên phải hoặc trái).
// Thu hẹp cửa sổ: Nếu cửa sổ vượt quá điều kiện cần kiểm tra, thì thu hẹp lại bằng cách di chuyển đầu kia của cửa sổ.
// Lặp lại bước 2–3 cho đến khi toàn bộ dãy được duyệt qua.
// 📌 Ví dụ điển hình: Tìm tổng lớn nhất của dãy con dài k phần tử
// Đề bài: Cho mảng số nguyên và một số nguyên 𝑘, tìm tổng lớn nhất của một dãy con liên tiếp có độ dài k.

// Ý tưởng giải quyết:

// Tính tổng của dãy con đầu tiên dài 
// 𝑘
// k phần tử.
// Dịch chuyển cửa sổ sang phải một phần tử:
// Trừ đi phần tử đầu tiên của cửa sổ.
// Cộng thêm phần tử tiếp theo.
// Cập nhật tổng lớn nhất nếu tổng hiện tại lớn hơn.
// Lặp lại cho đến hết mảng.
// Code minh họa:

public class MaxSubarraySum {
    public static int maxSubarraySum(int[] arr, int k) {
        int maxSum = 0;
        int windowSum = 0;

        // Tính tổng của cửa sổ đầu tiên
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Trượt cửa sổ
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i]; // Trừ phần tử cũ, cộng phần tử mới
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Max subarray sum of size " + k + " is: " + maxSubarraySum(arr, k));
    }
}

// Ví dụ
// const numbers = [2, 1, 5, 1, 3, 2];
// console.log(maxSubarraySum(numbers, 3));  // Output: 9 ([5, 1, 3])
// ⚡ Ứng dụng phổ biến của Sliding Window:
// Tìm tổng lớn nhất (hoặc nhỏ nhất) của dãy con liên tiếp có độ dài cố định.
// Tìm chuỗi con dài nhất thỏa mãn điều kiện (ví dụ: không chứa ký tự trùng lặp).
// Tìm số lượng dãy con thỏa mãn điều kiện (ví dụ: tổng nhỏ hơn hoặc bằng một giá trị).
// Tính trung bình trượt trong các bài toán xử lý dữ liệu thời gian thực.
// 📈 Độ phức tạp:
// Thời gian: O(n) — Mỗi phần tử chỉ được thêm và loại bỏ khỏi cửa sổ đúng một lần.
// Không gian: O(1) — Chỉ cần một vài biến lưu trữ tổng và kết quả.
