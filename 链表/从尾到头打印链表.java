package 链表;

import java.util.Stack;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
public class 从尾到头打印链表 {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        while (head!=null) {
            stack.push(head.val);
            head=head.next;
        }
        int[] res=new int[stack.size()];
        int index=0;
        while (!stack.isEmpty()) {
            res[index++]=stack.pop();
        }
        return res;
    }
}
