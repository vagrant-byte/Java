package 链表;

public class 两个链表的第一个公共节点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
       ListNode L1=headA;
       ListNode L2=headB;
       int size1=0;
       int size2=0;
       while (L1!=null) {
           size1++;
           L1=L1.next;
       }
       while (L2!=null) {
           size2++;
           L2=L2.next;
       }
       L1=headA;
       L2=headB;
       int len=size1-size2;
       if (len<0) {
           L1=headB;
           L2=headA;
           len=size2-size1;
       }
       while (len>0) {
           L1=L1.next;
           len--;
       }
        while (L1!=L2) {
            L1=L1.next;
            L2=L2.next;
        }
        return L1;

    }
}
