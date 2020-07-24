package com.leetcode.datastructure.queue;

/**
 * 703. 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
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
 *
 *
 *
 * 示例:  int k = 3;
 * 　　　 int[ ] = [ 4, 5, 8 ,2 ]
 *   每次进来一个元素就判断第K大个元素是多少？
 *  ① 前最大的K个数进行排序 ---> sorted（每次进来一个新数在里边排序，把最小的淘汰掉）  它的时间复杂度是：N.K LogK
 *  ②使用优先队列的方法：每次把最大的或者最小的元素放在最上边；小顶堆 MinHeap，优先级按从小到大来排列即最上面的元素永远是最小的。
 *  要找的是第K大个元素，要保证堆的size = k；
 *  它的好处是还是做N次比较运算，如果顶上的元素比这个小的话，那么只需要一次就可以找到最大不需KLogK，如果最大一个最大，它进来再调整后时间复杂度是log2K；整体即是N * （1 + log2K），取平均即 Nlog2k
 *
 *
 */
public class LeetCode703KthLargestElementInAStream {

}
