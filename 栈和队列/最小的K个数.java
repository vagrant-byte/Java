package 栈和队列;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 最小的K个数 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        if(k==0) {
            return list;
        }
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for (int i = 0; i <input.length ; i++) {
            if(priorityQueue.size()<k) {
                priorityQueue.add(input[i]);
            }else {
                int tmp=priorityQueue.peek();
                if(tmp>input[i]) {
                    priorityQueue.poll();
                    priorityQueue.offer(input[i]);
                }
            }
        }
        for (int i = 0; i <k&&!priorityQueue.isEmpty() ; i++) {
            list.add(priorityQueue.poll());
        }
        return list;

    }
}
