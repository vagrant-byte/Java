package 链表题;
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class LinkedList {
    //移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        if(head==null) {
            return null;
        }
        ListNode prev=head;
        ListNode cur=prev.next;
        while (cur!=null) {
            if(cur.val==val) {
                prev.next=cur.next;
                cur=cur.next;
            } else {
                prev=cur;
                cur=cur.next;
            }
        }
        if(head.val==val) {
            head=head.next;
        }
        return head;
    }
    //反转链表
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode cur=head;
        while (cur!=null) {
            ListNode tmp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=tmp;
        }
        return prev;
    }
    //返回链表中间节点
    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        if(head==null) {
            return null;
        }
        while (fast!=null&&fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

}
