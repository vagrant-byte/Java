package 链表;

import java.util.Stack;

public class 链表内指定区间反转 {
    public static ListNode reverseBetween (ListNode head, int m, int n) {
        if(head==null) {
            return null;
        }
        ListNode newHead=new ListNode(-1);
        newHead.next=head;
        ListNode prev=newHead;
        int length=n-m;
        while (head!=null&&m>1) {
            prev=prev.next;
            m--;
        }
        head=prev.next;
        Stack<ListNode> stack=new Stack<>();
        for (int i = 0; i <=length ; i++) {
            stack.push(head);
            head=head.next;
        }
        while (!stack.isEmpty()) {
            prev.next=stack.pop();
            prev=prev.next;
        }
        prev.next=head;
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode h1=new ListNode(2);
        ListNode h2=new ListNode(3);
        ListNode h3=new ListNode(4);
        ListNode h4=new ListNode(5);
        head.next=h1;
        h1.next=h2;
        h2.next=h3;
        h3.next=h4;
        h4.next=null;
        int m=2;
        int n=4;
        reverseBetween(head,m,n);
    }
}
