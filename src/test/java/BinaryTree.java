import org.junit.Test;

import java.util.*;

public class BinaryTree {
    // 链表节点
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

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

    // 传入层序遍历数组简单构造二叉树
    public static TreeNode buildTree(Integer... a) {
        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        return arrayToBinaryTree(list);
    }

    // 层序遍历数组构造二叉树
    public static TreeNode arrayToBinaryTree(List<Integer> list) {

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

    @Test
    // 深度遍历测试
    public void DFS() {
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        DFS(buildTree(1, 2, 3, 4, 5, 6, 7), preOrder, inOrder, postOrder);
        System.out.println("前序遍历：" + preOrder);
        System.out.println("中序遍历：" + inOrder);
        System.out.println("后序遍历：" + postOrder);
    }

    // 深度遍历
    public static void DFS(TreeNode node, List<Integer> preOrder, List<Integer> inOrder, List<Integer> postOrder) {
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
        // 从左子树到右子树经过某节点时（包括子树为null的节点）

        DFS(node.right, preOrder, inOrder, postOrder);

        postOrder.add(node.val);

//        preOrder.add(0);
//        inOrder.add(0);
//        postOrder.add(0);
        // 最后一次经过某节点时

//        if(node.left==null&&node.right==null)preOrder.add(-1);// 叶子节点
//        else if(node.left==null||node.right==null)preOrder.add(0);// 只有1个子节点的节点
//        else preOrder.add(999);// 有2个子节点的节点
    }

    @Test
    // 广度遍历测试
    public void BFS() {
        List<Integer> list = new ArrayList<>();

        BFS(buildTree(1, 2, 3, 4, 5, 6, 7), list);

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
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                  // 每次遍历完一层再进入下一层遍历
            TreeNode node = queue.poll(); // 队列出队
            list.add(node.val);           // 保存节点值
            if (node.left != null)
                queue.offer(node.left);   // 左子节点入队
            if (node.right != null)
                queue.offer(node.right);  // 右子节点入
//         }
        }
    }

//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------理解---------------------------------------------------------


    //给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
    //如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        int[] flag = new int[]{1};
        isSameTreeFun(p, q, flag);
        return flag[0] == 1;
    }

    public static void isSameTreeFun(TreeNode p, TreeNode q, int[] flag) {
        // 两树节点都为空，直接返回
        if (p == null && q == null) return;

        // 一树节点为空，另一树不为空，标志置0后返回
        if (p == null || q == null) {
            flag[0] = 0;
            return;
            // 两树节点均不为空，但值不同，标志置0
        } else if (p.val != q.val) {
            flag[0] = 0;
        }

        // 递归调用
        isSameTreeFun(p.left, q.left, flag);
        isSameTreeFun(p.right, q.right, flag);
    }


    //给你一个二叉树的根节点 root ， 检查它是否轴对称。
    public boolean isSymmetric(TreeNode root) {
        int[] flag = new int[]{1};
        TreeNode p = root.left;
        TreeNode q = root.right;
        isSymmetricFun(p, q, flag);
        return flag[0] == 1;
    }

    public static void isSymmetricFun(TreeNode p, TreeNode q, int[] flag) {
        // 两树节点都为空，直接返回
        if (p == null && q == null) return;

        // 一树节点为空，另一树不为空，标志置0后返回
        if (p == null || q == null) {
            flag[0] = 0;
            return;
            // 两树节点均不为空，但值不同，标志置0
        } else if (p.val != q.val) {
            flag[0] = 0;
        }

        // 递归调用，两树调用顺序相反
        isSymmetricFun(p.left, q.right, flag);
        isSymmetricFun(p.right, q.left, flag);
    }



    //给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTFun(nums, 0, nums.length - 1);
    }
    public TreeNode sortedArrayToBSTFun(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTFun(nums, left, mid - 1);
        root.right = sortedArrayToBSTFun(nums, mid + 1, right);
        return root;
    }



    //给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
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



    //给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node.left != null) deque.offer(node.left);
            if (node.right != null) deque.offer(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }



    //给定二叉树的根节点 root ，返回所有左叶子之和。
    public int sumOfLeftLeaves(TreeNode root) {
        int[] ans = new int[]{0};
        sumOfLeftLeavesFun(root, ans);
        return ans[0];
    }
    public void sumOfLeftLeavesFun(TreeNode node, int[] ans) {
        if (node == null) return;
        sumOfLeftLeavesFun(node.left, ans);
        if (node.left != null && node.left.left == null && node.left.right == null) {
            ans[0] += node.left.val;
        }
        sumOfLeftLeavesFun(node.right, ans);
    }



//    给你一个二叉树的根节点 root ，计算并返回 整个树 的坡度 。
//    一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
//    整个树 的坡度就是其所有节点的坡度之和。
    public int findTilt(algorithmtest.TreeNode root) {
        int[] ans = {0};
        findTiltFun(root, ans);
        return ans[0];
    }
    public void findTiltFun(algorithmtest.TreeNode node, int[] tmp) {
        if (node == null) return;
        findTiltFun(node.left, tmp);
        findTiltFun(node.right, tmp);
        int left = node.left == null ? 0 : node.left.val;
        int right = node.right == null ? 0 : node.right.val;
        node.val = node.val + right + left;
        tmp[0] += Math.abs(left - right);
    }


//----------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------未理解----------------------------------------------------------



    //给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
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



    @Test
    public void preorderTraversal() {
        System.out.println(preorderTraversal(buildTree(1, 2, 3, 4, 5, 6, 7)));
    }
    // 前序遍历迭代法
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }



    @Test
    public void inorderTraversal() {
        System.out.println(inorderTraversal(buildTree(1, 2, 3, 4, 5, 6, 7)));
    }
    // 中序遍历迭代法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }



    @Test
    public void postorderTraversal() {
        System.out.println(postorderTraversal(buildTree(1, 2, 3, 4, 5, 6, 7)));
    }
    // 后序遍历迭代法
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }


}
