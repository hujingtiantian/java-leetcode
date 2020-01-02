/**
 * Created by hujingtian on 2019/10/25.
 */
/**
 * Definition for singly-linked list.
 *
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var swapPairs = function(head) {
    if(head == null || head.next == null){
        return head;
    }
    var retNode = head.next;
    var pre = null;
    var next;
    var tempNode = head;
    while (tempNode != null && tempNode.next != null){
        next = tempNode.next;
        tempNode.next = next.next;
        next.next = tempNode;
        if(pre != null){
            pre.next = next;
        }
        pre = tempNode;
        tempNode = tempNode.next;
    }
    return retNode;
};

function ListNode(val) {
     this.val = val;
     this.next = null;
}