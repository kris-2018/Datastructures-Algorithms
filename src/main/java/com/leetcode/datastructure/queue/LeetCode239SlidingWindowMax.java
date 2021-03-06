package com.leetcode.datastructure.queue;

import java.util.ArrayDeque;

/**
 * 239. 滑动窗口最大值     Sliding Window Maximum
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 使用双端队列的处理
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 *
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
 *
 */
public class LeetCode239SlidingWindowMax {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        for (int i : maxSlidingWindow(nums, k)) {
            System.out.print(" " + i);
        }

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        int ri = 0;
        // store index
        ArrayDeque<Integer> queue = new ArrayDeque<>(); //ArrayDeque: Constructs an empty array deque with an initial nacity sufficient to hold 16 elements.
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // q contains index... r contains content
            queue.offer(i);
            if (i >= k - 1) {
                result[ri++] = nums[queue.peek()];
            }
        }
        return result;

    }

    /**
     * https://leetcode.com/problems/sliding-window-maximum/discuss/458121/Java-All-Solutions-(B-F-PQ-Deque-DP)-with-Explanation-and-Complexity-Analysis
     *
     * 时间复杂度为 O(nk)
     * 空间复杂度为 O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        int numOfWindow = nums.length - k + 1;
        int[] result = new int[numOfWindow + 1];
        for (int start = 0; start < numOfWindow; start++) {
            int end = start + k - 1;
            int maxVal = nums[start];
            for (int i = start + 1; i <= end; i++) {
                if (nums[i] > maxVal) {
                    maxVal = nums[i];
                }
            }
            result[start] = maxVal;
        }
        return result;

    }



}
