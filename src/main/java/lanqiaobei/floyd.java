package lanqiaobei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/12
 */
public class floyd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        long[][] d = new long[n + 1][n + 1]; // d[i][j]表示i到j的最短路径
        for (int i = 0; i <= n; i++) {
            Arrays.fill(d[i], Long.MAX_VALUE);
        }
        for (int i = 0; i <= n; i++) {
            d[i][i] = 0; // 自身到自身距离为0
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            d[x][y] = Math.min(d[x][y], z); // 题目是双向边
            d[y][x] = Math.min(d[y][x], z); // 同一条边可能给出不同权值
        }
        for (int k = 1; k <= n; k++) { // 依次将k个点作为中间点
            for (int i = 1; i <= n; i++) { // 扫描行
                for (int j = 1; j <= n; j++) { // 扫描列
                    if (d[i][k] != Long.MAX_VALUE && d[k][j] != Long.MAX_VALUE) {
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    }
                }
            }
        }
        while (q-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(d[x][y] != Long.MAX_VALUE ? d[x][y] : -1);
        }
        sc.close();
    }
}
