class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

 //删除倒数第K个节点
public class removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first=head;
        ListNode slow=head;
        while (n>0) {
            first=first.next;
            n--;
        }
        if(first==null) {
            return head.next;
        }
        while (first.next!=null) {
            first=first.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;

    }
}
