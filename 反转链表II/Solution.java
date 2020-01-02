package github.leetcode.反转链表II;

/**
 * Created by hujingtian on 2019/10/25.
 */
public class Solution {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        new Solution().reverseBetween(head,3,4);

    }



    public ListNode reverseBetween(ListNode head, int m, int n) {
        int num = n - m ;
        if(num == 0){
            return head;
        }
        ListNode retPre = null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode end = null;
        for(int i = 1 ; i < m ; i++){
            if(i == m-1){
                retPre = cur;
            }
            cur = cur.next;
        }
        end = cur;
        num = num+1;
        while (num > 0){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            num--;
        }
        if(retPre != null){
            retPre.next = pre;
        }else {
            retPre = pre;
        }
        end.next = cur;
        if(m > 1){
            return head;
        }
        return retPre;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
