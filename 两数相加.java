package 链表;

public class 两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null) {
            return l2;
        }
        if(l2==null) {
            return l1;
        }
        int tmp=0;
        ListNode head=new ListNode(-1);
        ListNode cur=head;
        while (l1!=null&&l2!=null) {
            tmp+=(l1.val+l2.val);
            cur.next=new ListNode(tmp%10);
            tmp=tmp/10;
            l1=l1.next;
            l2=l2.next;
            cur=cur.next;
        }
        if(l1==null) {
            while (l2!=null) {
                tmp+=l2.val;
                cur.next=new ListNode(tmp%10);
                tmp=tmp/10;
                cur=cur.next;
                l2=l2.next;
            }
        }
        if(l2==null) {
            while (l1!=null) {
                tmp+=l1.val;
                cur.next=new ListNode(tmp%10);
                tmp=tmp/10;
                cur=cur.next;
                l1=l1.next;
            }
        }
        if(tmp!=0) {
            cur.next=new ListNode(tmp%10);
        }
        return head.next;
    }
}
