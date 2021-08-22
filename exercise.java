package 剑指offer;

import java.util.Stack;
//复杂链表的复制
/*class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
    public Node copyRandomList(Node head) {
        if(head==null) {
            return null;
        }
        Node cur=head;
        //链表节点复制
        while(cur!=null) {
            Node copyNode=new Node(cur.val);
            copyNode.next=cur.next;
            cur.next=copyNode;
            cur=cur.next.next;
        }
        //从头开始遍历链表，通过 cur.next.random = cur.random.next 可以将复制节点的随机指针串起来，当然需要判断 cur.random 是否存在
        cur=head;
        while(cur!=null) {
            if(cur.random!=null) {
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        //将链表一分为2
        Node copyHead=head.next;
        cur = head;
        Node curCopy = head.next;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            if (curCopy.next != null) {
                curCopy.next = curCopy.next.next;
                curCopy = curCopy.next;
            }
        }
        return copyHead;
    }*/
public class exercise {
    Stack<Integer> stack=new Stack<>();
    Stack<Integer> minStack=new Stack<>();
    public void MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if(minStack.empty()) {
            minStack.push(x);
        } else {
            int tmp=minStack.peek();
            if(x>tmp) {
                minStack.push(tmp);
            } else {
                minStack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
