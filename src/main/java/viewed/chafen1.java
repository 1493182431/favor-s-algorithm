package viewed;

import java.io.*;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class chafen1 {

    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = Int(), q = Int();//分别表示数组长度和操作次数
        int[] arr = new int[n];  //接着读入数组arr
        for(int i = 0;i<n;i++){
            arr[i]= Int();
        }
        long[] diff = new long[n+1];  //定义一个长度为n+1的long类型数组diff，用于记录每个位置的差值

        for(int i = 0;i<q;i++){  //循环q次，每次读入l、r、d，表示将区间[l,r]中的每个数加上d
            int l = Int()-1;
            int r = Int()-1;
            int d = Int();
            diff[l]+=d;  //diff[l]加上d
            diff[r+1]-=d;//diff[r-1]减去d
        }
        for(int i =1;i<diff.length;i++) diff[i] +=diff[i-1];  //对diff数组进行前缀和操作，得到每个位置的实际差值
        for(int i =0;i<n;i++) {//遍历数组arr,将每个位置的值加上对应的差值，如果结果小于0，则输出0，否则输出结果。
            pw.print((arr[i]+diff[i]>0? arr[i]+diff[i]:0) + " ");
        }
        pw.flush();// 刷新缓冲区
    }

    public static int Int() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
