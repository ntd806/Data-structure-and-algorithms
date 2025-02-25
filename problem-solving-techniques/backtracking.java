// Backtracking là một kỹ thuật giải quyết bài toán bằng cách thử tất cả các khả năng (brute force) nhưng kết hợp 
// với việc cắt tỉa (pruning) để loại bỏ các nhánh không cần thiết,
// giúp giảm đáng kể số lượng trường hợp cần kiểm tra.

// Kỹ thuật này đặc biệt hiệu quả cho các bài toán tổ hợp, sắp xếp hoặc tìm kiếm, như:

// Sinh tổ hợp, hoán vị (permutation, combination)
// Bài toán Sudoku
// Bài toán N-Queens
// Bài toán Maze (mê cung)
// ⚙️ Cách hoạt động của Backtracking:
// Thử một lựa chọn (chọn một nhánh).
// Kiểm tra điều kiện (cắt tỉa):
// Nếu lựa chọn đó dẫn đến lời giải hợp lệ, tiếp tục.
// Nếu không hợp lệ, quay lui (backtrack) để thử lựa chọn khác.
// Lặp lại cho đến khi tìm được lời giải hoặc duyệt hết các khả năng.
// 📌 Ví dụ 1: Sinh tất cả các hoán vị của một mảng
// Bài toán: Cho một mảng số nguyên [1, 2, 3], hãy in ra tất cả các hoán vị của nó.

// Ý tưởng:

// Duyệt từng phần tử của mảng.
// Đánh dấu phần tử đã sử dụng (visited).
// Dùng đệ quy để sinh ra tất cả các hoán vị.
// Code minh họa (JavaScript):

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, path, result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path)); // Thêm một bản sao của đường dẫn
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // Bỏ qua các phần tử đã được dùng

            used[i] = true;
            path.add(nums[i]);  // Thêm phần tử vào đường dẫn
            backtrack(nums, used, path, result);  // Đệ quy
            path.remove(path.size() - 1);  // Quay lui
            used[i] = false;  // Đánh dấu lại là chưa sử dụng
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);

        // In kết quả
        System.out.println("Tất cả hoán vị:");
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }
}

// Giải thích:
// Hàm backtrack thử tất cả các khả năng.
// Khi đạt đến độ dài cần thiết, kết quả sẽ được thêm vào result.
// Quay lui (pop và gán lại false) để thử lựa chọn khác.
// 📌 Ví dụ 2: Giải Sudoku
// Bài toán: Điền các số từ 1 đến 9 vào một bảng 9x9 sao cho:

// Mỗi hàng, mỗi cột và mỗi ô vuông nhỏ 3x3 đều chứa đủ các số từ 1 đến 9.
// Ý tưởng:

// Tìm ô trống đầu tiên.
// Thử các số từ 1 đến 9.
// Kiểm tra điều kiện hợp lệ (không trùng trong hàng, cột, và ô vuông).
// Nếu hợp lệ, tiếp tục đệ quy.
// Nếu không, quay lui để thử số khác.
// Code minh họa (JavaScript):

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            // Kiểm tra hàng và cột
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }

            // Kiểm tra trong khối 3x3
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {  // Ô trống
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;  // Gán giá trị tạm thời

                            if (backtrack(board)) {
                                return true;
                            }

                            board[row][col] = '.';  // Quay lui
                        }
                    }
                    return false;  // Không thể đặt số hợp lệ
                }
            }
        }
        return true;  // Đã giải xong
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();

        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        solver.solveSudoku(board);

        // In kết quả
        System.out.println("Sudoku đã được giải:");
        for (char[] row : board) {
            for (char num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

// Giải thích:

// Hàm isValid kiểm tra xem số điền vào có hợp lệ không.
// Hàm backtrack thử điền các số vào các ô trống và quay lui nếu gặp lỗi.
// 📈 Độ phức tạp:
// Thời gian: Tùy thuộc vào bài toán, nhưng thường là O(kn) với:k: Số lượng lựa chọn tại mỗi bước. n: Số lượng bước cần thiết để hoàn thành lời giải.
// Không gian: O(n) — Độ sâu của đệ quy hoặc ngăn xếp.
// 🔥 Cắt tỉa (Pruning) hiệu quả:
// Giảm số lượng nhánh phải duyệt.
// Ví dụ: Trong bài toán sinh tổ hợp, nếu tổng hiện tại đã vượt quá mục tiêu, không cần tiếp tục thử thêm.
// 🎯 Khi nào nên dùng Backtracking?
// Khi cần tìm tất cả các khả năng thỏa mãn điều kiện.
// Khi bài toán có thể chia thành nhiều bước nhỏ và các bước sau phụ thuộc vào lựa chọn trước.
// Khi có thể loại bỏ các nhánh không cần thiết sớm bằng cách kiểm tra điều kiện hợp lệ.