package lanqiaobei;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/12
 */
public class segmentTree {
    static Scanner scan = new Scanner(System.in);
    //在此输入您的代码...
    static int n = scan.nextInt(), q = scan.nextInt();
    static long[] info = new long[n + 1];
    static long[] segTree = new long[4 * n];
    static long[] lazy = new long[4 * n];
    static int[][] op = new int[q][4];

    public static void main(String[] args) {
        for (int i = 1; i <= n; i++) {
            info[i] = scan.nextInt();
        }
        for (int i = 0; i < q; i++) {
            op[i][0] = scan.nextInt();
            op[i][1] = scan.nextInt();
            op[i][2] = scan.nextInt();
            if (op[i][0] == 1) op[i][3] = scan.nextInt();
        }
        // 树编号和区间均从1开始
        buildTree(1, n, 1);
        for (int i = 0; i < q; i++) {
            if (op[i][0] == 2) System.out.println(query(op[i][1], op[i][2], 1, n, 1));
            else update(op[i][1], op[i][2], op[i][3], 1, n, 1);
        }

        scan.close();
    }

    public static void buildTree(int s, int t, int p) {
        // 对 [s,t] 区间建立线段树,当前根的编号为 p
        if (s == t) {
            segTree[p] = info[s];
            return;
        }
        int m = s + ((t - s) >> 1);// 取半
        buildTree(s, m, p * 2);// 递归对左区间建树
        buildTree(m + 1, t, p * 2 + 1);// 递归对右区间建树
        segTree[p] = segTree[p * 2] + segTree[(p * 2) + 1];// 求左右子树之和
    }

    static long query(int l, int r, int s, int t, int p) {// [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
        if (l <= s && t <= r) return segTree[p];// 当前区间为询问区间的子集时直接返回当前区间的和
        int m = s + ((t - s) >> 1);// 取半
        if (lazy[p] != 0) pushLazy(s, t, p, m);// 如果当前节点的懒标记非空,则更新当前节点两个子节点的值和懒标记值
        long sum = 0;
        if (l <= m) sum = query(l, r, s, m, p * 2);
        if (r > m) sum += query(l, r, m + 1, t, p * 2 + 1);
        return sum;
    }

    public static void update(int l, int r, int c, int s, int t, int p) {// [l, r] 为修改区间, c 为被修改的元素的变化量, [s, t] 为当前节点包含的区间, p为当前节点的编号
        if (l <= s && t <= r) {// 当前区间为修改区间的子集时直接修改当前节点的值,然后打标记,结束修改
            segTree[p] += (t - s + 1) * c;
            lazy[p] += c;
            return;
        }
        int m = s + ((t - s) >> 1);// 取半
        if (lazy[p] != 0 && s != t) pushLazy(s, t, p, m);// 如果当前节点的懒标记非空,则更新当前节点两个子节点的值和懒标记值
        if (l <= m) update(l, r, c, s, m, p * 2);
        if (r > m) update(l, r, c, m + 1, t, p * 2 + 1);
        segTree[p] = segTree[p * 2] + segTree[p * 2 + 1];
    }

    private static void pushLazy(int s, int t, int p, int m) {
        segTree[p * 2] += lazy[p] * (m - s + 1);
        segTree[p * 2 + 1] += lazy[p] * (t - m);
        lazy[p * 2] += lazy[p];
        lazy[p * 2 + 1] += lazy[p];// 将标记下传给子节点
        lazy[p] = 0;// 清空当前节点的标记
    }
}
