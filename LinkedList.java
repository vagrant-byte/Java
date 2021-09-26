package 链表题;
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class LinkedList {
    //移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        if(head==null) {
            return null;
        }
        ListNode prev=head;
        ListNode cur=prev.next;
        while (cur!=null) {
            if(cur.val==val) {
                prev.next=cur.next;
                cur=cur.next;
            } else {
                prev=cur;
                cur=cur.next;
            }
        }
        if(head.val==val) {
            head=head.next;
        }
        return head;
    }
    //反转链表
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode cur=head;
        while (cur!=null) {
            ListNode tmp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=tmp;
        }
        return prev;
    }
    //返回链表中间节点
    public ListNode middleNode(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        if(head==null) {
            return null;
        }
        while (fast!=null&&fast.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    //链表倒数第k个节点
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode slow=head;
        ListNode fast=head;
        while(k>0) {
            if(fast!=null) {
                fast=fast.next;
                k--;
            } else {
                return null;
            }

        }
        while(fast!=null) {
            fast=fast.next;
            slow=slow.next;
        }
        return slow;

    }
    //合并两个链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(-1);
        ListNode tmp=head;
        while(l1!=null&&l2!=null) {
            if(l1.val<l2.val) {
                tmp.next=l1;
                l1=l1.next;
                tmp=tmp.next;
            } else {
                tmp.next=l2;
                l2=l2.next;
                tmp=tmp.next;
            }
        }
        if(l1==null) {
            tmp.next=l2;
        } else{
            tmp.next=l1;
        }
        return head.next;
    }
    //链表分割
    public ListNode partition(ListNode pHead, int x) {
        // write code here
        ListNode as=null;
        ListNode ae=null;
        ListNode bs=null;
        ListNode be=null;
        ListNode cur=pHead;
        while(cur!=null) {
            if(cur.val<x) {
                if(as==null) {
                    as=cur;
                    ae=cur;
                } else {
                    ae.next=cur;
                    ae=ae.next;
                }
            } else {
                if(bs==null) {
                    bs=cur;
                    be=cur;
                } else {
                    be.next=cur;
                    be=be.next;
                }
            }
            cur=cur.next;
        }
        if(as==null) {
            return bs;
        }
        ae.next=bs;
        if(bs!=null) {
            be.next=null;
        }
        return as;
    }

}
