// Trong hệ nhị phân:

// Số lẻ có bit cuối cùng (hay còn gọi là bit đơn vị) là 1.
// Số chẵn có bit cuối cùng là 0.
// Ví dụ:

// Số nhị phân 101 (5 trong hệ thập phân) có bit cuối là 1, nên đây là một số lẻ.
// Số nhị phân 110 (6 trong hệ thập phân) có bit cuối là 0, nên đây là một số chẵn.
// Giải thích:
// Bit cuối cùng (bit ít nhất) của một số trong hệ nhị phân đại diện cho phần 1 hay 0 trong hệ thập phân, và chính nó quyết định số đó là lẻ hay chẵn.
// Khi số có bit cuối là 1, điều đó có nghĩa là nó là số lẻ vì phần dư khi chia cho 2 là 1.
// Khi số có bit cuối là 0, điều đó có nghĩa là nó là số chẵn vì phần dư khi chia cho 2 là 0.

// Bit Manipulation là kỹ thuật thao tác trực tiếp trên các bit (0 và 1) của số nhị phân. Kỹ thuật này thường được sử dụng để:

// Tối ưu hóa không gian (bằng cách sử dụng ít bộ nhớ hơn).
// Tăng tốc độ tính toán (thao tác bit nhanh hơn phép toán số học thông thường).
// Giải quyết các bài toán liên quan đến nhị phân hoặc kiểm tra tính chất của số.
// 🔍 Các phép toán cơ bản:
// Phép toán   | Ký hiệu	 |  Ý nghĩa
// AND	       |   &	     |   Bit = 1 nếu cả hai bit đều là 1
// OR	       |   | 	     |   Bit = 1 nếu một trong hai bit là 1
// XOR         |   ^	     |   Bit = 1 nếu hai bit khác nhau
// NOT		   |   ~         |   Đảo ngược bit (0 thành 1, 1 thành 0)
// Shift Left  |   <<	     |   Dịch bit sang trái (nhân 2)
// Shift Right |   >>	     |   Dịch bit sang phải (chia 2)

// Thao tác Shift Left (Dịch trái) trong Bit Manipulation có nghĩa là di chuyển các bit của một số sang bên trái một hoặc nhiều vị trí, đồng thời các bit mới bên phải sẽ được điền bằng 0. Cứ mỗi lần dịch trái một bit, giá trị của số sẽ nhân đôi.
// Cú pháp:
// Giả sử bạn có một số nguyên x và bạn muốn dịch nó sang trái một số bit n. Cú pháp là:
// x << n
// Điều này có nghĩa là "Dịch bit của x sang trái n vị trí".

// Ví dụ 1:
// Giả sử bạn có số nhị phân 00000101 (đây là số 5 trong hệ thập phân).
// Khi dịch trái 1 bit (5 << 1):
// Trước khi dịch: 00000101 (5)
// Sau khi dịch trái 1 bit: 00001010 (10)
// Vậy, số 5 sau khi dịch trái 1 bit sẽ thành số 10.
// Ví dụ 2:
// Giả sử bạn có số nhị phân 00001010 (đây là số 10 trong hệ thập phân).
// Khi dịch trái 2 bit (10 << 2):
// Trước khi dịch: 00001010 (10)
// Sau khi dịch trái 2 bit: 00101000 (40)
// Vậy, số 10 sau khi dịch trái 2 bit sẽ thành số 40.
// Thao tác Shift Right (Dịch phải) làm ngược lại sang bên phải một hoặc nhiều vị trí. Các bit mới bên trái sẽ được điền bằng 0.
// Tóm tắt:
// Mỗi lần dịch trái 1 bit, giá trị của số sẽ nhân đôi.
// Sau khi dịch, các bit mới sẽ được điền 0 từ phía phải.

// 📌 Ví dụ 1: Kiểm tra số chẵn hay lẻ
public class BitwiseExample {
    public static boolean isOdd(int n) {
        return (n & 1) == 1;  // Kiểm tra bit cuối cùng
    }

    public static void main(String[] args) {
        int number = 5;
        if (isOdd(number)) {
            System.out.println(number + " là số lẻ.");
        } else {
            System.out.println(number + " là số chẵn.");
        }
    }
}
// Giải thích:
// Nếu bit cuối cùng của số là 1, số đó là lẻ.
// Dùng toán tử & 1 để kiểm tra bit cuối.

// 📌 Ví dụ 2: Đếm số bit 1 trong số nhị phân (Số lượng bit bật)
public class CountBits {
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;  // Cộng thêm nếu bit cuối là 1
            n >>= 1;         // Dịch phải 1 bit
        }
        return count;
    }

    public static void main(String[] args) {
        int number = 29;  // Binary: 11101
        System.out.println("Số bit 1 trong " + number + " là: " + countSetBits(number));
    }
}
// Giải thích:
// n & 1: Kiểm tra bit cuối cùng.
// n >>= 1: Dịch các bit sang phải (chia 2).

// 📌 Ví dụ 3: Đảo bit của một số
public class ReverseBits {
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {  // Giả sử số nguyên 32 bit
            result <<= 1;               // Dịch trái 1 bit
            result |= (n & 1);          // Thêm bit cuối của n vào result
            n >>= 1;                    // Dịch phải 1 bit
        }
        return result;
    }

    public static void main(String[] args) {
        int number = 13;  // Binary: 00000000 00000000 00000000 00001101
        int reversed = reverseBits(number);
        System.out.println("Số sau khi đảo bit: " + reversed);
    }
}
// Giải thích:
// Lấy từng bit của n từ phải qua trái và xây dựng số mới từ trái qua phải.
// Sử dụng dịch bit (<<, >>) để thao tác trên từng bit.

// 📌 Ví dụ 4: Kiểm tra xem một số có phải là lũy thừa của 2 không
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {
        int number = 16;
        if (isPowerOfTwo(number)) {
            System.out.println(number + " là lũy thừa của 2.");
        } else {
            System.out.println(number + " không phải là lũy thừa của 2.");
        }
    }
}
// Giải thích:
// Số là lũy thừa của 2 nếu chỉ có 1 bit được bật trong biểu diễn nhị phân.
// (n & (n - 1)) == 0 sẽ đúng nếu số đó chỉ có 1 bit bật.

// 📈 Tại sao nên dùng Bit Manipulation?
// Tối ưu thời gian: Nhanh hơn so với các phép toán thông thường.
// Tối ưu bộ nhớ: Sử dụng ít không gian hơn, đặc biệt trong các bài toán cần theo dõi trạng thái.
// Hiệu quả: Xử lý các bài toán nhị phân hoặc tổ hợp phức tạp với độ phức tạp thấp hơn.
