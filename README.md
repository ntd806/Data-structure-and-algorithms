# Data-structure-and-algorithms
Data Structures is about how data can be stored in different structures.  Algorithms is about how to solve different problems, often by searching through and manipulating data structures.  Theory about Data Structures and Algorithms (DSA) helps us to use large amounts of data to solve problems efficiently.

# Environments
This project for education puposes and open sources
All code is ran online at: https://www.onlinegdb.com/

Author: Anthony Nguyen Developer
Facebook: https://www.facebook.com/anthony.nguyen200090/


## 1. Nhóm Data Structures (Cấu trúc dữ liệu)
Các cấu trúc dữ liệu thường được hỏi trong phỏng vấn, bao gồm:

1. **Arrays (Mảng)**  
   - Là cấu trúc dữ liệu cơ bản nhất, cho phép truy cập phần tử theo chỉ số (index).  
   - Thường sử dụng để tìm hiểu về cách sắp xếp (sorting), tìm kiếm (searching), hoặc triển khai một số giải thuật cơ bản.

2. **Strings (Chuỗi)**  
   - Một dạng đặc biệt của mảng ký tự.  
   - Hay gặp trong các bài tập xử lý chuỗi: duyệt chuỗi, tìm chuỗi con (substring), thao tác với ký tự, v.v.

3. **Linked Lists (Danh sách liên kết)**  
   - Cấu trúc dữ liệu dạng chuỗi các node liên kết nhau qua con trỏ (pointer).  
   - Thường dùng để rèn luyện tư duy thao tác con trỏ, chèn/xoá node, đảo ngược danh sách (reverse list), phát hiện vòng lặp (detect cycle)...

4. **Stacks (Ngăn xếp)** và **Queues (Hàng đợi)**  
   - **Stack**: LIFO (Last In, First Out). Thường dùng trong các bài kiểm tra ngoặc (dấu đóng mở), duyệt cây, backtracking...  
   - **Queue**: FIFO (First In, First Out). Ứng dụng trong duyệt đồ thị BFS, lập lịch tác vụ, mô phỏng hàng đợi...

5. **Hash Tables (Bảng băm)**  
   - Lưu trữ cặp (key, value), tra cứu và cập nhật dữ liệu trung bình trong O(1).  
   - Quan trọng trong việc xử lý các bài tập yêu cầu tìm kiếm nhanh hoặc đếm tần suất.

6. **Matrix (Ma trận)**  
   - Là mảng 2 chiều, thường xuất hiện trong bài tập duyệt/di chuyển trên lưới (grid), đồ họa, tính đường đi ngắn nhất, v.v.

7. **Trees (Cây)**, **Binary Search Trees (Cây nhị phân tìm kiếm)**  
   - Cây là cấu trúc phân cấp, giúp thể hiện quan hệ cha-con.  
   - Cây nhị phân tìm kiếm (BST) có đặc tính quan trọng: giá trị bên trái nhỏ hơn node gốc, bên phải lớn hơn node gốc, giúp tìm kiếm nhanh.

8. **Heaps (Đống)**  
   - Là một dạng cây nhị phân có tính chất “heap” (min-heap hoặc max-heap).  
   - Ứng dụng trong các giải thuật sắp xếp (heap sort), tìm phần tử lớn nhất/nhỏ nhất một cách hiệu quả.

9. **Graphs (Đồ thị)**  
   - Mô hình hóa quan hệ giữa các đối tượng (node) qua các cạnh (edge).  
   - Là nền tảng cho nhiều bài toán đường đi ngắn nhất, thành phần liên thông, chu trình, v.v.

10. **Trie (Prefix Tree)**  
   - Cấu trúc cây chuyên dụng cho việc lưu trữ và tìm kiếm chuỗi (đặc biệt là các bài về từ điển, auto-complete).  

11. **Union Find (Disjoint Set)**  
   - Hỗ trợ xác định nhanh hai phần tử có thuộc cùng một tập (connected component) hay không.  
   - Thường dùng trong các bài toán kiểm tra đồ thị có chu trình, kết nối thành phần, hoặc Kruskal MST.

---

## 2. Nhóm Algorithms (Giải thuật)
Bao gồm các thuật toán sắp xếp, tìm kiếm, duyệt cây, duyệt đồ thị, v.v. Cụ thể:

### Sorting (Sắp xếp)
1. **Merge Sort**  
2. **Quick Sort**  
3. **Heap Sort**  
4. **Counting Sort**  
5. **Bucket Sort**  

Các thuật toán sắp xếp này khác nhau về độ phức tạp, ưu/nhược điểm, và cách triển khai. Thường gặp trong phỏng vấn để đánh giá khả năng tối ưu hoá.

### Tìm kiếm
- **Binary Search (Tìm kiếm nhị phân)**  
  - Kỹ thuật cơ bản để tìm phần tử trong mảng đã sắp xếp với thời gian O(log n).

