// Kỹ thuật Two-pointer (Hai con trỏ) là một trong những kỹ thuật rất phổ biến trong việc tối ưu thuật toán,
// đặc biệt là khi làm việc với mảng hoặc chuỗi đã sắp xếp. Ý tưởng cơ bản là sử dụng hai con trỏ để duyệt dữ liệu theo hai hướng khác nhau, 
// thay vì sử dụng vòng lặp lồng nhau (nested loop), từ đó giúp giảm độ phức tạp về thời gian từ O(n^2) xuống O(n) trong nhiều trường hợp.  

//  🔍 Cách hoạt động của kỹ thuật Two-pointer:
// - Khởi tạo: Đặt hai con trỏ ở hai đầu của mảng (thường là left = 0 và right = n - 1 với mảng dài n).  
// - Duyệt: Di chuyển một hoặc cả hai con trỏ dựa trên một điều kiện cụ thể cho đến khi gặp nhau hoặc thỏa mãn yêu cầu bài toán.  

//  📌 Ví dụ điển hình: Tìm hai số có tổng bằng X
// Đề bài: Cho một mảng số nguyên đã được sắp xếp theo thứ tự tăng dần và một số nguyên X. 
// Kiểm tra xem có tồn tại một cặp phần tử nào có tổng bằng X hay không.

// Ý tưởng giải quyết:  
// 1. Khởi tạo hai con trỏ:
//    - left ở đầu mảng 0
//    - right ở cuối mảng n - 1
// 2. Tính tổng của hai phần tử tại left và right:
//    - Nếu tổng nhỏ hơn X: Tăng con trỏ left lên left++ để tăng tổng.
//    - Nếu tổng lớn hơn X: Giảm con trỏ right xuống right-- để giảm tổng.
//    - Nếu tổng bằng X: Trả về true vì đã tìm được cặp phần tử phù hợp.
// 3. Lặp lại đến khi hai con trỏ gặp nhau.

// Code minh họa:
public class PairSum {
    // Phương thức kiểm tra xem có tồn tại cặp số nào có tổng bằng X không
    public static boolean hasPairWithSum(int[] arr, int X) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            
            if (sum == X) {
                return true; // Tìm thấy cặp có tổng bằng X
            } else if (sum < X) {
                left++;  // Tăng tổng
            } else {
                right--; // Giảm tổng
            }
        }
        
        return false; // Không tìm thấy cặp nào
    }

    public static void main(String[] args) {
        // Ví dụ sử dụng
        int[] arr = {1, 2, 3, 4, 6};
        int X = 6;
        
        if (hasPairWithSum(arr, X)) {
            System.out.println("Tìm thấy cặp số có tổng bằng " + X);
        } else {
            System.out.println("Không tìm thấy cặp số có tổng bằng " + X);
        }
    }
}

// Ví dụ
// const numbers = [1, 3, 4, 5, 7, 10, 11];
// console.log(hasPairWithSum(numbers, 9));  // Output: true (4 + 5)

//  ⚡ Ứng dụng phổ biến của Two-pointer:
// - Kiểm tra dãy con đối xứng (Palindrome) trong chuỗi.  
// - Tìm tổng nhỏ nhất hoặc lớn nhất trong mảng con.  
// - Loại bỏ phần tử trùng lặp trong mảng đã sắp xếp.  
// - Sắp xếp hoặc hợp nhất hai mảng đã được sắp xếp (ví dụ như trong thuật toán merge sort).  

//  📈 Độ phức tạp:
// - Thời gian: O(n) vì mỗi phần tử chỉ được xét tối đa một lần.  
// - Không gian: O(1) vì không sử dụng thêm bộ nhớ phụ.  

