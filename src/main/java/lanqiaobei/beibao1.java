package lanqiaobei;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class beibao1 {//01 背包问题
    public static void main(String[] args) {
        //第一行输入 物品数量 和 背包总容量  第二~N+1行输入 物品体积和价值
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int W = scan.nextInt();
        int[] w = new int[n + 1];//为了后续方便，定义n+1个元素，从1开始n里面是体积数据
        int[] p = new int[n + 1];  //价值数组
        int[][] DP = new int[n + 1][W + 1]; //这里存的是前i个物品放入容量为j的背包中的最大价值

        w[0] = 0;
        p[0] = 0;
        for (int i = 1; i < n + 1; i++) {//
            w[i] = scan.nextInt();
            p[i] = scan.nextInt();
        }


        //DP数组的边界条件：
        // 1） DP[0][i] = 0 即不装物品，所以价值为0  the first line init
        // 2）DP[i][0] = 0 即前i个物品装入容量为0的背包，也还是不装，所以利润为0
        for (int i = 0; i < W + 1; i++) {
            DP[0][i] = 0;
        }

        for (int i = 0; i < n + 1; i++) {
            DP[i][0] = 0;
        }

        //DP[i][j]表示的是，把前i个物品放入容量为j的背包中获取的最大容量
        //在 0-1 背包问题中，每种物品只有一个，因此将物品i放入背包后，只能从前i-1个物品中选择。
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (w[i] > j) {//判断： 如果第i件商品体积大于背包容量j 那么第i件物品就无法放入 DP[i][j] = DP[i-1][j]
                    DP[i][j] = DP[i - 1][j];
                } else {//那就是第i件商品体积小于等于背包容量j：
                    // 价值就取 装入i获取的利润DP[i-1][j-w[i]]+p[i] 和 不装入i获取的利润DP[i-1][j] 的最大值
                    DP[i][j] = Math.max(DP[i - 1][j - w[i]] + p[i], DP[i - 1][j]);
                }
            }
        }

        System.out.println(DP[n][W]);

    }
}
