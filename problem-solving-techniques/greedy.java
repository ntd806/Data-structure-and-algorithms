// Greedy (hay thuật toán tham lam) là một chiến lược giải bài toán bằng cách luôn luôn chọn phương án tốt nhất tại mỗi bước (tức là lựa chọn cục bộ tối ưu) 
// mà không quan tâm đến các bước tiếp theo. Ý tưởng của thuật toán tham lam là:

// Ở mỗi bước, chọn lựa phương án có lợi nhất (theo một tiêu chí nào đó) để hướng tới lời giải tối ưu toàn cục.
// Thuật toán không quay lui (backtracking) hay xem xét lại các lựa chọn đã thực hiện.
// ⚙️ Đặc điểm của thuật toán Greedy:
// Greedy-choice property: Lựa chọn cục bộ tối ưu sẽ dẫn đến lời giải toàn cục tối ưu (trong một số bài toán có đặc tính này).
// Optimal substructure: Lời giải của bài toán có thể được cấu thành từ các lời giải của các bài toán con.
// 📌 Ứng dụng của Greedy:
// Bài toán đổi tiền (Coin Change):
// Ví dụ, nếu ta có các đồng tiền với mệnh giá đã được sắp xếp giảm dần, ta luôn lấy đồng tiền có mệnh giá lớn nhất mà không vượt quá số tiền cần đổi.
// Kruskal's MST:
// Trong bài toán tìm cây khung nhỏ nhất của đồ thị, ta luôn chọn cạnh có trọng số nhỏ nhất và không tạo thành chu trình.
// Dijkstra's Shortest Path:
// Luôn chọn đỉnh có khoảng cách nhỏ nhất từ nguồn.
// 📌 Ví dụ: Bài toán đổi tiền (Coin Change) với Greedy trong Java
// Giả sử bạn có các đồng tiền với mệnh giá 25,10,5,1 cent và cần đổi 63 cent thành tiền xu với số đồng ít nhất. Với chiến lược tham lam, 
// ta luôn chọn đồng có mệnh giá lớn nhất có thể sử dụng.

public class CoinChange {
    public static void main(String[] args) {
        // Các đồng tiền được sắp xếp giảm dần
        int[] coins = {25, 10, 5, 1};
        int amount = 63;
        int coinCount = 0;
        
        System.out.println("Đổi " + amount + " cent thành các đồng tiền như sau:");
        for (int coin : coins) {
            if (amount >= coin) {
                int count = amount / coin; // Số đồng tiền có thể dùng với mệnh giá này
                coinCount += count;
                amount %= coin; // Cập nhật số tiền còn lại sau khi dùng đồng tiền hiện tại
                System.out.println("Sử dụng " + count + " đồng " + coin + " cent.");
            }
        }
        System.out.println("Tổng số đồng tiền cần dùng: " + coinCount);
    }
}
// Giải thích:
// Khởi tạo:
// Mảng coins chứa các mệnh giá được sắp xếp giảm dần.
// amount là số tiền cần đổi (63 cent trong ví dụ).
// Vòng lặp:
// Với mỗi đồng tiền, ta tính số lượng đồng có thể dùng bằng cách chia amount cho coin.
// Cập nhật amount còn lại sau khi sử dụng đồng đó.
// Tích lũy số lượng đồng tiền vào biến coinCount.
// Kết quả:
// Đầu ra sẽ liệt kê số đồng mỗi mệnh giá được sử dụng và tổng số đồng dùng.
// 🔥 Nhận xét:
// Ưu điểm:
// Đơn giản, dễ cài đặt và hiệu quả về thời gian.
// Nhược điểm:
// Không phải lúc nào lựa chọn cục bộ tối ưu cũng dẫn đến lời giải toàn cục tối ưu.
// Ví dụ: Với một số bộ mệnh giá không "chuẩn" (non-canonical coin systems), thuật toán tham lam có thể không tìm được lời giải tối ưu.
// Trong các bài toán như Kruskal's MST,
// thuật toán Greedy được sử dụng để liên tục chọn cạnh có trọng số nhỏ nhất mà không tạo thành chu trình, giúp xây dựng cây khung tối ưu cho đồ thị.

