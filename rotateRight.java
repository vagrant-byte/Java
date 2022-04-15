import java.util.ArrayList;
import java.util.List;

public class rotateRight {
    //旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) {
            return null;
        }
        int count=1;
        ListNode cur=head;
        while (cur.next!=null) {
            count++;
            cur=cur.next;
        }
        k%=count;
        if(k==0) {
            return head;
        }
        //首尾相连
        cur.next=head;
        //找到倒数第k个节点
        for (int i = 0; i <count-k ; i++) {
            cur=cur.next;
        }
        ListNode newHead=cur.next;
        cur.next=null;
        return newHead;
    }
}
