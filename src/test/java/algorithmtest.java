import org.junit.Test;
import org.w3c.dom.Node;

import java.util.*;

public class algorithmtest {
    // 树节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    // 深度遍历测试
    public void DFS() {
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        DFS(buildTree(3, 9, 20, null, null, 15, 7), preOrder, inOrder, postOrder);
        System.out.println("前序遍历：" + preOrder);
        System.out.println("中序遍历：" + inOrder);
        System.out.println("后序遍历：" + postOrder);
    }

    // 深度遍历
    public void DFS(TreeNode node, List<Integer> preOrder, List<Integer> inOrder, List<Integer> postOrder) {
        if (node == null) return;

//        preOrder.add(0);
//        inOrder.add(0);
//        postOrder.add(0);
        // 第一次经过某节点时

        preOrder.add(node.val);

        DFS(node.left, preOrder, inOrder, postOrder);

        inOrder.add(node.val);

//        preOrder.add(0);
//        inOrder.add(0);
//        postOrder.add(0);
        // 从左子树到右子树经过某节点时

        DFS(node.right, preOrder, inOrder, postOrder);

        postOrder.add(node.val);

        // 最后一次经过某节点时
//        preOrder.add(0);
//        inOrder.add(0);
//        postOrder.add(0);


        //if(node.left==null&&node.right!=null)preOrder.add(999);
//        if(node.left==null&&node.right==null)preOrder.add(-1);// 叶子节点
//        else if(node.left==null||node.right==null)preOrder.add(0);// 只有1个子节点的节点
//        else preOrder.add(999);// 有2个子节点的节点
    }

    @Test
    // 广度遍历测试
    public void BFS() {
        List<Integer> list = new ArrayList<>();

        BFS(buildTree(4, 2, 7, 1, 3, 6, 9), list);

        System.out.println(list);
    }

