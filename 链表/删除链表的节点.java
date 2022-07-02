package 链表;

public class 删除链表的节点 {
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) {
            return head;
        }
        if(head.val==val) {
            head=head.next;
        }
        ListNode prev=head;
        ListNode cur=head.next;
        while (cur!=null) {
            if(cur.val==val) {
                prev.next=cur.next;
            }
            prev=prev.next;
            cur=cur.next;
        }
        return head;
    }
}
