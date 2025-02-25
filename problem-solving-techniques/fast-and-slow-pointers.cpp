// Fast and Slow Pointers là một kỹ thuật thường được sử dụng trong các bài toán liên quan đến danh sách liên kết (Linked List) 
// hoặc các cấu trúc dữ liệu tuần tự. Hai con trỏ sẽ di chuyển với tốc độ khác nhau:
// Con trỏ nhanh (Fast Pointer): Di chuyển 2 bước mỗi lần.
// Con trỏ chậm (Slow Pointer): Di chuyển 1 bước mỗi lần.
// 🔍 Ý tưởng chính:
// Nếu có vòng lặp trong danh sách liên kết, con trỏ nhanh sẽ đuổi kịp con trỏ chậm tại một thời điểm nào đó.
// Nếu không có vòng lặp, con trỏ nhanh sẽ đạt đến cuối danh sách trước.
// Dùng để tìm điểm giữa của danh sách liên kết bằng cách dừng con trỏ chậm khi con trỏ nhanh đến cuối danh sách.
// ⚙️ Các ứng dụng phổ biến:
// 1️⃣ Phát hiện vòng lặp trong Linked List (Cycle Detection)
// Ý tưởng:
// Dùng thuật toán Floyd’s Cycle Detection Algorithm (còn gọi là Tortoise and Hare Algorithm).
// Nếu danh sách có vòng lặp, con trỏ nhanh và chậm sẽ gặp nhau tại một điểm nào đó.
// Code minh họa (JavaScript):
#include <iostream>
using namespace std;

// Định nghĩa struct ListNode
struct ListNode {
    int value;
    ListNode* next;

    ListNode(int val) : value(val), next(nullptr) {}
};

// Hàm kiểm tra vòng lặp trong danh sách liên kết
bool hasCycle(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;

    while (fast != nullptr && fast->next != nullptr) {
        slow = slow->next;           // Di chuyển 1 bước
        fast = fast->next->next;     // Di chuyển 2 bước

        if (slow == fast) {
            return true;  // Có vòng lặp
        }
    }

    return false;  // Không có vòng lặp
}

int main() {
    // Tạo danh sách liên kết
    ListNode* head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    head->next->next->next = head->next;  // Tạo vòng lặp

    // Kiểm tra vòng lặp
    if (hasCycle(head)) {
        cout << "Danh sách có vòng lặp." << endl;
    } else {
        cout << "Danh sách không có vòng lặp." << endl;
    }

    // Vì có vòng lặp nên không thể giải phóng bộ nhớ ở đây
    return 0;
}

// Giải thích:
// Con trỏ nhanh di chuyển nhanh hơn con trỏ chậm.
// Nếu có vòng lặp, con trỏ nhanh sẽ gặp con trỏ chậm tại một điểm.
// 2️⃣ Tìm điểm giữa của Linked List
// Ý tưởng:
// Con trỏ chậm di chuyển 1 bước, con trỏ nhanh di chuyển 2 bước.
// Khi con trỏ nhanh đến cuối danh sách, con trỏ chậm sẽ đứng ở giữa.
// Code minh họa (JavaScript):

#include <iostream>
using namespace std;
// Định nghĩa struct ListNode
struct ListNode {
    int value;
    ListNode* next;

    ListNode(int val) : value(val), next(nullptr) {}
};

// Hàm tìm nút giữa của danh sách liên kết
int findMiddle(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;

    while (fast != nullptr && fast->next != nullptr) {
        slow = slow->next;           // Di chuyển 1 bước
        fast = fast->next->next;     // Di chuyển 2 bước
    }

    return slow->value;  // Giá trị của nút giữa
}

int main() {
    // Tạo danh sách liên kết
    ListNode* head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    head->next->next->next = new ListNode(4);
    head->next->next->next->next = new ListNode(5);

    // Tìm và in nút giữa
    int middleValue = findMiddle(head);
    cout << "Giá trị của nút giữa là: " << middleValue << endl;

    // Giải phóng bộ nhớ
    ListNode* current = head;
    while (current != nullptr) {
        ListNode* temp = current;
        current = current->next;
        delete temp;
    }

    return 0;
}

// Giải thích:

// Khi con trỏ nhanh đến cuối, con trỏ chậm sẽ ở giữa danh sách.
// 3️⃣ Tìm điểm bắt đầu của vòng lặp (Start of Cycle)
// Ý tưởng:

// Khi hai con trỏ gặp nhau, đặt lại con trỏ chậm ở đầu danh sách.
// Di chuyển cả hai con trỏ 1 bước mỗi lần.
// Nơi chúng gặp nhau tiếp theo chính là điểm bắt đầu của vòng lặp.
// Code minh họa (JavaScript):

#include <iostream>
using namespace std;
// Định nghĩa struct ListNode
struct ListNode {
    int value;
    ListNode* next;

    ListNode(int val) : value(val), next(nullptr) {}
};

// Hàm phát hiện vòng lặp và trả về điểm bắt đầu vòng lặp (nếu có)
ListNode* detectCycle(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;

    // Kiểm tra vòng lặp
    while (fast != nullptr && fast->next != nullptr) {
        slow = slow->next;
        fast = fast->next->next;

        if (slow == fast) {  // Phát hiện vòng lặp
            slow = head;  // Đặt con trỏ chậm về đầu danh sách
            while (slow != fast) {
                slow = slow->next;
                fast = fast->next;
            }
            return slow;  // Điểm bắt đầu của vòng lặp
        }
    }

    return nullptr;  // Không có vòng lặp
}

int main() {
    // Tạo danh sách liên kết
    ListNode* head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    head->next->next->next = new ListNode(4);
    head->next->next->next->next = head->next;  // Tạo vòng lặp (bắt đầu tại nút có giá trị 2)

    // Kiểm tra vòng lặp
    ListNode* cycleStart = detectCycle(head);
    if (cycleStart != nullptr) {
        cout << "Vòng lặp bắt đầu tại nút có giá trị: " << cycleStart->value << endl;
    } else {
        cout << "Không có vòng lặp trong danh sách." << endl;
    }

    // Không thể giải phóng bộ nhớ vì danh sách có vòng lặp
    return 0;
}

// 📈 Độ phức tạp:
// Thời gian: O(n) — Mỗi phần tử chỉ được duyệt qua tối đa 2 lần.
// Không gian: O(1) — Chỉ dùng thêm 2 con trỏ.
// 🎯 Tại sao nên dùng Fast and Slow Pointers?
// Tối ưu về thời gian và không gian.
// Hiệu quả khi xử lý các bài toán liên quan đến chu kỳ, vòng lặp, hoặc tìm nút giữa trong danh sách liên kết.
// Dễ dàng áp dụng cho các bài toán phức tạp hơn như Palindrome Linked List hoặc Reordering Linked List.