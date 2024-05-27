package lanqiaobei;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class dandiaozhan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//楼房数
        int[] height = new int[n];//楼房高度
        Stack<Integer> st = new Stack<>();//单调栈1求右高用
        //栈中存放的始终是没找到最高的楼房的下标
        //一旦找到最高则出栈
        Stack<Integer> st2 = new Stack<>();//栈2求左高用
        int[] leftH = new int[n];//装最高楼房编号
        int[] rightH = new int[n];//装右高楼房编号
        Arrays.fill(leftH, -1);//填充-1
        Arrays.fill(rightH, -1);//填充-1
        for (int i = 0; i < n; i++) {
            height[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int h = height[i];
            while (!st.isEmpty() && h > height[st.peek()]) {//当前元素大于栈顶元素对应楼房高度
                int j = st.pop();//找到栈顶元素对应楼房的更高了，出栈，记录楼房下标
                rightH[j] = i + 1;//将该楼房的编号填到右高数组对应位置，注意编号从1开始
            }
            st.push(i);
        }
        //左高同理~用心体会
        for (int i = n - 1; i >= 0; i--) {
            int h = height[i];
            while (!st2.isEmpty() && h > height[st2.peek()]) {
                int j = st2.pop();
                leftH[j] = i + 1;
            }
            st2.push(i);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(leftH[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(rightH[i] + " ");
        }
    }
}