    // 广度遍历
    public void BFS(TreeNode root, List<Integer> list) {
        if (root == null) return;
        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 初始化一个列表，用于保存遍历序列
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 队列出队
            list.add(node.val);           // 保存节点值
            if (node.left != null)
                queue.offer(node.left);   // 左子节点入队
            if (node.right != null)
                queue.offer(node.right);  // 右子节点入队
        }
    }

    // 传入层序遍历数组简单构造二叉树
    public TreeNode buildTree(Integer... a) {
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        return arrayToBinaryTree(list);
    }

    // 层序遍历数组构造二叉树
    public TreeNode arrayToBinaryTree(List<Integer> list) {

        // 列表为空表示列表已经被创建但是没有元素，而列表为null表示列表并未被创建,两种情况都要避免。
        if (list == null || list.isEmpty()) return null;

        // 根节点放入列表第一个元素
        TreeNode root = new TreeNode(list.get(0));
        int index = 0;// 索引
        int len = list.size();// 列表长度

        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll(); // 队列出队

            if ((index + 1) < len && list.get(++index) != null) {// 左子节点赋值并入队
                node.left = new TreeNode(list.get(index));
                queue.offer(node.left);
            }
            if ((index + 1) < len && list.get(++index) != null) {// 右子节点赋值并入队
                node.right = new TreeNode(list.get(index));
                queue.offer(node.right);
            }
        }
        return root;
    }

    /* 构建二叉树：分治 */
    TreeNode dfs(int[] preorder, Map<Integer, Integer> inorderMap, int i, int l, int r) {
        // 子树区间为空时终止
        if (r - l < 0)
            return null;
        // 初始化根节点
        TreeNode root = new TreeNode(preorder[i]);
        // 查询 m ，从而划分左右子树
        int m = inorderMap.get(preorder[i]);
        // 子问题：构建左子树
        root.left = dfs(preorder, inorderMap, i + 1, l, m - 1);
        // 子问题：构建右子树
        root.right = dfs(preorder, inorderMap, i + 1 + m - l, m + 1, r);
        // 返回根节点
        return root;
    }

    /* 构建二叉树 */
    TreeNode buildTreeByPreAndIn(int[] preorder, int[] inorder) {
        // 初始化哈希表，存储 inorder 元素到索引的映射
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return dfs(preorder, inorderMap, 0, 0, inorder.length - 1);
    }

    // 链表节点
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    @Test
    public void buildLinkedList() {
        List<Integer> list = new ArrayList<>();
        ListNode head = buildLinkedList(1, 2, 4, 5, 6, 9);
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        System.out.println(list);
    }

    public void travelLinkedList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        System.out.println(list);
    }

    public ListNode buildLinkedList(Integer... a) {
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        if (list.isEmpty() || list == null) return null;
        ListNode head = new ListNode(list.get(0));
        ListNode last = head;
        for (int i = 1; i < list.size(); i++) {
            ListNode node = new ListNode(list.get(i));
            node.next = null;
            last.next = node;
            last = node;
        }
        return head;
    }

    @Test
    public void isSameTree() {
        System.out.println(isSameTree(buildTree(1, 2, 1), buildTree(1, 1, 2)));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        int[] flag = new int[]{1};
        in1(p, q, flag);
        return flag[0] == 1;
    }

    public void in1(TreeNode p, TreeNode q, int[] flag) {
        if (p == null && q == null) return;

        if (p == null || q == null) {
            flag[0] = 0;
            return;
        } else if (p.val != q.val) {
            flag[0] = 0;
        }
        in1(p.left, q.left, flag);
        in1(p.right, q.right, flag);
    }

    @Test
    public void isSymmetric() {
        System.out.println(isSymmetric(buildTree(1, 2, 2, null, 3, null, 3)));
    }

    public boolean isSymmetric(TreeNode root) {
        int[] flag = new int[]{1};
        TreeNode p = root.left;
        TreeNode q = root.right;
        in2(p, q, flag);
        return flag[0] == 1;
    }

    public void in2(TreeNode p, TreeNode q, int[] flag) {
        if (p == null && q == null) return;

        if (p == null || q == null) {
            flag[0] = 0;
            return;
        } else if (p.val != q.val) {
            flag[0] = 0;
        }
        in2(p.left, q.right, flag);
        in2(p.right, q.left, flag);
    }

    @Test
    public void MinDepth() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(MinDepth(arrayToBinaryTree(list)));
    }

    public int MinDepth(TreeNode root) {
        if (root == null) return 0;
        int[] list = new int[]{999999, 0};
        minDepth(root, list);
        return list[0];
    }

    public void minDepth(TreeNode node, int[] list) {
        if (node == null) return;

        list[1]++;
        if (node.left == null && node.right == null) {
            list[0] = Math.min(list[0], list[1]);
        }

        minDepth(node.left, list);
        minDepth(node.right, list);

        list[1]--;

    }

    @Test
    public void hasPathSum() {
        List<Integer> p = new ArrayList<>(Arrays.asList(1, 2));

        System.out.println(hasPathSum(arrayToBinaryTree(p), 1));
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
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

    public void inHasPathSum(TreeNode node, int targetSum, List<Integer> list) {
        if (node == null) return;

        targetSum -= node.val;
        if (node.left == null && node.right == null && targetSum == 0) list.set(0, 0);
        inHasPathSum(node.left, targetSum, list);

        inHasPathSum(node.right, targetSum, list);

        targetSum += node.val;
    }

    @Test
    public void invertTree() {
        List<Integer> list = new ArrayList<>();
        TreeNode treeNode = invertTree(buildTree(4, 2, 7, 1, 3, 6, 9));
        BFS(treeNode, list);
        System.out.println(list);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 初始化队列，加入根节点
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        TreeNode tmp = new TreeNode();
        // 初始化一个列表，用于保存遍历序列
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast(); // 队列出队
            if (node.right != null && node.left != null) {
                deque.offer(node.left);
                deque.offer(node.right);
                tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            } else if (node.right == null && node.left != null) {
                deque.offer(node.left);
                node.right = node.left;
                node.left = null;
            } else if (node.left == null && node.right != null) {
                deque.offer(node.right);
                node.left = node.right;
                node.right = null;
            }
        }
        return root;
    }

    @Test
    public void binaryTreePaths() {
        System.out.println(binaryTreePaths(buildTree(37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null, null, null, 8)));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        Stack<String> tmp = new Stack<>();
        List<String> list = new ArrayList<>();
        in3(root, list, tmp);
        return list;
    }

    public void in3(TreeNode node, List<String> list, Stack<String> tmp) {
        if (node == null) return;
        tmp.push(String.valueOf(node.val));
        tmp.push("->");
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tmp.size() - 1; i++) {
                sb.append(tmp.get(i));
            }
            list.add(sb.toString());
        }
        in3(node.left, list, tmp);
        in3(node.right, list, tmp);
        tmp.pop();
        tmp.pop();
    }

    @Test
    public void sumOfLeftLeaves() {
        System.out.println(sumOfLeftLeaves(buildTree(1)));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        int[] ans = new int[]{0};
        in4(root, ans);
        return ans[0];
    }

    public void in4(TreeNode node, int[] ans) {
        if (node == null) return;
        in4(node.left, ans);
        if (node.left != null && node.left.left == null && node.left.right == null) {
            ans[0] += node.left.val;
        }
        in4(node.right, ans);
    }

    @Test
    public void isValidBST() {
        System.out.println(isValidBST(buildTree(5, 1, 4, null, null, 3, 6)));
    }

    public boolean isValidBST(TreeNode root) {
        long[] ans = new long[]{0, 1};
        ans[0] = Long.MIN_VALUE;
        in5(root, ans);
        return ans[1] == 1;
    }

    public void in5(TreeNode node, long[] ans) {
        if (node == null) return;
        in5(node.left, ans);
        if (ans[0] >= node.val) ans[1] = 0;
        ans[0] = node.val;
        in5(node.right, ans);
    }

    @Test
    public void levelOrder() {
        System.out.println(levelOrder(buildTree(3, 9, 20, null, null, 15, 7)));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        lev1(root, list);
        return list;
    }

    public void lev1(TreeNode root, List<List<Integer>> list) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                tmp.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            list.add(tmp);
        }
    }

    @Test
    public void zigzagLevelOrder() {
        System.out.println(zigzagLevelOrder(buildTree(3, 9, 20, null, null, 15, 7)));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        lev2(root, list);
        return list;
    }

    public void lev2(TreeNode root, List<List<Integer>> list) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if ((index & 1) == 0) tmp.add(0, poll.val);
                else tmp.add(poll.val);
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            list.add(tmp);
            index++;
        }
    }

    @Test
    public void pathSum() {
        System.out.println(pathSum(buildTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1), 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();

        inHasPathSum(root, targetSum, tmp, list);
        return list;
    }

    public void inHasPathSum(TreeNode node, int targetSum, List<Integer> tmp, List<List<Integer>> list) {
        if (node == null) return;

        targetSum -= node.val;
        tmp.add(node.val);
        if (node.left == null && node.right == null && targetSum == 0) {
            list.add(new ArrayList<>(tmp));
        }
        inHasPathSum(node.left, targetSum, tmp, list);

        inHasPathSum(node.right, targetSum, tmp, list);

        targetSum += node.val;
        tmp.remove(tmp.size() - 1);
    }

    @Test
    public void buildTree() {
        int[] a = new int[]{3, 9, 20, 15, 7};
        int[] b = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(a, b));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode();
        List<TreeNode> stack = new ArrayList<>();

        for (int i = 0; i < preorder.length; i++) {

        }
        return null;
    }

    public int sumNumbers(TreeNode root) {
        int[] ans = new int[1];
        List<Integer> tmp = new ArrayList<>();
        INsumNumbers(root, ans, tmp);
        return ans[0];
    }

    public void INsumNumbers(TreeNode node, int[] ans, List<Integer> tmp) {
        if (node == null) return;
        tmp.add(node.val);
        if (node.left == null && node.right == null) {
            for (int i = 0; i < tmp.size(); i++) {
                ans[0] += (int) (tmp.get(i) * Math.pow(10.0, tmp.size() - i - 1));
            }
        }
        INsumNumbers(node.left, ans, tmp);
        INsumNumbers(node.right, ans, tmp);
        tmp.remove(tmp.size() - 1);
    }

    @Test
    public void canPermutePalindrome() {
        System.out.println(canPermutePalindrome("abdg"));
    }

    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() == 1 || set.isEmpty();
    }

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        in7(root, map);
        for (Integer value : map.values()) {
            max = Math.max(value, max);
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) list.add(entry.getKey());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void in7(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) return;

        in7(node.left, map);
        if (map.containsKey(node.val)) map.put(node.val, map.get(node.val) + 1);
        else map.put(node.val, 1);
        in7(node.right, map);
    }

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        lev1(root, list);
        return list;
    }

    public void lev1(Node root, List<List<Integer>> list) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                tmp.add(poll.val);
                for (Node child : poll.children) {
                    if (child != null) queue.add(child);
                }
            }
            list.add(tmp);
        }
    }

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        pre1(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i).left = null;
        }
    }

    public void pre1(TreeNode node, List<TreeNode> list) {
        if (node == null) return;
        list.add(node);
        pre1(node.left, list);
        pre1(node.right, list);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        in6(root, list);
        return list.get(k - 1);
    }

    public void in6(TreeNode node, List<Integer> list) {
        if (node == null) return;

        in6(node.left, list);
        list.add(node.val);
        in6(node.right, list);
    }

    public static String compressString(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        int a = 0, b = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(a) != S.charAt(b)) {
                stringBuilder.append(S.charAt(a));
                stringBuilder.append(b - a);
                a = b;
            }
            b++;
        }
        if (b != a) {
            stringBuilder.append(S.charAt(a));
            stringBuilder.append(b - a);
        }

        return S.length() < stringBuilder.length() ? S : stringBuilder.toString();
    }

    public String replaceSpaces(String S, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') stringBuilder.append("%20");
            else stringBuilder.append(S.charAt(i));
        }
        return stringBuilder.toString();
    }

    public boolean CheckPermutation(String s1, String s2) {
        char[] charArray1 = s1.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = s2.toCharArray();
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        for (char c : astr.toCharArray()) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    public int[] diStringMatch(String s) {
        int max = s.length(), min = 0;
        int[] ans = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = min;
                min++;
            } else {
                ans[i] = max;
                max--;
            }
        }
        ans[s.length() + 1] = s.charAt(s.length() - 1) == 'I' ? max : min;
        return ans;
    }

    public boolean validPalindrome(String s) {
        int delete1 = 1, delete2 = 1;
        boolean flag1 = true, flag2 = true;
        for (int i = 0, j = s.length() - 1; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(j) && delete1 == 1) {
                j--;
                delete1 = 0;
            }
            if (s.charAt(i) == s.charAt(j)) j--;
            else flag1 = false;
        }
        for (int i = 0, j = s.length() - 1; j > s.length() / 2; j--) {
            if (s.charAt(i) != s.charAt(j) && delete2 == 1) {
                i++;
                delete2 = 0;
            }
            if (s.charAt(i) == s.charAt(j)) i++;
            else flag2 = false;
        }
        return flag1 || flag2;
    }

    public int climbStairs(int n) {
        if (n == 1 || n == 0) return 1;
        int one = 1, two = 1, tmp = 0;
        for (int i = 2; i <= n; i++) {
            tmp = one + two;
            two = one;
            one = tmp;
        }
        return tmp;
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) map.put(c, map.get(c) + 1);
            else map.put(c, 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
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

    public boolean canConstruct(String ransomNote, String magazine) {
        int len = ransomNote.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            if (map.containsKey(ransomNote.charAt(i))) map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) + 1);
            else map.put(ransomNote.charAt(i), 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i)) && map.get(magazine.charAt(i)) > 0) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) - 1);
                len--;
            }
        }
        return len == 0;
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i : nums1) {
            if (map.containsKey(i)) map.put(i, map.get(i) + 1);
            else map.put(i, 1);
        }
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                ans.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        Set<ListNode> set = new HashSet<>();
        while (a != null || b != null) {
            if (!set.add(a) && a != null) {
                return a;
            }
            if (!set.add(b) && b != null) {
                return b;
            }
            a = a == null ? null : a.next;
            b = b == null ? null : b.next;
        }
