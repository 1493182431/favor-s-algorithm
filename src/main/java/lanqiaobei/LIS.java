package lanqiaobei;

import java.io.*;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class LIS {//求最长递增子序列
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in))));
    static void nextToken() {try {in.nextToken();} catch (IOException e) {e.printStackTrace();}}
    static int nextInt() {nextToken();return (int) in.nval;}

    public static void main(String[] args) {
        int n = nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = nextInt();
        int[] frr = new int[n];
        frr[0] = arr[0]; //设定默认第一个数
        int len = 1;
        for (int i = 1; i < n; ++i) {
            if (arr[i] > frr[len - 1]) { //大则添加末尾
                frr[len++] = arr[i];
            } else { //小于等于则替换第一个出现的 >= arr[i] 的战力值（有可能就是末尾值）(为之后更小的数争取最长子序列)
                int l = 0, r = len - 1, mid;
                while (l < r) {
                    mid = (l + r) >>> 1;
                    if (frr[mid] >= arr[i]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                frr[l] = arr[i]; //替换
            }
        }
        System.out.print(len);
    }
}
