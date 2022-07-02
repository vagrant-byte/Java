package 链表;

public class 链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null) {
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;
        while (k>0) {
            fast=fast.next;
            k--;
        }
        if(fast==null) {
            //倒数第k个节点大于链表长度
            return head;
        }
        while (fast!=null) {
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
}
