Trong lập trình, đặc biệt là với các ngôn ngữ như C, C++, Java, và PHP, kiểu dữ liệu char và string được sử dụng 
để làm việc với ký tự và chuỗi ký tự. Dưới đây là một cái nhìn tổng quan:

 🔤 1. Kiểu char là gì?
- char (viết tắt của *character*) là kiểu dữ liệu lưu trữ một ký tự đơn lẻ.  
- Thông thường, nó chiếm 1 byte (8 bits) trong bộ nhớ và chứa giá trị mã ASCII tương ứng với ký tự đó.

 🌟 Ví dụ trong C:
char letter = 'A';
printf("Ký tự: %c, Mã ASCII: %d\n", letter, letter);

Kết quả:

Ký tự: A, Mã ASCII: 65

- Ở đây, 'A' được lưu dưới dạng số nguyên với mã ASCII là 65.

📜 2. Kiểu string là gì?
- string là một tập hợp các ký tự được lưu trữ liên tiếp nhau.  
- Tùy vào ngôn ngữ lập trình, string có thể được định nghĩa khác nhau:
  - C: Một mảng các ký tự kết thúc bằng ký tự '\0' (null terminator).
  - C++ / Java / PHP: Có kiểu dữ liệu String tích hợp sẵn.

 🌟 Ví dụ trong C:
char name[] = "ChatGPT";
printf("Tên: %s\n", name);

 🌟 Ví dụ trong PHP:
$name = "ChatGPT";
echo "Tên: $name";

 🔍 3. Sự khác biệt giữa char và string:

| Tính năng       | char                           | string                             |
|---------------- |--------------------------------|------------------------------------|
| Lưu trữ         | 1 ký tự duy nhất               | Nhiều ký tự (chuỗi)                |
| Kích thước      | 1 byte                         | Tùy thuộc vào độ dài chuỗi         |
| Ký tự kết thúc  | Không có                       | Ký tự kết thúc \0 (C)              |
| Ví dụ           | 'A'                            | "Hello"                            |

 🔗 4. Khi nào nên dùng?
- Dùng char khi chỉ cần lưu trữ một ký tự duy nhất (ví dụ: kiểm tra chữ cái đầu tiên của từ).
- Dùng string khi cần lưu trữ và thao tác với một tập hợp nhiều ký tự (ví dụ: in ra một câu, đọc dữ liệu đầu vào từ người dùng).

Bạn muốn mình giải thích sâu hơn về cách thao tác với string trong ngôn ngữ cụ thể nào không? (Ví dụ như PHP, C++, hoặc Java?)