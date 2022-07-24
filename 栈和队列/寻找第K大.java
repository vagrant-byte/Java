package 栈和队列;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 寻找第K大 {
    public int findKth(int[] a, int n, int K) {
        // write code here
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(K, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for (int i = 0; i <n ; i++) {
            if(priorityQueue.size()<K) {
                priorityQueue.add(a[i]);
            }else {
                int tmp=priorityQueue.peek();
                if(tmp<a[i]) {
                    priorityQueue.poll();
                    priorityQueue.offer(a[i]);
                }
            }
        }
        return priorityQueue.peek();
    }
}
