import java.util.Arrays;

public class UnionFind {
    private int[] parent;
    private int[] rank; // Dùng để lưu "độ cao" ước tính của cây đại diện

    // Khởi tạo với n phần tử: mỗi phần tử ban đầu là một tập riêng với rank = 0
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Mỗi phần tử tự làm cha của nó
            rank[i] = 0;   // Ban đầu, rank của mọi cây đều là 0
        }
    }

    // Tìm đại diện của phần tử x (với kỹ thuật nén đường dẫn)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Hợp nhất hai tập chứa x và y theo union by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Nếu hai phần tử đã ở cùng một tập, không cần hợp nhất
        if (rootX == rootY) return false;

        // So sánh rank của 2 cây đại diện
        if (rank[rootX] > rank[rootY]) {
            // Gắn cây có rank thấp (rootY) vào cây có rank cao (rootX)
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            // Nếu cả 2 cây có cùng rank, gắn rootY vào rootX và tăng rank của rootX lên 1
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
    
    // Phương thức in ra trạng thái hiện tại của mảng parent và rank
    public void printState() {
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Rank:   " + Arrays.toString(rank));
        System.out.println("----------------------------------");
    }
    
    public static void main(String[] args) {
        // Tạo UnionFind với 6 phần tử (0 đến 5)
        UnionFind uf = new UnionFind(6);
        
        // Ban đầu: mỗi phần tử là một cây riêng, rank của mỗi cây đều là 0
        uf.printState();
        
        // Thực hiện union(0, 1)
        // Vì rank[0] và rank[1] đều bằng 0, nên sau union, ta gắn 1 vào 0 và tăng rank[0] lên 1
        uf.union(0, 1);
        uf.printState();
        
        // Thực hiện union(2, 3)
        // Tương tự, rank[2] và rank[3] đều 0, nên sau union, rank[2] tăng lên 1
        uf.union(2, 3);
        uf.printState();
        
        // Thực hiện union(0, 2)
        // Lúc này, root của 0 (là 0) có rank = 1, root của 2 (là 2) cũng có rank = 1,
        // nên sau union, ta gắn 2 vào 0 (có thể chọn theo ý) và tăng rank[0] lên thành 2.
        uf.union(0, 2);
        uf.printState();
        
        // Thực hiện union(4, 5)
        // rank của 4 và 5 đều 0, sau union, rank[4] sẽ tăng lên 1
        uf.union(4, 5);
        uf.printState();
        
        // Thực hiện union(0, 4)
        // Lúc này, root của 0 (là 0) có rank = 2, root của 4 (là 4) có rank = 1,
        // nên ta gắn cây của 4 vào cây của 0 mà không cần tăng rank.
        uf.union(0, 4);
        uf.printState();
    }
}
