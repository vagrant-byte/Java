package 链表;

import java.util.ArrayList;
import java.util.Stack;

public class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
//反转链表
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
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack=new Stack<>();
        ArrayList<Integer> list=new ArrayList<>();
        ListNode cur=listNode;
        while (cur!=null) {
            stack.add(cur.val);
            cur=cur.next;
        }
        //出栈
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
//        ArrayList<Integer> list=new ArrayList<>();
//        ListNode cur=listNode;
//        while (cur!=null) {
//            list.add(cur.val);
//            cur=cur.next;
//        }
//        int i=0;
//        int j=list.size()-1;
//        //反转数组
//        while (i<j) {
//            Integer tmp=list.get(i);
//            list.set(i,list.get(j));
//            list.set(j,tmp);
//            i++;
//            j--;
//        }
//        return list;

    }
//  链表中倒数第k个节点 快慢指针，快指针先走k步，然后快慢指针一起往后走，当快指针为空时慢指针所在的位置就是倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null) {
            return null;
        }
        ListNode prev=head;
        ListNode slow=head;
        //prev!=null 防止k超出链表长度
        while (k>0&&prev!=null) {
            prev=prev.next;
            k--;
        }
        while (prev!=null) {
            prev=prev.next;
            slow=slow.next;
        }
        return k>0?null:slow;
    }
    //合并两个排序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(-1);
        ListNode cur=head;
        while (l1!=null&&l2!=null) {
            if (l1.val<l2.val) {
                cur.next=l1;
                l1=l1.next;
                cur=cur.next;
            } else {
                cur.next=l2;
                l2=l2.next;
                cur=cur.next;
            }
        }
        if(l1==null) {
            cur.next=l2;
        }else {
            cur.next=l1;
        }
        return head.next;
    }
    //删除链表重复节点
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        //使用一个头节点
        ListNode newHead=new ListNode(0);
        newHead.next=head;

        ListNode prev=newHead;
        ListNode last=prev.next;
        while (last!=null) {
            while (last.next!=null&&last.val!=last.next.val) {
                prev=last;
                last=last.next;
            }
            while (last.next!=null&&last.val==last.next.val) {
                //删除重复节点
                last=last.next;
            }
            //走到这里一共有三种情况
            //1.last.next!=null 此时去重
            //2.last.next==null
            //3.last.next==null&&prev.next==null 说明没有重复的节点
            if(prev.next!=last) {
                //去重
                prev.next=last.next;
            }
            last=last.next;
        }
        return newHead.next;
    }
}
