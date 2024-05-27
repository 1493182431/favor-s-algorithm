package viewed;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/8
 */
public class leisaizi {
    static int[] op = new int[7];
    static int mod = 1000000007;
    static int[][] flag = new int[7][7];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long[] ans = new long[1];
        op[1] = 4;op[2] = 5;op[3] = 6;op[4] = 1;op[5] = 2;op[6] = 3;
        int n = scan.nextInt();
        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            flag[x][y] = 1;
            flag[y][x] = 1;
        }
        check(new ArrayList<>(), n, ans);
        System.out.println(ans[0]);
        scan.close();
    }

    public static void check(ArrayList<Integer> state, int n, long[] ans) {
        if (state.size() == n) {//决定了递归层数
            ans[0] += (long) Math.pow(4, n);
            ans[0] %= mod;
            return;
        }
        for (int i = 1; i <= 6; i++) {//for循环表示每层递归状态数
            if (state.isEmpty()) {
                state.add(i);
                check(state, n, ans);
                state.remove(state.size() - 1);
            } else if (flag[op[state.get(state.size() - 1)]][i] == 0) {
                state.add(i);
                check(state, n, ans);
                state.remove(state.size() - 1);
            }
        }
    }
}
