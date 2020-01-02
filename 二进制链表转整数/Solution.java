package github.leetcode.二进制链表转整数;

import java.util.ArrayList;

/**
 * Created by hujingtian on 2019/12/19.
 */
public class Solution {

    public static void main(String[] args){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(0);
        listNode.next.next = new ListNode(1);
        new Solution().getDecimalValue(listNode);
    }

    public int getDecimalValue(ListNode head) {
        ListNode cur = head;
        int ans = 0;
        while (cur != null) {
            ans <<= 1;
            ans += cur.val;
            cur = cur.next;
        }
        return ans;
    }
}