### Các giải thuật khác
- **Kadane’s Algorithm**: Tìm tổng lớn nhất của dãy con liên tiếp trong mảng (maximum subarray sum).  
- **Tree traversal (duyệt cây)**: Bao gồm in-order, pre-order, post-order, level-order.  
- **Graph Algorithms (Thuật toán đồ thị)**: 
  - **DFS/BFS** (Duyệt sâu/Duyệt rộng),  
  - **Topological Sort** (Sắp xếp topo),  
  - **Minimum Spanning Tree** (Kruskal, Prim),  
  - **Dijkstra** (Đường đi ngắn nhất),  
  - **Bellman-Ford** (Đường đi ngắn nhất, có xét trọng số âm).  

Các thuật toán này là nền tảng để xử lý nhiều bài toán khác nhau trong phỏng vấn.

---

## 3. Nhóm Problem-Solving Techniques (Kỹ thuật giải quyết vấn đề)
Các kỹ thuật phổ biến dùng để thiết kế hoặc tối ưu giải thuật:

1. **Two-pointer (Hai con trỏ)**  
   - Thường dùng trong mảng hoặc chuỗi đã sắp xếp, để duyệt từ hai đầu về giữa hoặc kết hợp với một số điều kiện (ví dụ bài toán cặp phần tử có tổng bằng X).

2. **Sliding Window (Cửa sổ trượt)**  
   - Dùng trong các bài tập liên quan đến dãy con (subarray) liên tiếp, tối ưu độ dài hoặc tổng giá trị.

3. **Prefix Sum (Mảng tích luỹ trước)**  
   - Hỗ trợ tính nhanh tổng đoạn con, số lần xuất hiện, v.v.

4. **Monotonic Stack (Ngăn xếp đơn điệu)**  
   - Thường để giải quyết bài toán “Next Greater Element”, hay tìm khoảng lớn nhất thoả mãn điều kiện, v.v.

5. **Top ‘K’ Elements**  
   - Kết hợp cấu trúc dữ liệu như Heap (min-heap/max-heap) để tìm K phần tử lớn nhất/nhỏ nhất.

6. **Fast and Slow Pointers (Con trỏ nhanh và chậm)**  
   - Dùng trong Linked List để phát hiện vòng lặp, tìm điểm giữa...

7. **Bit Manipulation (Thao tác bit)**  
   - Thường xuất hiện trong bài tối ưu không gian và thời gian, hoặc trong các bài kiểm tra kiến thức nền tảng về nhị phân.

8. **Divide and Conquer (Chia để trị)**  
   - Tư duy chia bài toán lớn thành các bài toán con, ví dụ Merge Sort, Quick Sort...

9. **Recursion (Đệ quy)**  
   - Cách tổ chức hàm gọi chính nó, thường đi kèm với chia để trị, backtracking...

10. **Backtracking**  
    - Duyệt toàn bộ (brute force) kèm cắt tỉa (pruning), áp dụng trong các bài toán tổ hợp, sắp xếp, sudoku, v.v.

11. **Greedy (Tham lam)**  
    - Lựa chọn phương án tốt nhất tại mỗi bước cục bộ, ví dụ: chọn đồng tiền lớn nhất trước trong bài toán đổi tiền, chọn cạnh nhỏ nhất trước trong Kruskal MST...

12. **Dynamic Programming (Lập trình động)**  
    - Chia nhỏ bài toán, lưu trữ kết quả trung gian (memoization/tabulation).  
    - Xuất hiện thường xuyên trong các bài tối ưu: đường đi, xâu con chung dài nhất (LCS), chuỗi con liên tiếp lớn nhất, v.v.

---

## Tóm lại
- **Data Structures**: Cần nắm vững cách cài đặt, đặc điểm, ưu/nhược điểm và các thao tác cơ bản (thêm, xoá, tìm kiếm).  
- **Algorithms**: Hiểu rõ cơ chế, độ phức tạp (time complexity, space complexity) và khi nào nên dùng thuật toán nào.  
- **Problem-Solving Techniques**: Là những phương pháp, khuôn mẫu tư duy để giải quyết bài toán phức tạp, giúp bạn đưa ra giải pháp một cách hệ thống và tối ưu.

Việc thành thạo cả ba nhóm (Data Structures, Algorithms, Problem-Solving Techniques) là chìa khoá để giải quyết tốt hầu hết các bài toán trong phỏng vấn lập trình. Khi ôn tập, bạn nên:
1. **Học cách cài đặt**: Thử viết code triển khai từng cấu trúc dữ liệu/thuật toán.  
2. **Nắm rõ độ phức tạp**: Thời gian (time) và không gian (space).  
3. **Luyện tập qua bài tập thực tế**: Áp dụng từng kỹ thuật vào các bài toán mẫu, để rèn khả năng nhận diện mô hình (pattern).  

Đó là những nội dung cốt lõi mà hình ảnh đã tóm tắt, giúp bạn định hướng ôn tập DSA cho phỏng vấn.