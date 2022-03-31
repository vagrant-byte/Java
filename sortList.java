import java.util.ArrayList;
import java.util.Arrays;

public class sortList {
    //排序链表
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        int size=0;
        ListNode cur=head;
        while (cur!=null) {
            size++;
            cur=cur.next;
        }
        int[] res=new int[size];
        cur=head;
        for (int i = 0; i <size ; i++) {
            res[i]=cur.val;
            cur=cur.next;
        }
        Arrays.sort(res);
        cur=head;
        for (int j = 0; j <res.length ; j++) {
            cur.val=res[j];
            cur=cur.next;
        }
        return head;


    }
}


