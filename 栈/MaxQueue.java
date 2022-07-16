package 栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//队列的最大值
public class MaxQueue {
//    双端队列
    public Deque<Integer> queue;
    public Deque<Integer> max_queue;
    public MaxQueue() {
        queue=new ArrayDeque<>();
        max_queue=new ArrayDeque<>();
    }

    public int max_value() {
        if(max_queue.isEmpty()) {
            return -1;
        }
        return max_queue.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!max_queue.isEmpty()&&value>max_queue.peekLast()) {
            max_queue.pollLast();
        }
        max_queue.offer(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        int val=queue.pop();
        if(max_queue.peek()==val) {
            max_queue.pop();
        }
        return val;
    }
}
