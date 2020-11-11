package com.leetcode.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值     Sliding Window Maximum
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 使用双端队列的处理
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * <p>
 * 滑动窗口最大值 Array Sliding Window
 * 示例:
 * 　　　　输入: nums = [1,3,-1,-3,5,3,6]
 * 　　　　　　  k = 3(固定大小的窗口)
 * 　　　　　　　输出: [3,3,5,5,6]
 * ①优先队列--大顶堆
 * 　　a. 维护Heap,删除滑走的,添加进来的 logK
 * 　　b. Max --> Top O(1)
 * 　　　　整体时间复杂度: NLogK
 * ②使用Queue ==> deque
 * 　　a. 入队列
 * 　　b. 维护
 * 　　　　时间复杂度 O(N.1)
 */
public class LeetCode239SlidingWindowMax {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        for (int i : maxSlidingWindow(nums, k)) {
            System.out.print(" " + i);
        }
    }

    /**
     * 借助双端队列 Deque
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> queue = new ArrayDeque<>(); //ArrayDeque: Constructs an empty array deque with an initial nacity sufficient to hold 16 elements.
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!queue.isEmpty() && queue.peek() < i - k + 1) { //peek 即peekFirst
                queue.poll(); // poll 即pollFirst
            }
            // remove smaller numbers in k range as they are useless
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // queue contains index, result contains content
            queue.offer(i); //offer即offerLast
            if (i >= k - 1) {
                result[ri++] = nums[queue.peek()];
            }
        }
        return result;

    }

    /**
     * 暴力法 Brute-Force
     * 时间复杂度为 O(nk) n为数组元素的个数, k为滑动长度
     * 空间复杂度为 O(N - k + 1), 用于输出数组。
     * 遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1个滑动窗口, 每个有k 个元素
     * https://leetcode.com/problems/sliding-window-maximum/discuss/458121/Java-All-Solutions-(B-F-PQ-Deque-DP)-with-Explanation-and-Complexity-Analysis
     * @param nums 数组
     * @param k 窗口内k个元素
     * @return int[]
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        //遍历每个滑动窗口,一共 (n - k + 1)个窗口
        for (int i = 0; i < n - k + 1; i++) {
            //每个窗口内一共k个元素, 遍历一遍就添加一个最大值.
            int maxValue = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++)
                maxValue = Math.max(maxValue, nums[j]);
            result[i] = maxValue;
        }
        return result;
    }
}