//        while (b!=null){
//            if(set.contains(b))return b;
//            b=b.next;
//        }
        return null;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        Set<ListNode> set = new HashSet<>();
        ListNode now = head;
        while (now.next != null) {
            if (set.contains(now.next)) return true;
            set.add(now);
            now = now.next;
        }
        return false;
    }

    public int romanToInt(String s) {
        int f = 2000, l = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M': {
                    l = 1000;
                    break;
                }
                case 'D': {
                    l = 500;
                    break;
                }
                case 'C': {
                    l = 100;
                    break;
                }
                case 'L': {
                    l = 50;
                    break;
                }
                case 'X': {
                    l = 10;
                    break;
                }
                case 'V': {
                    l = 5;
                    break;
                }
                case 'I': {
                    l = 1;
                    break;
                }
            }
            if (l > f) ans -= 2 * f;
            f = l;
            ans += l;
        }
        return ans;
    }

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            if (i >= 3) {
                ans[arr[i][1]] = Integer.toString(i + 1);
            } else {
                ans[arr[i][1]] = desc[i];
            }
        }
        return ans;
    }

    public void reverseString(char[] s) {
        char temp;
        int len = s.length;
        for (int i = 0; i < len / 2; i++) {
            temp = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = temp;
        }
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] split = s.split(" ");
        if (pattern.length() != split.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i)) && !Objects.equals(map.get(pattern.charAt(i)), split[i])) {
                return false;
            } else if (!map.containsKey(pattern.charAt(i)) && map.containsValue(split[i])) {
                return false;
            } else {
                map.put(pattern.charAt(i), split[i]);
            }
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        char[] scharArray = s.toCharArray();
        char[] tcharArray = t.toCharArray();
        Arrays.sort(scharArray);
        Arrays.sort(tcharArray);
        return Arrays.equals(scharArray, tcharArray);
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            } else if (!map.containsKey(s.charAt(i)) && map.containsValue(t.charAt(i))) {
                return false;
            } else {
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public String addBinary(String a, String b) {
        //模拟全加器
        StringBuilder ans = new StringBuilder();
        int sum = 0, carry = 0, A = 0, B = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (!(i < 0 && j < 0)) {
            A = i < 0 ? 0 : a.charAt(i) == '0' ? 0 : 1;
            B = j < 0 ? 0 : b.charAt(j) == '0' ? 0 : 1;
            sum = carry ^ A ^ B;
            carry = (A & B) | carry & (A ^ B);
            ans.append(sum);
            i--;
            j--;
        }
        if (carry > 0) {
            ans.append('1');
        }
        return ans.reverse().toString();
    }

    public int lengthOfLastWord(String s) {
        int lenght = 0, size = s.length() - 1;
        if (s.charAt(s.length() - 1) == ' ') {
            while (s.charAt(size) == ' ') {
                size--;
            }
        }
        for (int i = size; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                lenght++;
            } else break;

        }
        return lenght;
    }

    public boolean isValid(String s) {

        List<Character> stack = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !stack.isEmpty() && stack.get(stack.size() - 1) == '(') {
                stack.remove(stack.size() - 1);
            } else if (c == ']' && !stack.isEmpty() && stack.get(stack.size() - 1) == '[') {
                stack.remove(stack.size() - 1);
            } else if (c == '}' && !stack.isEmpty() && stack.get(stack.size() - 1) == '{') {
                stack.remove(stack.size() - 1);
            } else {
                stack.add(c);
            }
        }
        return stack.isEmpty();
    }

    public String longestCommonPrefix(String[] strs) {

        String ans = strs[0];
        for (String str : strs) {
            if (str.length() < ans.length()) {
                ans = ans.substring(0, str.length());
            }
            for (int i = 0; i < ans.length(); i++) {
                if ((ans.charAt(i) ^ str.charAt(i)) != 0) {
                    ans = ans.substring(0, i);
                }

            }
        }
        return ans;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>(Collections.nCopies(nums.length + 1, 0));
        temp.set(0, 1);

        for (Integer i : nums) {
            temp.set(i, 1);
        }
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) == 0) ans.add(i);
        }
        return ans;
    }

    public int thirdMax(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] array = set.stream().mapToInt(Integer::intValue).toArray();
        if (array.length < 3) return array[array.length - 1];
        return array[array.length - 3];
    }

    public List<Integer> getRow(int rowIndex) {
        int index = rowIndex + 1;
        List<List<Integer>> lists = new ArrayList<>(index);
        for (int i = 1; i <= index; i++) {
            List<Integer> listInside = new ArrayList<>(i);
            listInside.add(1);
            for (int j = 1; j < i - 1; j++) {
                listInside.add(lists.get(i - 2).get(j - 1) + lists.get(i - 2).get(j));
            }
            if (i > 1) {
                listInside.add(1);
            }
            lists.add(listInside);
        }
        return lists.get(index);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            List<Integer> listInside = new ArrayList<>(i);
            listInside.add(1);
            for (int j = 1; j < i - 1; j++) {
                listInside.add(lists.get(i - 2).get(j - 1) + lists.get(i - 2).get(j));
            }
            if (i > 1) {
                listInside.add(1);
            }
            lists.add(listInside);
        }
        return lists;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    @Test
    public void rotate() {
        int[][] matrix = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate(int[][] matrix) {
        if (matrix.length == 0) return;
        int len = matrix.length - 1;
        int tmplen = matrix.length - 1;
        int start = 0;
        while (start <= tmplen) {
            for (int i = start; i < tmplen; i++) {
                int temp = matrix[start][i];
                // 每次循环交换4个元素
                matrix[start][i] = matrix[len - i][start];
                matrix[len - i][start] = matrix[len - start][len - i];
                matrix[len - start][len - i] = matrix[i][len - start];
                matrix[i][len - start] = temp;
            }
            start++;
            tmplen--;
        }
    }

    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> column = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    column.add(j);
                }
            }
        }


        for (Integer i : row) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
        for (Integer j : column) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        }

    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        String tmp = s2 + s2;
        return tmp.contains(s1);
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<Integer> occurred = new HashSet<Integer>();
        occurred.add(head.val);
        ListNode pos = head;
        // 枚举前驱节点
        while (pos.next != null) {
            // 当前待删除节点
            ListNode cur = pos.next;
            if (occurred.add(cur.val)) {
                pos = pos.next;
            } else {
                pos.next = pos.next.next;
            }
        }
        pos.next = null;
        return head;
    }

    public int kthToLast(ListNode head, int k) {
        ListNode first = head;
        ListNode last = head;
        for (int i = 1; i < k; i++) {
            last = last.next;
        }
        while (last.next != null) {
            first = first.next;
            last = last.next;
        }
        return first.val;
    }

    public void deleteNode(ListNode node) {
        while (true) {
            node.val = node.next.val;
            if (node.next.next == null) {
                node.next = null;
                break;
            }
            node = node.next;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;

        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) return false;
        }

        return true;
    }

    public ListNode dgetIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (!set.add(headA)) return headA;
                headA = headA.next;
            }
            if (headB != null) {
                if (!set.add(headB)) return headB;
                headB = headB.next;
            }
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) return head;
            head = head.next;
        }
        return null;
    }

    @Test
    public void reverseList() {
        travelLinkedList(reverseList(buildLinkedList(1, 2, 3, 4, 5)));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first;
        ListNode second = null;
        while (head != null) {
            if (second == null) first = null;
            else first = second;
            second = new ListNode(head.val);
            head = head.next;
            second.next = first;
        }
        return second;
    }

    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int ans = 0;
        while (tmp != 0) {
            ans += tmp & 1;
            tmp = tmp >> 1;
        }
        return ans;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        ImergeTrees(root1, root2);
        root1.val += root2.val;
        return root1;
    }

    public void ImergeTrees(TreeNode ans, TreeNode tmp) {
        if (ans == null || tmp == null) return;

        if (ans.left != null) {
            if (tmp.left != null) ans.left.val += tmp.left.val;
        } else {
            if (tmp.left != null) ans.left = new TreeNode(tmp.left.val);
        }
        if (ans.right != null) {
            if (tmp.right != null) ans.right.val += tmp.right.val;
        } else {
            if (tmp.right != null) ans.right = new TreeNode(tmp.right.val);
        }
        ImergeTrees(ans.left, tmp.left);
        ImergeTrees(ans.right, tmp.right);
    }

    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int num : nums) {
            if (num == 0) red++;
            else if (num == 1) white++;
            else blue++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (red != 0) {
                nums[i] = 0;
                red--;
                continue;
            }
            if (white != 0) {
                nums[i] = 1;
                white--;
                continue;
            }
            if (blue != 0) {
                nums[i] = 2;
                blue--;
            }
        }
    }

    public int myAtoi(String s) {
        boolean flag = false;
        int index = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 58 && s.charAt(i) > 47) {
                if ((i - 1) > -1 && s.charAt(i - 1) == '-') flag = true;
                index = i;
                break;
            }
        }
        for (int i = index; i < s.length(); i++) {
            if (s.charAt(i) >= 58 || s.charAt(i) <= 47) break;
            list.add(s.charAt(i));
        }
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += (int) ((list.get(i) - 48) * Math.pow(10, list.size() - i - 1));
        }
        return ans = flag == true ? -1 * ans : ans;
    }

    @Test
    public void testd() {
        System.out.println(myAtoi("words and 987"));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;
        ListNode target = head;
        ListNode tmp = head;
        for (int i = 0; i < n; i++) {
            tmp = tmp.next;
        }
        if (tmp == null) return head.next;
        else tmp = tmp.next;
        while (tmp != null) {
            target = target.next;
            tmp = tmp.next;
        }
        target.next = target.next.next;
        return head;
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

    @Test
    public void reverse() {
        System.out.println(reverse(-2147483648));
    }
    public int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans *= 10;// ans每轮左移一位
            ans += x % 10;// 加上x末位数字
            x /= 10;// x右移一位
        }
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return 0;
        return (int) ans;
    }

    @Test
    public void searchRange() {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 10)));
    }
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        if (nums.length == 0) return ans;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        // 二分查找
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                int start = mid;
                int end = mid;
                while (start > 0 && nums[start - 1] == target) {
                    start--;
                }
                while (end < nums.length - 1 && nums[end + 1] == target) {
                    end++;
                }
                ans[0] = start;
                ans[1] = end;
                return ans;
            }
        }
        return ans;
    }

    @Test
    public void countAndSay(){
        System.out.println(countAndSay(75));
    }
    public String countAndSay(int n) {
        if (n == 1) return "1";
        List<StringBuilder> sequences = new ArrayList<>();
        sequences.add(new StringBuilder("1"));

        for (int i = 1; i < n; i++) {
            StringBuilder current = sequences.get(i - 1);
            StringBuilder next = new StringBuilder();
            int ele = 0;
            int index = 0;
            current.append(' ');

            while (index < current.length()) {
                if (current.charAt(ele) == current.charAt(index)) {
                    index++;
                } else {
                    next.append((char) (index - ele + 48));
                    next.append(current.charAt(ele));
                    ele = index;
                }
            }

            sequences.add(next);
        }

        return sequences.get(n - 1).toString();
    }


}

