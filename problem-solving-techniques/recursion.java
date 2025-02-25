// Recursion là kỹ thuật trong đó một hàm tự gọi chính nó nhằm giải quyết một bài toán bằng cách chia bài toán đó thành các bài toán nhỏ hơn,
// tương tự với bản chất ban đầu. Recursion thường đi kèm với các chiến lược như chia để trị (Divide and Conquer) 
// và backtracking để giải quyết các bài toán phức tạp.

// ⚙️ Các thành phần chính của đệ quy:
// Base Case (Trường hợp cơ sở):
// Đây là điều kiện dừng, giúp ngăn không cho hàm gọi chính nó vô hạn. Khi đạt đến base case, hàm trả về một giá trị cụ thể mà không cần gọi lại hàm.
// Recursive Case (Trường hợp đệ quy):
// Đây là phần mà hàm tự gọi chính nó với các giá trị hoặc trạng thái thay đổi, tiến dần đến base case.
// 📌 Ứng dụng của Recursion:
// Chia để trị (Divide and Conquer):
// Phân tách bài toán lớn thành các bài toán con nhỏ hơn, giải quyết từng phần riêng biệt rồi kết hợp kết quả.
// Ví dụ: Merge Sort, Quick Sort.

// Backtracking:
// Sử dụng đệ quy để thử nghiệm tất cả các khả năng, kết hợp với cắt tỉa (pruning) khi phát hiện nhánh không thể dẫn đến lời giải.
// Ví dụ: Sinh hoán vị, giải Sudoku, bài toán N-Queens.

// 📌 Ví dụ 1: Tính giai thừa (Factorial) bằng đệ quy
public class Factorial {
    // Base case: 0! = 1
    public static int factorial(int n) {
        if (n == 0) {
            return 1; // Trường hợp cơ sở
        }
        // Recursive case: n! = n * (n-1)!
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println("Giai thừa của " + number + " là: " + factorial(number));
    }
}
// Giải thích:

// Khi n bằng 0, hàm trả về 1 (base case).
// Mỗi lời gọi hàm sẽ giảm n cho đến khi đạt đến base case.
// 📌 Ví dụ 2: Merge Sort (Chia để trị) bằng đệ quy

public class MergeSort {
    // Hàm sắp xếp mảng bằng merge sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) { // Base case: Mảng có 1 hoặc 0 phần tử
            return;
        }
        
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);       // Sắp xếp nửa trái
        mergeSort(arr, mid + 1, right);    // Sắp xếp nửa phải
        merge(arr, left, mid, right);      // Hợp nhất 2 nửa đã sắp xếp
    }

    // Hàm hợp nhất 2 nửa mảng đã sắp xếp
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy dữ liệu vào mảng tạm
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightArr.length; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        // Hợp nhất lại mảng
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(arr, 0, arr.length - 1);
        System.out.print("Mảng sau khi sắp xếp: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
// Giải thích:

// Mảng được chia thành 2 nửa cho đến khi mỗi phần chỉ chứa 1 phần tử (base case).
// Sau đó, các phần được hợp nhất lại theo thứ tự tăng dần.
// 📌 Ví dụ 3: Sinh hoán vị bằng Backtracking và đệ quy

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    // Hàm sinh tất cả hoán vị của mảng số nguyên
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, path, result);
        return result;
    }
    
    // Hàm đệ quy Backtracking
    private static void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {  // Base case: Đã chọn đủ phần tử
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // Nếu phần tử đã được sử dụng, bỏ qua
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, used, path, result);  // Gọi đệ quy
            path.remove(path.size() - 1);  // Quay lui
            used[i] = false;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        System.out.println("Các hoán vị của mảng:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }
}
// Giải thích:

// Hàm backtrack thử tất cả các khả năng sắp xếp các phần tử của mảng.
// Mỗi lần gọi đệ quy sẽ thêm một phần tử vào path cho đến khi đạt đủ độ dài của mảng (base case), sau đó quay lui để thử nghiệm các khả năng khác.
// 🎯 Lợi ích của việc sử dụng Recursion:
// Tính tự nhiên: Đặc biệt phù hợp với các bài toán có cấu trúc lặp lại hoặc phân rã như cây, danh sách liên kết, tổ hợp, v.v.
// Giảm phức tạp: Giúp code trở nên ngắn gọn và dễ hiểu hơn so với sử dụng các vòng lặp lồng nhau.
// Dễ dàng triển khai: Khi bài toán có thể chia thành các bài toán con giống nhau, recursion là một lựa chọn tự nhiên.
// ⚠️ Lưu ý khi sử dụng Recursion:
// Base Case: Luôn đảm bảo có điều kiện dừng để tránh lặp vô hạn và gây ra lỗi tràn ngăn xếp (Stack Overflow).
// Hiệu quả: Đôi khi, đệ quy có thể không tối ưu về mặt bộ nhớ so với giải pháp lặp, đặc biệt khi có nhiều lời gọi đệ quy sâu.
// Recursion là một kỹ thuật mạnh mẽ và linh hoạt, đặc biệt hữu ích khi giải quyết các bài toán phức tạp như chia để trị và backtracking. 
// Bạn có thể thử vận dụng trong nhiều bài toán khác nhau để hiểu rõ hơn về cách hoạt động của nó.