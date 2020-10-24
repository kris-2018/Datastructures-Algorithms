package com.leetcode.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 */
public class LeetCode142LinkedListCycleII {

    public static void main(String[] args) {
        /** 插入数据 */
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        node1.setNext(node2);
        node2.setNext(node4);
        node4.setNext(node1);

        System.out.println("\n链表带环的节点为: ");
        System.out.println((detectCycle(node1)));
    }

    /**
     * 方法一
     * 双指针解法
     * 时间复杂度 O(n)  空间复杂度 O(1)
     * 假设从头结点到环形入口节点 的节点数为x, 环形入口节点到 fast指针与slow指针相遇节点 节点数为y, 从相遇节点 再到环形入口节点节点数为 z;
     * 相遇时：
     * slow指针走过的节点数为: x + y
     * fast指针走过的节点数： x + y + n (y + z)，n为fast指针在环内走了n圈才遇到slow指针， （y+z）为 一圈内节点的个数
     * 因为fast指针是一步走两个节点，slow指针一步走一个节点， 所以 fast指针走过的节点数 = slow指针走过的节点数 * 2, 即:
     *      (x + y) * 2 = x + y + n (y + z)
     * 两边消掉一个（x+y）:  x + y = n (y + z)
     * 因为我们要找环形的入口，那么要求的是x， 因为x表示 头结点到 环形入口节点的的距离。
     * 所以我们要求x ，将x单独放在左面：x = n (y + z) - y
     * 在从n(y+z)中提出一个 （y+z）来，整理公式之后为如下公式：x = (n - 1) (y + z) + z 注意这里n一定是大于等于1的，因为 fast指针至少要多走一圈才能相遇slow指针
     * 这个公式说明什么呢，
     * 当 n为1的时候，公式就化解为 x = z
     * 这就意味着，从头结点出发一个指针，从相遇节点也出发一个指针，这两个指针每次只走一个节点， 那么当这两个指针相遇的时候就是环形入口的节点
     * 也就是在相遇节点处，定义一个指针index1，在头结点处定一个指针index2。
     * 让index1和index2同时移动，每次移动一个节点， 那么他们相遇的地方就是 环形入口的节点。
     * n如果大于1，就是fast指针在环形转n圈之后才遇到 slow指针。
     * 其实这种情况和n为1的时候 效果是一样的，一样可以通过这个方法找到环形的入口节点，只不过，index1 指针在环里 多转了(n-1)圈，然后再遇到index2，相遇点依然是环形的入口节点。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null; // no circle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) { //先判断是否有环, they collided, have cycle linkedlist;
                while (head != fast) { //确定有环之后, 找环的入口
                    //两相遇指针, 一个从头结点开始, 一个从相遇点开始每次走一步直到再次相遇为止
                    fast = fast.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null; // no circle
    }


    /**
     * 方法二
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * HashSet法，不断将节点放进set中，若是已存在，则肯定是入环的第一个节点，将该节点返回即可
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        Set nodes = new HashSet();
        //ListNode current = head;
        while (head != null) {
            if (nodes.contains(head))
                return head;
            nodes.add(head);
            head = head.next;
        }
        return null;
    }
}
