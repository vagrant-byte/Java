import java.util.Comparator;
import java.util.PriorityQueue;

//合并K个升序链表  用容量为k的最小堆优先队列，把链表的头节点放进去，然后出队当前优先队列中最小的挂在链表上，
// 然后让出队的那个节点的下一个入队，再出从当前优先队列中最小,直到优先队列为空
public class mergeKLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) {
            return null;
        }
        ListNode cur=new ListNode(-1);
        ListNode tmp=cur;
        PriorityQueue<ListNode> priorityQueue=new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });

        for (ListNode node:lists) {
            if (node==null) {
                continue;
            }
            priorityQueue.add(node);
        }
        while (!priorityQueue.isEmpty()) {
            ListNode listNode=priorityQueue.poll();
            tmp.next=listNode;
            tmp=tmp.next;
            if(listNode.next!=null) {
                priorityQueue.add(listNode.next);
            }
        }
        return cur.next;
    }
}
