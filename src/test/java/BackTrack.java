import org.junit.Test;

import java.util.*;

public class BackTrack {
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
//     * @param choices 需要遍历的数据结构,每轮遍历可传入不一样的集合
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
    public void backtrackTest() {
        int[] nums = new int[]{1, 2, 2};
        System.out.println("全排列：");
        System.out.println(permutation(nums));

        System.out.println("使用used[]去除上一轮已经选择过的：");
        System.out.println(used(nums));

        System.out.println("提前排序去除每一轮的重复选择：");
        System.out.println(deduplication(nums));

        System.out.println("使用start去除重复子集：");
        System.out.println(start(nums));

        System.out.println("");
        System.out.println(startAdd(nums));
    }


    // 全排列
    public List<List<Integer>> permutation(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackpermutation(new ArrayList<>(), nums, res);
        return res;
    }

    public void backtrackpermutation(List<Integer> tmp, int[] nums, List<List<Integer>> res) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrackpermutation(tmp, nums, res);
            tmp.remove(tmp.size() - 1);
        }
    }

    // 使用used[]数组去除上一轮已经选择过的
    public List<List<Integer>> used(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackUsed(new ArrayList<>(), nums, new int[nums.length], res);
        return res;
    }

    public void backtrackUsed(List<Integer> tmp, int[] nums, int[] used, List<List<Integer>> res) {
        if (tmp.size() == nums.length) res.add(new ArrayList<>(tmp));
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 0) {
                tmp.add(nums[i]);
                used[i] = 1;
                backtrackUsed(tmp, nums, used, res);
                tmp.remove(tmp.size() - 1);
                used[i] = 0;
            }
        }
    }

    // 提前排序去除每一轮的重复选择
    public List<List<Integer>> deduplication(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrackDeduplication(new ArrayList<>(), nums, new int[nums.length], res);
        return res;
    }

    public void backtrackDeduplication(List<Integer> tmp, int[] nums, int[] used, List<List<Integer>> res) {
        if (tmp.size() == nums.length) res.add(new ArrayList<>(tmp));
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1 || (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0)) {
                continue;
            }
            tmp.add(nums[i]);
            used[i] = 1;
            backtrackDeduplication(tmp, nums, used, res);
            tmp.remove(tmp.size() - 1);
            used[i] = 0;
        }
    }

    // 使用start去除重复子集
    public List<List<Integer>> start(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackStart(new ArrayList<>(), nums, 0, res);
        return res;
    }

    public void backtrackStart(List<Integer> tmp, int[] nums, int start, List<List<Integer>> res) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrackStart(tmp, nums, i, res);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> startAdd(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackStartAdd(new ArrayList<>(), nums, 0, res);
        return res;
    }

    public void backtrackStartAdd(List<Integer> tmp, int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            res.add(new ArrayList<>(tmp));

        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrackStartAdd(tmp, nums, i + 1, res);
            tmp.remove(tmp.size() - 1);
        }
    }


    @Test
    public void letterCombinations() {
        System.out.println(letterCombinations("23"));
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
        // 每轮遍历传入不一样的集合,第一轮"abc"，第二轮"def"
        for (int i = 0; i < map.get(digits.charAt(count)).length(); i++) {
            tmp.append(map.get(digits.charAt(count)).charAt(i));
            btletterCombinations(ans, tmp, digits, map, count + 1);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }


//    给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//    解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//    示例 1：
//    输入：nums = [1,2,2]
//    输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//
//    示例 2：
//    输入：nums = [0]
//    输出：[[],[0]]
    @Test
    public void subsetsWithDup() {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
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
        //
        for (int i = count != 0 && nums[count] == nums[count - 1] ? start : 0; i < 2; i++) {
            if (i == 0) tmp.add(next);
            // 每轮遍历传入不一样的集合,第一轮[1,空]，第二轮[2,空],第三轮[2,空]
            btsubsetsWithDup(tmp, ans, nums, count < nums.length - 1 ? nums[count + 1] : 0, count + 1, i);
            if (i == 0) tmp.remove(tmp.size() - 1);
        }
    }


}
