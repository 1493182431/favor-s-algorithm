/**
 * @description:
 * @author：Favor
 * @date: 2024/5/9
 */
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class shiyaner {
    public static final int[][] DES_S_BOX = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13},
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12},
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
            {2, 12, 4, 1, 7, 10, 11, 6, 5, 8, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 13, 3, 9, 8, 6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    public static final int[] E = {
            31, 0, 1, 2, 3, 4,
            3, 4, 5, 6, 7, 8,
            7, 8, 9, 10, 11, 12,
            11, 12, 13, 14, 15, 16,
            15, 16, 17, 18, 19, 20,
            19, 20, 21, 22, 23, 24,
            23, 24, 25, 26, 27, 28,
            27, 28, 29, 30, 31, 0};
    static int[][][] count = new int[8][64][16];

    public static void DES() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 64; j++)//第一个输入j
                for (int k = 0; k < 64; k++) {//第二个输入k
                    int t1 = (j & 0x20) | ((j & 0x01) << 4) | ((j >> 1) & 0xf);//用于第一个输入查找s盒
                    int t2 = (k & 0x20) | ((k & 0x01) << 4) | ((k >> 1) & 0xf);//用于第二个输入查找s盒
                    count[i][j ^ k][DES_S_BOX[i][t1] ^ DES_S_BOX[i][t2]]++;//对应分布表的项加一
                }
    }

    public static Map<String, Double> maxValue() {
        // 创建一个用于存储字符串和概率值的Map
        Map<String, Double> mp = new HashMap<>();

        // 循环计算不同参数下的概率值
        for (int h = 0; h < 6; h++) {
            for (int i = 1; i < 16; i++) {
                for (int j = 1; j < 16; j++) {
                    for (int k = 1; k < 16; k++) {
                        // 生成十六进制字符串
                        String hstr = String.join("", Collections.nCopies(h, "0"))
                                + Integer.toHexString(i) + Integer.toHexString(j) + Integer.toHexString(k)
                                + String.join("", Collections.nCopies(5 - h, "0"));

                        // 将十六进制字符串转换为二进制字符串
                        String bstr = String.format("%32s", new BigInteger(hstr, 16).toString(2)).replace(' ', '0');
                        StringBuilder out = new StringBuilder();

                        // 从二进制字符串中提取特定位置的字符
                        for (int e : E) {
                            out.append(bstr.charAt(e));
                        }

                        // 计算概率值
                        double p = 1.0;
                        for (int l = 0; l < 8; l++) {
                            p *= count[l][Integer.parseInt(out.toString().substring(l * 6, (l + 1) * 6), 2)][0] / 64.0;
                        }

                        // 将结果存入Map
                        mp.put(hstr, p);
                    }
                }
            }
        }

        // 对Map按值进行降序排序并返回排序后的Map
        return mp.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        DES();
        Map<String, Double> mp = maxValue();
        mp.entrySet().stream().limit(3).forEach(entry -> System.out.println("输入: " + entry.getKey() + ", 对应概率为: " + entry.getValue()));
    }
}

