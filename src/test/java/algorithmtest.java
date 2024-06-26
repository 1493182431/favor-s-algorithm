import org.junit.Test;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    public void countAndSay() {
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

    @Test
    public void longestConsecutive() {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int last = Integer.MIN_VALUE;
        int tmp = 0;
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (last + 1 == nums[i]) {
                last = nums[i];
                tmp++;
            } else if (last + 1 < nums[i]) {
                ans = Math.max(ans, tmp);
                tmp = 0;
                last = nums[i];
            }

        }
        ans = Math.max(ans, tmp);
        return ++ans;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (k == 0) {
                if (nums[i] > heap.peek()) {
                    heap.remove(heap.peek());
                    heap.add(nums[i]);
                }
            } else {
                heap.add(nums[i]);
                k--;
            }
        }

        return heap.peek();
    }

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        int row = grid[0].length;
        int column = grid.length;

        int count;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                count = 0;
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) count++;
                    if (j == 0 || grid[i][j - 1] == 0) count++;
                    if ((i == column - 1) || grid[i + 1][j] == 0) count++;
                    if ((j == row - 1) || grid[i][j + 1] == 0) count++;
                }
                ans += count;
            }
        }
        return ans;
    }

    @Test
    public void permute() {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] used = new int[nums.length];
        backtrackpermute(tmp, nums, used, res);
        return res;
    }

    public void backtrackpermute(List<Integer> tmp, int[] nums, int[] used, List<List<Integer>> res) {
        if (tmp.size() == nums.length) res.add(new ArrayList<>(tmp));
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 0) {
                tmp.add(nums[i]);
                used[i] = 1;
                backtrackpermute(tmp, nums, used, res);
                tmp.remove(tmp.size() - 1);
                used[i] = 0;
            }
        }
    }

    //    排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组。确保每次递归不会与上次递归选到相同的元素。
    @Test
    public void permuteUnique() {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int[] used = new int[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrackpermuteUnique(tmp, nums, used, res);
        return res;
    }

    public void backtrackpermuteUnique(List<Integer> tmp, int[] nums, int[] used, List<List<Integer>> res) {
        if (tmp.size() == nums.length) res.add(new ArrayList<>(tmp));
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1 || (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0)) {
                continue;
            }
            tmp.add(nums[i]);
            used[i] = 1;
            backtrackpermuteUnique(tmp, nums, used, res);
            tmp.remove(tmp.size() - 1);
            used[i] = 0;
        }
    }


    //    组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 begin 变量。
