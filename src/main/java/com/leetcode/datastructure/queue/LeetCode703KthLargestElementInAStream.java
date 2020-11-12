package com.leetcode.datastructure.queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 703. 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 * <p>
 * <p>
 * <p>
 * 示例:  int k = 3;
 * 　　　 int[ ] = [ 4, 5, 8 ,2 ]
 * 每次进来一个元素就判断第K大个元素是多少？
 * ① 前最大的K个数进行排序 ---> sorted（每次进来一个新数在里边排序，把最小的淘汰掉）  它的时间复杂度是：N.K LogK
 * ②使用优先队列的方法：每次把最大的或者最小的元素放在最上边；小顶堆 MinHeap，优先级按从小到大来排列即最上面的元素永远是最小的。
 * 要找的是第K大个元素，要保证堆的size = k；
 * 它的好处是还是做N次比较运算，如果顶上的元素比这个小的话，那么只需要一次就可以找到最大不需KLogK，如果最大一个最大，它进来再调整后时间复杂度是log2K；整体即是N * （1 + log2K），取平均即 Nlog2k
 */
public class LeetCode703KthLargestElementInAStream {

    public static void main(String[] args) {
        int k = 3;
        int[] nums = {4, 5, 8, 2};
        LeetCode703KthLargestElementInAStream kth = new LeetCode703KthLargestElementInAStream(k, nums);
        System.out.println(kth.add(3));  //4  priorityQueue {4,5,8}
        System.out.println(kth.add(5));  //5  priorityQueue {5,8,5} 4与5做比较,4 < 5 将4移除,5添加到队尾
        System.out.println(kth.add(10)); //5  priorityQueue {5,8,10} 5与10做比较,5 < 10将5移除,10添加到队尾
        System.out.println(kth.add(9));  //8  priorityQueue {8,10,9} 5与9做比较,5 < 9 将5移除,9添加到队尾
        System.out.println(kth.add(4));  //8  priorityQueue {8,10,9} 8与4做比较,8 > 4 直接返回队首元素8
    }

    /* 方法一: Java 的 PriorityQueue 优先级队列
    * 1 是线程不安全的队列
      2 存储使用数组实现
      3 利用比较器做优先级比较

        1 最小堆的特性就是最小的值在最上面，每次取O(1)，插入O(log n)
        2 初始化的时候，注意如何添加元素，并给队列一个合适大小的初值
        3 每次添加元素，能添加到队列的有两种情况，一种未到k个，另一种比堆顶大
    * */
    Queue<Integer> priorityQueue;
    int k;
    public LeetCode703KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        priorityQueue = new PriorityQueue<>(k);
        for (int val : nums)
            add(val);
    }
    private int add(int val) {
        if (priorityQueue.size() < k)
            priorityQueue.add(val); //添加到队列
        else if (priorityQueue.peek() < val) {
            priorityQueue.poll(); // 移除
            priorityQueue.add(val);
        }
        Integer peek = priorityQueue.peek();
        return peek;
    }
    //方法二 小顶堆  https://leetcode.com/problems/kth-largest-element-in-a-stream/discuss/152588/Explanation-of-MinHeap-solution-(NO-CODE)
/*    int kArr[];
    public LeetCode703KthLargestElementInAStream(int k, int[] nums) {
        kArr = new int[k];
        Arrays.fill(kArr, Integer.MIN_VALUE);
        for (int num : nums) {
            add(num);
        }
    }
    private int add(int val) {
        if (val > kArr[0]) {
            kArr[0] = val;
            heapify();
        }
        return kArr[0];
    }
    //构建小顶堆 MinHeap that contains only k largest elements.
    private void heapify() {
        int currRoot;
        int nextRoot = 0;
        do {
            currRoot = nextRoot;
            if (2 * currRoot + 1 < kArr.length && kArr[2 * currRoot + 1] < kArr[nextRoot])
                nextRoot = 2 * currRoot + 1;
            if (2 * currRoot + 2 < kArr.length && kArr[2 * currRoot + 2] < kArr[nextRoot])
                nextRoot = 2 * currRoot + 2;

            int temp = kArr[nextRoot];
            kArr[nextRoot] = kArr[currRoot];
            kArr[currRoot] = temp;

        } while (currRoot != nextRoot);
    }
    */
}
