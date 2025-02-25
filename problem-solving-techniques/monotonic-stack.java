// Monotonic Stack là một kỹ thuật sử dụng ngăn xếp (stack) để giải quyết các bài toán liên quan đến việc tìm phần tử gần nhất (gần hơn theo vị trí) thoả mãn một điều kiện nào đó, như:

// Phần tử lớn hơn gần nhất (Next Greater Element - NGE)
// Phần tử nhỏ hơn gần nhất (Next Smaller Element - NSE)
// Monotonic Stack duy trì một ngăn xếp theo thứ tự đơn điệu (giảm dần hoặc tăng dần) để dễ dàng tìm phần tử gần nhất theo yêu cầu, từ đó giúp giảm độ phức tạp từ O(n2) xuống O(n).

// ⚙️ Cách hoạt động của Monotonic Stack:
// Khởi tạo một ngăn xếp trống.
// Duyệt qua từng phần tử trong mảng:
// Nếu phần tử hiện tại lớn hơn (hoặc nhỏ hơn) phần tử trên đỉnh ngăn xếp, thì:
// Lấy phần tử trên đỉnh ra khỏi ngăn xếp (pop) vì nó không còn hữu ích.
// Đẩy phần tử hiện tại vào ngăn xếp (push).
// Kết quả sẽ được lưu trữ hoặc xử lý ngay khi duyệt qua từng phần tử.
// 📌 Ví dụ điển hình: Tìm phần tử lớn hơn gần nhất (Next Greater Element - NGE)
// Đề bài: Cho một mảng số nguyên, tìm phần tử lớn hơn gần nhất ở bên phải cho mỗi phần tử. Nếu không có, trả về -1.

// Ý tưởng giải quyết:

// Duyệt từ cuối mảng về đầu (ngược lại).
// Dùng ngăn xếp để lưu các phần tử chưa tìm được NGE.
// Với mỗi phần tử:
// Loại bỏ các phần tử nhỏ hơn hoặc bằng nó ra khỏi ngăn xếp (pop).
// Phần tử trên đỉnh ngăn xếp chính là NGE của nó.
// Đẩy phần tử hiện tại vào ngăn xếp.
// Code minh họa:

import java.util.Stack;
import java.util.Arrays;

public class NextGreaterElement {
    public static int[] nextGreaterElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        Arrays.fill(result, -1); // Khởi tạo tất cả các phần tử bằng -1

        for (int i = arr.length - 1; i >= 0; i--) {
            // Loại bỏ phần tử nhỏ hơn hoặc bằng phần tử hiện tại
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            // Nếu stack không rỗng, phần tử trên cùng là NGE
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            // Đẩy phần tử hiện tại vào stack
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 25};
        int[] result = nextGreaterElement(arr);

        System.out.println("Next Greater Elements:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(arr[i] + " -> " + result[i]);
        }
    }
}

// Ví dụ
// const numbers = [2, 1, 2, 4, 3];
// console.log(nextGreaterElement(numbers));  // Output: [4, 2, 4, -1, -1]
// Giải thích:

// Phần tử lớn hơn gần nhất của 2 là 4.
// Phần tử lớn hơn gần nhất của 1 là 2.
// Phần tử lớn hơn gần nhất của 4 là không có, nên trả về -1.
// 🔥 Các loại Monotonic Stack:
// Monotonic Increasing Stack (Ngăn xếp tăng dần):

// Phần tử nhỏ hơn sẽ bị loại bỏ.
// Dùng để tìm Next Greater Element hoặc Previous Greater Element.
// Monotonic Decreasing Stack (Ngăn xếp giảm dần):

// Phần tử lớn hơn sẽ bị loại bỏ.
// Dùng để tìm Next Smaller Element hoặc Previous Smaller Element.
// ⚡ Ứng dụng thực tế của Monotonic Stack:
// Tìm Next Greater/Smaller Element trong mảng.
// Bài toán Histogram (Tìm diện tích hình chữ nhật lớn nhất trong biểu đồ).
// Bài toán Trapping Rain Water (Tính lượng nước mưa đọng lại giữa các cột).
// Tìm khoảng cách ngắn nhất đến phần tử lớn hơn hoặc nhỏ hơn tiếp theo.