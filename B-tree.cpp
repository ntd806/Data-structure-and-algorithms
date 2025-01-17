#include <iostream>
using namespace std;

struct BTree {
    int *d;              // Con trỏ tới mảng các khóa (keys).
    BTree **child_ptr;   // Con trỏ tới mảng các con trỏ (chỉ đến các nút con của nút hiện tại).
    bool l;              // Cờ (flag) để kiểm tra xem nút này có phải là nút lá hay không.
    int n;               // Số lượng khóa hiện có trong nút.
} *r = NULL, *np = NULL, *x = NULL;

BTree* init(int degree) {
    // Cấp phát bộ nhớ cho một nút mới của cây B-Tree
    np = new BTree;
    // Cấp phát mảng các khóa (d) với kích thước degree - 1
    // Vì mỗi nút có tối đa degree - 1 khóa
    np->d = new int[degree - 1];
    // Cấp phát mảng con trỏ đến các nút con (child_ptr)
    // Mỗi nút có tối đa degree con trỏ con, bao gồm cả nút lá và nút không lá
    np->child_ptr = new BTree *[degree];
    // Đặt cờ l = true, chỉ ra rằng đây là một nút lá (leaf node)
    np->l = true;
    // Đặt số lượng khóa trong nút là 0 (chưa có khóa)
    np->n = 0;
    // Khởi tạo tất cả các con trỏ trong mảng child_ptr là NULL
    // Các con trỏ này trỏ đến các nút con và sẽ được gán giá trị khi cần thiết
    for (int i = 0; i < degree; i++) {
        np->child_ptr[i] = NULL;
    }
    // Trả về con trỏ đến nút vừa khởi tạo
    return np;
}


int main() {
    return 0;
}