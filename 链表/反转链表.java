package 链表;

import java.util.Stack;

public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        if(head==null) {
            return null;
        }
        ListNode newHead=null;
        while (head!=null) {
            ListNode tmp=head.next;
            head.next=newHead;
            newHead=head;
            head=tmp;
        }
        return newHead;

    }
    //使用栈
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode newHead=stack.pop();
        ListNode tmp=newHead;
        while (!stack.isEmpty()) {
            tmp.next = stack.pop();
            tmp = tmp.next;
        }
        tmp.next=null;
        return newHead;
    }
}
