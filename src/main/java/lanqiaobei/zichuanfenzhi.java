package lanqiaobei;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/10
 */
public class zichuanfenzhi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        String str=scan.nextLine();
        int ans=str.length();
        for (int i = 2; i <=str.length(); i++) {
            int index=0;
            while (index+i<=str.length()){
                String tmp=str.substring(index, index+i);
                int[]ch=new int[26];//若需要存储的长度有限，则使用数组作为哈希表可以提升速度
                for (int j = 0; j < tmp.length(); j++) {
                    ch[tmp.charAt(j)-'a']++;
                }
                for (int j : ch) {
                    if (j == 1) ans++;
                }
                index++;
            }
        }
        System.out.println(ans);
        scan.close();
    }
}
