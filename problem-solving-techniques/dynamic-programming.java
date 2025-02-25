// Dynamic Programming (DP) là một phương pháp giải quyết bài toán bằng cách chia nhỏ bài toán lớn thành các bài toán con nhỏ hơn và 
// lưu trữ kết quả trung gian (để không phải tính lại nhiều lần) nhằm tối ưu hóa thời gian chạy. DP thường được áp dụng cho các bài toán có tính chất con trùng
// lặp (overlapping subproblems) và cấu trúc tối ưu con (optimal substructure).

// ⚙️ Các bước cơ bản khi giải bài toán bằng DP:
// Xác định bài toán con (Subproblems):

// Phân chia bài toán lớn thành các bài toán con nhỏ hơn mà kết quả của chúng có thể được sử dụng để xây dựng lời giải tổng thể.
// Xác định mối quan hệ chuyển trạng thái (Recurrence Relation):

// Xác định công thức chuyển trạng thái liên kết kết quả của bài toán con với lời giải của bài toán lớn.
// Lưu trữ kết quả trung gian:

// Memoization (Top-Down): Ghi nhớ kết quả của các bài toán con khi tính đệ quy, tránh tính lại.
// Tabulation (Bottom-Up): Xây dựng lời giải từ các bài toán con nhỏ nhất lên đến bài toán lớn nhất theo dạng bảng.
// Tái cấu trúc lời giải:

// Từ kết quả của các bài toán con, xây dựng lời giải của bài toán ban đầu.
// 📌 Ứng dụng phổ biến:
// Đường đi tối ưu: Tìm đường đi ngắn nhất trong đồ thị (ví dụ: thuật toán Dijkstra, Bellman-Ford).
// Xâu con chung dài nhất (Longest Common Subsequence - LCS): Tìm chuỗi con chung dài nhất của hai chuỗi.
// Chuỗi con liên tiếp lớn nhất: Tìm chuỗi con liên tiếp có tổng lớn nhất trong mảng số.
// Nhiều bài toán tổ hợp và tối ưu hóa khác.
// 📌 Ví dụ: Tìm xâu con chung dài nhất (LCS) bằng phương pháp Tabulation trong Java
// Bài toán:
// Cho hai chuỗi text1 và text2, tìm độ dài của xâu con chung dài nhất của chúng.

// Giải pháp bằng DP (Bottom-Up Tabulation):
import java.util.Arrays;
public class LCS {
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // dp[i][j] lưu trữ độ dài LCS của text1[0...i-1] và text2[0...j-1]
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";
        System.out.println("Độ dài xâu con chung dài nhất: " + longestCommonSubsequence(text1, text2));
    }
}
// Giải thích:
// Bảng dp:
// dp[i][j] lưu trữ độ dài LCS của chuỗi con text1[0...i-1] và text2[0...j-1].
// Chuyển trạng thái:
// Nếu ký tự hiện tại của hai chuỗi trùng nhau, ta cộng thêm 1 vào lời giải của bài toán con nhỏ hơn (dp[i-1][j-1]).
// Nếu không trùng, ta lấy giá trị lớn nhất giữa việc bỏ qua ký tự hiện tại của text1 hoặc text2 (tức là max(dp[i-1][j], dp[i][j-1])).
// Kết quả cuối cùng:
// dp[m][n] chứa độ dài của LCS cho toàn bộ hai chuỗi.
// 🎯 Ưu điểm của Dynamic Programming:
// Tối ưu hóa: Giảm đáng kể số lần tính lại các bài toán con, từ đó tiết kiệm thời gian.
// Phù hợp với các bài toán tối ưu: Những bài toán có cấu trúc lặp lại và phụ thuộc lẫn nhau thường được giải quyết hiệu quả bằng DP.
// Dynamic Programming là một công cụ mạnh mẽ trong lập trình giải thuật, giúp giải quyết nhiều bài toán tối ưu một cách hiệu quả. 
// Bạn có thể áp dụng các phương pháp memoization hoặc tabulation tùy thuộc vào bài toán cụ thể.