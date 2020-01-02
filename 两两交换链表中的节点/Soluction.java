package github.leetcode.两两交换链表中的节点;

/**
 * Created by hujingtian on 2019/10/22.
 */


import java.util.List;

/**
 * Definition for singly-linked list.
 */
class Solution {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        new Solution().swapPairs(head);

    }

    public ListNode swapPairs(ListNode head) {
       ListNode retNode = head;
       if(head == null || head.next == null){
           return retNode;
       }
       retNode = head.next;
       ListNode pre = null;
       ListNode temp = head;
       ListNode next = null;
       while (temp != null && temp.next != null){
               next = temp.next;
               temp.next = next.next;
               next.next = temp;
               if(pre != null){
                   pre.next = next;
               }
               pre = temp;
               temp = temp.next;

       }
       return retNode;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}