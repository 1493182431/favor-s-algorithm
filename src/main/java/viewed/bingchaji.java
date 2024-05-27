package viewed;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class bingchaji {
    static Scanner scan = new Scanner(System.in);
    //在此输入您的代码...
    static int n = scan.nextInt(), m = scan.nextInt();
    static int[] relation = new int[n + 1];

    public static void main(String[] args) {
        for (int i = 0; i < relation.length; i++) {
            relation[i] = i;
        }
        int[][] info = new int[m][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                info[i][j] = scan.nextInt();
            }
        }
        //注意：先将输入全部接受后再进行输出，否则容易出错
        for (int i = 0; i < m; i++) {
            int op = info[i][0], x = info[i][1], y = info[i][2];
            if (op == 1) {
                merge(x, y);
            } else {
                if (find(x) == find(y)) System.out.println("YES");
                else {
                    System.out.println("NO");
                }
            }
        }
        scan.close();
    }

    public static int find(int x) {//并查集的查询操作，本质是寻找节点x的根节点(同时压缩路径，节省时间)
        if (x == relation[x]) return x;//所有的根节点都是连接自己的环
        else {//relation[x]表示节点x的父节点，从x节点出发向上递归寻找x的父节点，直到找到x的根节点为止
            relation[x] = find(relation[x]);//将x节点的父节点设为根节点，并沿途将所有节点的父节点设置为x的根节点
            return relation[x];//返回x的父节点（同时也是x的根节点）
        }
    }

    public static void merge(int i, int j) {//并查集合并操作，本质是将节点j的根节点设置为节点i的根节点的父节点
        relation[find(i)] = find(j);
    }
}
