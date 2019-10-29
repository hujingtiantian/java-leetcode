package github.leetcode.K个一组翻转链表;

/**
 * Created by hujingtian on 2019/10/24.
 */
public class Solution {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next = new ListNode(7);
//        head.next.next.next.next.next.next.next = new ListNode(8);
  //      head.next.next.next.next.next.next.next.next = new ListNode(9);
        new Solution().reverseKGroup(head,2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode retPre = null;
        ListNode cur = head;
        ListNode retEnd = head;
        while (cur!=null){
            ListNode pre = null;
            ListNode start = cur;
            ListNode end = cur;
            boolean flag = checkReverseKGroup(end,k);
            if(!flag){
                if(retPre != null){
                    retEnd.next = cur;
                    return retPre;
                }else {
                    return head;
                }
            }
            for(int i = 0 ; i < k ;i++){
                ListNode tempNext = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tempNext;
            }
            if(retPre == null){
                retPre=pre;
                retEnd.next = cur;
            }else {
                retEnd.next = pre;
                retEnd = start;
            }

        }

       return retPre;
    }

    private boolean checkReverseKGroup(ListNode end, int k) {
        for(int i = 0 ; i < k-1 ; i++){
            if(end == null){
                return false;
            }
            end = end.next;
        }
        if(end == null){
            return false;
        }
        return true;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
