import org.junit.Test;

import java.util.*;

public class good {
    public boolean hasPathSum(algorithmtest.TreeNode root, int targetSum) {
        // java基本数据类型是值传递，在深层递归中改变的值不会传递到浅层递归。

        // Java包装类对象的状态是不可变的，这意味着一旦创建了Integer、Boolean、Double等包装类对象，它们的值就无法被修改。
        // 当你尝试修改包装类对象的值时，实际上是创建了一个新的包装类对象，而原始对象的值保持不变。

        // Java中，有一些类的对象状态是可以改变的，这些类通常被称为可变类。这些类包括但不限于：
        //StringBuilder和StringBuffer：这两个类表示可变的字符序列，可以通过调用append、insert、delete等方法来改变其状态。
        //ArrayList、LinkedList等集合类：这些类表示可变的集合，可以通过添加、删除元素来改变其状态。
        //HashMap、TreeMap等映射类：这些类表示可变的映射关系，可以通过插入、删除键值对来改变其状态。
        //自定义的可变类：你可以创建自己的类，并使其状态可变，以便在对象中保存和修改数据。
        //总的来说，任何类都可以被设计成可变的，只要在类的设计中允许对象状态的改变。

        // 此时使用ArrayList，在深层递归中改变的值会传递到浅层递归
        List<Integer> list = new ArrayList<>();
        list.add(1);
        inHasPathSum(root, targetSum, list);
        return list.get(0) == 0;
    }

    public void inHasPathSum(algorithmtest.TreeNode node, int targetSum, List<Integer> list) {
        if (node == null) return;

        targetSum -= node.val;
        if (node.left == null && node.right == null && targetSum == 0) list.set(0, 0);
        inHasPathSum(node.left, targetSum, list);

        inHasPathSum(node.right, targetSum, list);

        targetSum += node.val;
    }


    // KMP 算法
    // ss: 原串(string)  pp: 匹配串(pattern)
    public int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }


    //    全加器：A，B，Carry0，Sum，Carry1
    //    Sum = A ^ B ^ Carry0
    //    Carry1 = A & Carry0 | A & B | B & Carry0 或 Carry1 = (A & B) | Carry0 & (A ^ B)
    public int getSum(int a, int b) {
        int Sum = 0;
        int Carry = 0;
        int ans = 0;
        int A = 0;
        int B = 0;
        for (int i = 0; i < 32; i++) {
            A = a & 1;
            B = b & 1;
            Sum = A ^ B ^ Carry;
            Carry = A & Carry | A & B | B & Carry;
            ans ^= Sum << i;
            a >>= 1;
            b >>= 1;
        }
        return ans;
    }


    // 自定义排序
    @Test
    public void Comparator() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);
        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);
        // 定制排序的用法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // lambda简化
        // Collections.sort(arrayList, (o1, o2) -> o2 - o1);
        System.out.println("定制排序后：");
        System.out.println(arrayList);

        // lambda简化
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        set.add(1);
        set.add(2);
        set.add(5);
        set.add(6);
        set.add(0);
        set.add(9);
        set.add(7);
        System.out.println(set);
    }


    //    int[][] matrix=new int[row][column];
    //    row=matrix.length;
    //    column=matrix[0].length;
    //    row表示矩阵的行数，column表示矩阵的列数
    //    [[1,2],[3,4],[5,6],[7,8],[9,10]] row=5,column=2
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }

        int[][] ans = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            ans[x / c][x % c] = mat[x / n][x % n];
        }
        return ans;

    }



    //    public int reverseBits(int n) {
    //        int ans = 0;
    //        for (int i = 0; i < 32; i++) {
    //            ans ^= (((1 << i) & n) >>> i) << 31 - i;
    //        }
    //        return ans;
    //    }

    //颠倒给定的 32 位无符号整数的二进制位
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    //
//- 第一次位运算：n = n >>> 1 & M1 | (n & M1) << 1
//    - n >>> 1将n向右移动1位，同时将高位补0
//    - n & M1将n与M1按位与，提取出n中奇数位的值
//    - (n & M1) << 1将奇数位的值向左移动1位
//    - 最后将这两个值合并，得到偶数位和奇数位交换的结果
//
//- 第二次位运算：n = n >>> 2 & M2 | (n & M2) << 2
//    - n >>> 2将n向右移动2位，同时将高位补0
//    - n & M2将n与M2按位与，提取出n中每2位的值
//    - (n & M2) << 2将每2位的值向左移动2位
//    - 最后将这两个值合并，得到相邻2位分别在4位之间交换的结果
//
//- 第三次位运算：n = n >>> 4 & M4 | (n & M4) << 4
//    - n >>> 4将n向右移动4位，同时将高位补0
//    - n & M4将n与M4按位与，提取出n中每4位的值
//    - (n & M4) << 4将每4位的值向左移动4位
//    - 最后将这两个值合并，得到相邻4位分别在8位之间交换的结果
//
//- 第四次位运算：n = n >>> 8 & M8 | (n & M8) << 8
//    - n >>> 8将n向右移动8位，同时将高位补0
//    - n & M8将n与M8按位与，提取出n中每8位的值
//    - (n & M8) << 8将每8位的值向左移动8位
//    - 最后将这两个值合并，得到相邻8位分别在16位之间交换的结果
//
//- 最后一步：return n >>> 16 | n << 16
//    - n >>> 16将n向右移动16位，同时将高位补0
//    - n << 16将n向左移动16位，同时将低位补0
//    - 最后将这两个值合并返回，得到完全反转后的结果
    @Test
    public void syutdi() {
        System.out.println(reverseBits(12345678));
    }




}