//    每一轮for循环的选择应当跳过上一轮for循环已经选完的节点，避免产生重复的子集，即每一轮for循环以index=begin开始。
    @Test
    public void combinationSum() {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum(nums, 8));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new LinkedList<Integer>(), res);
        return res;
    }

    public void dfs(int[] candidates, int idx, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    @Test
    public void combinationSum2() {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(nums, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        int[] used = new int[candidates.length];
        dfscombinationSum2(used, candidates, 0, target, new LinkedList<Integer>(), res);
        return res;
    }

    public void dfscombinationSum2(int[] used, int[] candidates, int idx, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = idx; i < candidates.length; i++) {
            if (!set.contains(candidates[i]) && used[i] == 0) {
                set.add(candidates[i]);
                used[i] = 1;
                path.add(candidates[i]);
                dfscombinationSum2(used, candidates, i, target - candidates[i], path, res);
                path.removeLast();
                used[i] = 0;
            }

        }
    }


//    /* 回溯算法框架 */
//    排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组。确保每次递归不会与上次递归选到相同的元素。
//    组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 begin 变量。
//    每一轮for循环的选择应当跳过上一轮for循环已经选完的节点，避免产生重复的子集，即每一轮for循环以index=begin开始。
//    重复问题，若要去除每一轮for循环中的重复元素，可使用Set<Integer>set=new HashSet<>()，或者提前对待循环节点排序
//
//
//    /**
//     *
//     * @param state 临时变量用于存储尝试和回退
//     * @param choices 需要遍历的数据结构
//     * @param res 解答结果
//     */
//    void backtrack(State state, List<Choice> choices, List<State> res) {
//        // 判断是否为解
//        if (isSolution(state)) {
//            // 记录解，将当前解答加入到res中
//            recordSolution(state, res);
//            // 只需记录第一个解则加return，若需全部遍历找到所有解则需加return
//            // return;
//        }
//        // 遍历所有选择
//        for (Choice choice : choices) {
//            // 剪枝：判断选择是否合法
//            if (isValid(state, choice)) {
//                // 尝试：做出选择，更新状态
//                makeChoice(state, choice);
//                // 第一次经过某节点
//                firstTravel();
//
//                // 递归调用
//                backtrack(state, choices, res);
//
//                // 回退：撤销选择，恢复到之前的状态
//                undoChoice(state, choice);
//                // 最后一次经过某节点
//                lastTravel();
//            }
//        }
//    }

    @Test
    public void test1026() {
        TreeNode root = buildTree(1, 7, 2, 4, 5, 3, 7, 8, 9, 7, 10, 11, 7);
        List<Integer> state = new ArrayList<>();
        List<TreeNode> choices = new ArrayList<>();
        choices.add(root);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(state, choices, res);
        System.out.println(res);
    }

    /* 回溯算法：find all the path of Integer 7 in a binary tree*/
    void backtrack(List<Integer> state, List<TreeNode> choices, List<List<Integer>> res) {
        if (!state.isEmpty() && state.get(state.size() - 1) == 7) {
            res.add(new ArrayList<>(state));
        }
        for (TreeNode choice : choices) {
            if (choice != null && choice.val != 3) {
                state.add(choice.val);
                backtrack(state, Arrays.asList(choice.left, choice.right), res);
                state.remove(state.size() - 1);
            }
        }
    }

    @Test
    public void combine() {
        System.out.println(combine(4, 2));
    }

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTrackCombine(new ArrayList<>(), n, k, 1);
        return ans;
    }

    public void backTrackCombine(List<Integer> tmp, int n, int k, int start) {
        if (k <= 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            tmp.add(i);
            backTrackCombine(tmp, n, k - 1, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void combinationSum3() {
        System.out.println(combinationSum3(3, 7));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        bccombinationSum3(ans, new ArrayList<>(), k, n, new int[9], 1);
        return ans;
    }

    public void bccombinationSum3(List<List<Integer>> ans, List<Integer> tmp, int k, int n, int[] used, int start) {
        if (k == 0 && n == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if (k < 0 || n < 0) return;
        for (int i = start; i < 10; i++) {
            if (used[i - 1] == 0) {
                used[i - 1] = 1;
                tmp.add(i);
                bccombinationSum3(ans, tmp, k - 1, n - i, used, i);
                tmp.remove(tmp.size() - 1);
                used[i - 1] = 0;
            }
        }
    }

    @Test
    public void letterCombinations() {
        System.out.println(letterCombinations("234567"));
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        btletterCombinations(ans, new StringBuilder(), digits, map, 0);
        return ans;
    }

    public void btletterCombinations(List<String> ans, StringBuilder tmp, String digits, Map<Character, String> map, int count) {
        if (tmp.length() == digits.length()) {
            ans.add(new String(tmp));
            return;
        }
        for (int i = 0; i < map.get(digits.charAt(count)).length(); i++) {
            tmp.append(map.get(digits.charAt(count)).charAt(i));
            btletterCombinations(ans, tmp, digits, map, count + 1);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }

    @Test
    public void subsets() {
        System.out.println(subsets(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrackSubsets(new ArrayList<>(), ans, nums, nums[0], 0);
        return ans;
    }

    public void backtrackSubsets(List<Integer> tmp, List<List<Integer>> ans, int[] nums, int next, int count) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                tmp.add(next);
            }
            backtrackSubsets(tmp, ans, nums, count < nums.length - 1 ? nums[count + 1] : 0, count + 1);
            if (i == 0) tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void subsetsWithDup() {
        System.out.println(subsetsWithDup(new int[]{1, 1}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        btsubsetsWithDup(new ArrayList<>(), ans, nums, nums[0], 0, 0);
        return ans;
    }

    public void btsubsetsWithDup(List<Integer> tmp, List<List<Integer>> ans, int[] nums, int next, int count, int start) {
        if (count == nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = count != 0 && nums[count] == nums[count - 1] ? start : 0; i < 2; i++) {
            if (i == 0) tmp.add(next);
            btsubsetsWithDup(tmp, ans, nums, count < nums.length - 1 ? nums[count + 1] : 0, count + 1, i);
            if (i == 0) tmp.remove(tmp.size() - 1);
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int current = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[current - 2] != nums[i]) {
                nums[current] = nums[i];
                current++;
            }
        }
        return current;
    }

    public ListNode sortList(ListNode head) {
        ListNode node = head;
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        Collections.sort(list);
        node = head;
        int i = 0;
        while (node != null) {
            node.val = list.get(i);
            node = node.next;
            i++;
        }
        return head;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int r1 = 0, r2 = m - 1; // 定义上下边界
        int c1 = 0, c2 = n - 1; // 定义左右边界
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    @Test
    public void sdkgnjk() {
        int[][] matrix = new int[][]{};
        System.out.println(spiralOrder(matrix));
    }


    public void qdeleteNode(ListNode node) {
        ListNode first = node;
        ListNode second = node.next;
        while (second.next != null) {
            first.val = second.val;
            first = second;
            second = second.next;
        }
        first.val = second.val;
        first.next = null;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode first = head;
        ListNode second = head.next;
        ListNode tmp = head.next;
        if (first.next == null || second.next == null) return head;
        while (true) {
            if (second.next == null) break;
            first.next = second.next;
            first = first.next;
            if (first.next == null) break;
            second.next = first.next;
            second = second.next;
        }
        second.next = null;
        first.next = tmp;
        return head;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int j = 0;
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            j = i + 1;
            while (j != temperatures.length) {
                if (i != 0 && temperatures[i - 1] == temperatures[i]) {
                    ans[i] = ans[i - 1] == 0 ? 0 : ans[i - 1] - 1;
                    break;
                }
                if (temperatures[i] < temperatures[j]) {
                    ans[i] = j - i;
                    break;
                }
                j++;
            }
            if (j == temperatures.length) ans[i] = 0;
        }
        return ans;
    }

    public int trailingZeroes(int n) {
        int ans = 0;
        int tmp = 1;
        while (tmp * 5 <= n) {
            int i = tmp;
            while (i % 5 == 0) {
                ans++;
                i /= 5;
            }
            ans++;
            tmp++;
        }
        return ans;
    }

    @Test
    public void srds() {
        System.out.println(trailingZeroes(668));
    }


    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i += (numRows - 1) * 2) {
            ans.append(s.charAt(i));
        }
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = 0; j * (numRows - 1) * 2 + i < s.length(); j++) {
                ans.append(s.charAt(j * (numRows - 1) * 2 + i));
                int r = (j + 1) * (numRows - 1) * 2 - i;
                if (r < s.length()) {
                    ans.append(s.charAt(r));
                }
            }
        }
        for (int i = numRows - 1; i < s.length(); i += (numRows - 1) * 2) {
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }

    @Test
    public void seisi() {
        System.out.println(convert("PAYPALISHIRINGyuthdhrdyrsdyrsdhfxc", 3));
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        ListNode last = null;
        head = second;
        while (second.next != null) {
            if (last != null) {
                last.next = second;
            }
            first.next = second.next;
            second.next = first;
            last = first;
            first = first.next;
            if (first.next == null) {
                second = null;
                break;
            }
            second = first.next;
        }
        if (second == null) {
            last.next = first;
        } else {
            if (last != null) last.next = second;
            first.next = second.next;
            second.next = first;
        }
        return head;
    }


    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int r1 = 0, r2 = n - 1; // 定义上下边界
        int c1 = 0, c2 = n - 1; // 定义左右边界
        int count = 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                ans[r1][c] = count;
                count++;
            }
            for (int r = r1 + 1; r <= r2; r++) {
                ans[r][c2] = count;
                count++;
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    ans[r2][c] = count;
                    count++;
                }
                for (int r = r2; r > r1; r--) {
                    ans[r][c1] = count;
                    count++;
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public int getMinimumDifference(TreeNode root) {
        int[] ans = {9999999, 9999999};
        getMinimumDifferenceFun(root, ans);
        return ans[1];
    }

    public void getMinimumDifferenceFun(TreeNode node, int[] minValue) {
        if (node == null) return;
        getMinimumDifferenceFun(node.left, minValue);
        minValue[1] = Math.min(minValue[1], Math.abs(node.val - minValue[0]));
        minValue[0] = node.val;
        getMinimumDifferenceFun(node.right, minValue);
    }

    public int findTilt(TreeNode root) {
        int[] ans = {0};
        findTiltFun(root, ans);
        return ans[0];
    }

    public void findTiltFun(TreeNode node, int[] tmp) {
        if (node == null) return;
        findTiltFun(node.left, tmp);
        findTiltFun(node.right, tmp);
        int left = node.left == null ? 0 : node.left.val;
        int right = node.right == null ? 0 : node.right.val;
        node.val = node.val + right + left;
        tmp[0] += Math.abs(left - right);
    }


    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        avgFun(root, ans);
        return ans;
    }

    public void avgFun(TreeNode root, List<Double> ans) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return;
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (ans.size() == count) ans.add((double) node.val);
                else ans.set(count, ans.get(count) + node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.set(count, ans.get(count) / len);
            count++;

        }

    }

    @Test
    public void daegkhk() {
        recoverTree(buildTree(1, 3, null, null, 2));
    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        List<TreeNode> last = new ArrayList<>(1);
        recoverTreeIn(root, nodes, last);
        if (nodes.size() == 2) {
            int tmp = nodes.get(0).val;
            nodes.get(0).val = nodes.get(1).val;
            nodes.get(1).val = tmp;
        }
        if (nodes.size() == 4) {
            int tmp = nodes.get(0).val;
            nodes.get(0).val = nodes.get(3).val;
            nodes.get(3).val = tmp;
        }
    }

    public void recoverTreeIn(TreeNode node, List<TreeNode> nodes, List<TreeNode> last) {
        if (node == null) return;
        recoverTreeIn(node.left, nodes, last);
        if (!last.isEmpty()) {
            if (last.get(0).val > node.val) {
                nodes.add(last.get(0));
                nodes.add(node);
            }
            last.set(0, node);
        } else last.add(node);
        recoverTreeIn(node.right, nodes, last);
    }


    public BinaryTree.TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTFun(nums, 0, nums.length - 1);
    }

    public BinaryTree.TreeNode sortedArrayToBSTFun(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        BinaryTree.TreeNode root = new BinaryTree.TreeNode(nums[mid]);
        root.left = sortedArrayToBSTFun(nums, left, mid - 1);
        root.right = sortedArrayToBSTFun(nums, mid + 1, right);
        return root;
    }


    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        ListNode mid = slow;
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;
        connectFun(root);
        return root;
    }

    public void connectFun(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                node.next = i == len - 1 ? null : queue.peek();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        rightSideViewFun(root, ans);
        return ans;
    }

    public void rightSideViewFun(TreeNode root, List<Integer> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (i == len - 1) list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
    }


    @Test
    public void wihfn() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        char l, r;
        while (left <= right) {
            if (s.charAt(left) >= 65 && s.charAt(left) <= 90) l = (char) (s.charAt(left) + 32);
            else if ((s.charAt(left) >= 97 && s.charAt(left) <= 122) || (s.charAt(left) >= 48 && s.charAt(left) <= 57))
                l = s.charAt(left);
            else l = ' ';
            if (s.charAt(right) >= 65 && s.charAt(right) <= 90) r = (char) (s.charAt(right) + 32);
            else if ((s.charAt(right) >= 97 && s.charAt(right) <= 122) || (s.charAt(right) >= 48 && s.charAt(right) <= 57))
                r = s.charAt(right);
            else r = ' ';

            if (l != ' ' && r == l) {
                left++;
                right--;
            } else if (l == ' ') left++;
            else if (r == ' ') right--;
            else return false;
        }
        return true;
    }

    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans += (int) (((int) columnTitle.charAt(columnTitle.length() - i - 1) - 64) * Math.pow(26, i));
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (j < s.length() && set.add(s.charAt(j))) {
                j++;
            }
            ans = Math.max(ans, set.size());
            set.clear();
        }
        return ans;
    }

    public boolean isSubsequence(String s, String t) {
        int first = 0, second = 0;
        while (second < t.length()) {
            if (first == s.length()) break;
            if (s.charAt(first) == t.charAt(second)) {
                first++;
                second++;
            } else second++;
        }

        return first == t.length();
    }

    public int longestPalindrome(String s) {
        int[] count = new int[128];
        int length = s.length();
        int res = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            count[c]++;
        }

        for (int i : count) {
            res += i / 2 * 2;
            if (i % 2 == 1 && res % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    public int countSegments(String s) {
        int ans = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && tmp == 0) {
                tmp++;
                ans++;
            }
            if (s.charAt(i) == ' ') tmp = 0;
        }
        return ans;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = nums1.clone();
        int i = 0, j = 0, index = 0;
        while (i < m || j < n) {
            if (i == m) {
                nums1[index] = nums2[j];
                j++;
                index++;
                continue;
            }
            if (j == n) {
                nums1[index] = tmp[i];
                i++;
                index++;
                continue;
            }
            if (tmp[i] <= nums2[j]) {
                nums1[index] = tmp[i];
                i++;
                index++;
            } else {
                nums1[index] = nums2[j];
                j++;
                index++;
            }
        }
    }

    public int majorityElement(int[] nums) {
        int[] tmp = new int[nums.length];
        for (int num : nums) {
            tmp[num]++;
            if (tmp[num] == nums.length / 2) return num;
        }
        return 0;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        for (int num : nums) {
            if (tmp.isEmpty()) {
                tmp.add(num);
                continue;
            }
            if (num == tmp.get(tmp.size() - 1) + 1) tmp.add(num);
            else {
                if (tmp.size() == 1) ans.add(String.valueOf(tmp.get(0)));
                else ans.add(tmp.get(0) + "->" + tmp.get(tmp.size() - 1));
                tmp = new ArrayList<>();
                tmp.add(num);
            }
        }
        if (!tmp.isEmpty()) {
            if (tmp.size() == 1) ans.add(String.valueOf(tmp.get(0)));
            else ans.add(tmp.get(0) + "->" + tmp.get(tmp.size() - 1));
        }
        return ans;
    }

    class NumArray {
        private int[] tmp;

        public NumArray(int[] nums) {
            tmp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (i != 0) tmp[i] = tmp[i - 1] + nums[i];
                else tmp[i] = nums[i];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) return tmp[right];
            return tmp[right] - tmp[left - 1];
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int[] tmp = new int[1001];
        for (int i : nums1) {
            tmp[i] = 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i : nums2) {
            if (tmp[i] == 1) {
                tmp[i] = 0;
                ans.add(i);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] tmp = new int[1001];
        for (int i : nums1) {
            tmp[i]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i : nums2) {
            if (tmp[i] > 0) {
                tmp[i]--;
                ans.add(i);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        int i = 0, j = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                ans++;
                i++;
                j++;
            } else j++;
        }
        return ans;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0, tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans = Math.max(ans, tmp);
                tmp = 0;
            } else tmp++;
        }
        return Math.max(ans, tmp);
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int left = timeSeries[0], right = timeSeries[0] + duration - 1, ans = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] >= left && timeSeries[i] <= right) {
                right = timeSeries[i] + duration - 1;
            } else {
                ans += (right - left + 1);
                left = timeSeries[i];
                right = timeSeries[i] + duration - 1;
            }
        }
        return ans;
    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public int arrayPairSum(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }

    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        return Math.min(set.size(), candyType.length / 2);
    }

    public int maxCount(int m, int n, int[][] ops) {
        for (int i = 0; i < ops.length; i++) {
            m = Math.min(m, ops[i][0]);
            n = Math.min(n, ops[i][1]);
        }
        return m * n;
    }

    @Test
    public void fwfw() {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    public String simplifyPath(String path) {
        List<String> tmp = new LinkedList<>(List.of(path.split("/")));
        StringBuilder ans = new StringBuilder();
        int last = 0;
        for (int i = 0; i < tmp.size(); i++) {
            if (ans.isEmpty() && tmp.get(i).equals("..")) {
                ans.append('/');
                continue;
            }
            if (tmp.get(i).equals("..")) {
                ans.delete(ans.lastIndexOf("/"), ans.length());
            } else if (!tmp.get(i).equals(".") && !tmp.get(i).equals("/") && !tmp.get(i).isEmpty()) {
                if (!ans.isEmpty() && ans.charAt(ans.length() - 1) == '/') ans.append(tmp.get(i));
                else {
                    ans.append('/');
                    ans.append(tmp.get(i));
                }
            }
        }
        return ans.isEmpty() ? "/" : ans.toString();
    }

    @Test
    public void wkhk() {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    public double findMaxAverage(int[] nums, int k) {
        if (nums.length == 1) return nums[0];
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double tmp = sum;
        for (int i = k; i < nums.length; i++) {
            tmp -= nums[i - k];
            tmp += nums[i];
            sum = nums[i] > nums[i - k] ? tmp : sum;
        }
        return sum / k;
    }

    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int[] tmp = new int[10001];
        for (int num : nums) {
            if (tmp[num] == 1) ans[0] = num;
            tmp[num] = 1;
        }
        for (int i = 1; i <= nums.length; i++) {
            if (tmp[i] == 0) ans[1] = nums[i];
        }
        return ans;
    }

    public int findLengthOfLCIS(int[] nums) {
        int ans = 1, tmp = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) tmp++;
            else tmp = 1;
            ans = Math.max(ans, tmp);
        }
        return ans;
    }

    @Test
    public void eljkek() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] array = strs[i].toCharArray();
            Arrays.sort(array);
            String tmp = new String(array);
            if (map.containsKey(tmp)) map.get(tmp).add(strs[i]);
            else {
                map.put(tmp, new ArrayList<>());
                map.get(tmp).add(strs[i]);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> value : map.values()) {
            ans.add(value);
        }
        return ans;
    }

    public TreeNode convertBST(TreeNode root) {
        int[] tmp = new int[1];
        inorderConvertBST(root, tmp);
        return root;
    }

    public void inorderConvertBST(TreeNode node, int[] tmp) {
        if (node == null) return;
        inorderConvertBST(node.right, tmp);
        tmp[0] += node.val;
        node.val = tmp[0];
        inorderConvertBST(node.left, tmp);
    }

    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        findTargetOrder(root,set,k);
        return set.contains(10001);
    }
    public void findTargetOrder(TreeNode node, Set<Integer> set, int k) {
        if (node == null||set.contains(10001)) return;
        if (set.contains(k - node.val)) set.add(10001);
        else set.add(node.val);
        findTargetOrder(node.left, set, k);
        findTargetOrder(node.right, set, k);
    }

    public boolean isHappy(int n) {
        Set<Integer>set=new HashSet<>();
        int tmp=0;
        while (n!=1){
            while (n!=0){
                tmp+=((n%10)*(n%10));
                n/=10;
            }
            if (set.contains(tmp))return false;
            set.add(tmp);
            n=tmp;
            tmp=0;
        }
        return true;
    }

    public int[] countBits(int n) {
        int[]bits=new int[n+1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

    public int fib(int n) {
        int ans=0,a=0,b=1;
        if(n==0||n==1)return n;
        for (int i = 2; i <= n; i++) {
            ans=a+b;
            a=b;
            b=ans;
        }
        return ans;
    }

    public int minCostClimbingStairs(int[] cost) {
        int a=cost[0],b=cost[1],ans=0,tmp=0;
        if(cost.length==2)return Math.min(a,b);
        for (int i = 2; i < cost.length; i++) {
            tmp=Math.min(a,b);
            ans+=tmp;
            a=b;
            b=cost[i];
        }
        return ans;
    }



}

