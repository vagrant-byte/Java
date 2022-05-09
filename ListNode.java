package 链表;

import java.util.ArrayList;
import java.util.Stack;

public class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public int[] reversePrint(ListNode head) {

//        ==================用栈进行存储===================
//        Stack<Integer> stack=new Stack<>();
//        ListNode cur=head;
//        while (cur!=null) {
//            stack.push(cur.val);
//            cur=cur.next;
//        }
//        ArrayList<Integer> list=new ArrayList<>();
//        if (!stack.isEmpty()) {
//            list.add(stack.pop());
//        }
//        int size=list.size();
//        int[] res=new int[size];
//        for (int i = 0; i <size ; i++) {
//            res[i]=list.get(i);
//        }
//        return res;
//        =================数组存储后逆置===================
//        ListNode cur=head;
//        int count=0;
//        while (cur!=null) {
//            count++;
//            cur=cur.next;
//        }
//        int[] res=new int[count];
//        cur=head;
//        int i=0;
//        while (cur!=null) {
//            res[i++]=cur.val;
//            cur=cur.next;
//        }
//        i=0;
//        int[] ret=new int[count];
//        for (int j = count-1; j >=0 ; j--) {
//            ret[i++]=res[j];
//        }
//        return ret;
//   =================递归实现=====================================
        ArrayList<Integer> list=new ArrayList<>();
        printList(head,list);
        int size=list.size();
        int[] res=new int[size];
        for (int i = 0; i <size ; i++) {
            res[i]=list.get(i);
        }
        return res;
    }
    private void printList(ListNode head, ArrayList<Integer> list) {
        if(head==null) {
            return;
        }
        printList(head.next,list);
        list.add(head.val);
    }

}
