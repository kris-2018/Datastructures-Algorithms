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
        int[] nums2 = {7, 2, 4};
        int k = 3;
        int k2 = 2;
        for (int i : maxSlidingWindow(nums, k)) {
            System.out.print(" " + i);
        }
    }

    /**
     * 借助双端队列 Deque
     * 在双端队列中, 要始终保证队头是队列中最大的值。即在添加一个值之前, 比它小的都要被移除掉 然后再添加这个值。
     * 时间复杂度O(n), 空间复杂度O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //边界条件的判断
        if (nums == null || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        //双端队列就是两边都可以插入和删除数据的队列; store index
        Deque<Integer> deque = new ArrayDeque<>(); //ArrayDeque: Constructs an empty array deque with an initial nacity sufficient to hold 16 elements.
        for (int i = 0; i < nums.length; i++) {
            //如果队列中队头元素peek() 和当前元素位置相差i-k，相当于队头元素要出窗口了，就把队头元素给移除，注意队列中存储的是元素的下标
            // remove numbers out of range k, queue.peek()为窗口的最大值的下标
            while (!deque.isEmpty() && deque.peek() <= i - k) { //peek 即peekFirst
                deque.poll(); // poll 即pollFirst
            }
            //在添加一个值之前,前面比他小的都要被移除掉(队尾值 和当前值做比较),并且还要保证窗口中队列头部元素永远是队列中最大的
            // remove smaller numbers in k range as they are useless
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            //当前元素的下标加入到队列的尾部  queue contains index, result contains content
            deque.offer(i); //offer即offerLast
            //当窗口的长度大于等于k个的时候才开始计算（注意这里的i是从0开始的）,即第一个窗口中最大值添加到数组 result中
            if (i + 1 >= k) {
                result[index++] = nums[deque.peek()]; // 队头元素是队列中最大的，把队列头部的元素加入到数组中
            }
        }
        return result;

    }

    /**
     * 双端队列, 两端扫描
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        //从左往右窗口的第一个最大值默认是数组第一个值
        maxLeft[0] = nums[0];
        maxRight[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = (i % k == 0) ? nums[i] : Math.max(maxLeft[i - 1], nums[i]);
            int j = len - i - 1;
            maxRight[j] = ((j + 1) % k == 0) ? nums[j] : Math.max(maxRight[j + 1], nums[j]);
        }
        //返回的结果值
        int[] res = new int[len - k + 1];
        for (int i = 0, j = 0; i < res.length; i++) {
            //取每个窗口内从左往右扫描的最后一个值和从右往左扫描的最后
            //一个值(如果从左边数是第一个)的最大值
            res[j++] = Math.max(maxRight[i], maxLeft[i + k - 1]);
        }
        return res;
    }

    /*动态规划*/

    /**
     * 暴力法 Brute-Force
     * 时间复杂度为 O(nk) n为数组元素的个数, k为滑动长度
     * 空间复杂度为 O(N - k + 1), 用于输出数组。
     * 遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1个滑动窗口, 每个有k 个元素
     * https://leetcode.com/problems/sliding-window-maximum/discuss/458121/Java-All-Solutions-(B-F-PQ-Deque-DP)-with-Explanation-and-Complexity-Analysis
     *
     * @param nums 数组
     * @param k    窗口内k个元素
     * @return int[]
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {
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

