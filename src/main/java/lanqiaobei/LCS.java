package lanqiaobei;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class LCS {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n=scan.nextInt(),m=scan.nextInt();
        int []s1=new int[n+1];//矩阵行增大1，行序列数组也需增大1，方便下标对齐
        for (int i = 1; i < s1.length; i++) {
            s1[i]=scan.nextInt();
        }
        int []s2=new int[m+1];//矩阵列增大1，列序列数组也需增大1，方便下标对齐
        for (int i = 1; i < s2.length; i++) {
            s2[i]=scan.nextInt();
        }
        //dp[i][j]表示序列s1[1]~s1[i]和序列s2[1]~s2[j]的LCS（最长公共子序列）的长度
        int[][]dp=new int[n+1][m+1];//第一行第一列都需设为0，故矩阵大小需要比实际大一行和一列
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(s1[i]==s2[j])dp[i][j]=dp[i-1][j-1]+1;//若当前两序列字符相同，则取同时减去1个字符的值
                else dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);//若当前两序列字符不相同，则取分别减去1个字符的最大值
            }
        }
        System.out.println(dp[n][m]);
        scan.close();
    }
}
