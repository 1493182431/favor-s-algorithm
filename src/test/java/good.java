import org.junit.Test;

import java.util.*;

public class good {

    // &&有短路作用！！！
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


    @Test
    public void KMP_Test() {
        System.out.println(strStr("aaa", "aaa"));
    }

    // KMP算法
    // ss文本串 pp模式串 在ss中找到第一个与pp匹配的子串并返回匹配到的下标
    public int strStr(String ss, String pp) {
        // 构造next数组
        int[] next = new int[pp.length()];
        int prev = 0, index = 1;
        next[0] = 0;
        while (index < pp.length()) {
            // 若待求next[index]与上一个最长前后缀中的前缀的下一个字符相等则next[index]=next[index-1]+1
            if (pp.charAt(prev) == pp.charAt(index)) {
                prev++;
                next[index] = prev;
                index++;
                // 若上一个最长前后缀长度==0，则index++，并且next[index]=0，创建数组时默认为0
            } else if (prev == 0) index++;
                // 若上一个最长前后缀长度!=0，并且待求next[index]与上一个最长前后缀中的前缀的下一个字符不相等
                // 则上一个最长前后缀长度变为上一个最长前后缀中最长前后缀的长度
            else prev = next[prev - 1];
        }

        // 字符串匹配
        int i = 0, j = 0;
        while (i < ss.length()) {
            if (ss.charAt(i) == pp.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) j = next[j - 1];
            else i++;
            if (j == pp.length()) return i - j;
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



    public boolean CanConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // map.getOrDefault：得到key对应的value，如果没有则得到默认值
        for (char c : ransomNote.toCharArray()) {
            if (map.containsKey(c)) {
                int count = map.get(c);
                if (count > 1) {
                    map.put(c, count - 1);
                } else {
                    map.remove(c);
                }
            } else {
                return false;
            }
        }
        return true;
    }



    public int firstUniqChar(String s) {
        // 若需要的哈希表容量较小可以考虑自己构建哈希表
        int[] arr = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (arr[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }



    // 大整数加法
    // 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
    // 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            // 本位
            ans.append(result % 10);
            // 进位
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    // 大整数乘法
    // 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    // 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        // m位数*n位数的结果最多位m+n位数
        int[] res = new int[m + n];
        // 处理每位的乘积
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                res[i + j + 1] += x * y;
            }
        }
        // 处理进位
        for (int i = m + n - 1; i > 0; i--) {
            res[i - 1] += res[i] / 10;
            res[i] %= 10;
        }
        // m位数*n位数的结果最少为m+n-1位数
        int index = res[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (index < m + n) {
            sb.append(res[index]);
            index++;
        }
        return sb.toString();
    }



    // 生成杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>>ans=new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer>tmp=new ArrayList<>();
            tmp.add(1);
            for (int j = 1; j <= i; j++) {
                // 组合数递推公式 Cm,n=n!/m!*(n-m)! Cm,n=Cm-1,n*(n-m+1)/m
                // ArrayList中set方法只能改变已存在的下标对应的值
                tmp.add(tmp.get(j-1)*(1+i-j)/j);
            }
            ans.add(tmp);
        }
        return ans;
    }

}
