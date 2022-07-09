package 链表;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class 复杂链表的复制 {
    public Node copyRandomList(Node head) {
        if(head==null) {
            return null;
        }
        //原节点 拷贝节点
        HashMap<Node,Node> map=new HashMap<>();
        Node cur=head;
        while (cur!=null) {
            //拷贝值
            map.put(cur,new Node(cur.val));
            cur=cur.next;
        }
        cur=head;
        while (cur!=null) {
            //拷贝节点
            map.get(cur).next=map.get(cur.next);
            map.get(cur).random=map.get(cur.random);
            cur=cur.next;
        }
        return map.get(head);
    }
}
