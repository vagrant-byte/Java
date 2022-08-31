package 队列;

import java.util.PriorityQueue;

//数据流中的中位数
public class MedianFinder {
    PriorityQueue<Integer> minQ;//小根堆
    PriorityQueue<Integer> maxQ;//大根堆
    public MedianFinder() {
        minQ=new PriorityQueue<>();
        maxQ=new PriorityQueue<Integer>((a,b)->b-a);
    }

    public void addNum(int num) {
        if(maxQ.isEmpty()) {
            maxQ.add(num);
        }else if(maxQ.peek()>=num) {
            maxQ.add(num);
        }else {
            minQ.add(num);
        }
        int minLen=minQ.size();
        int maxLen=maxQ.size();
        while (Math.abs(maxLen-minLen)>=2) {
            if(maxLen>minLen) {
                minQ.add(maxQ.poll());
                maxLen--;
            }else {
                maxQ.add(minQ.poll());
                minLen--;
            }
        }
    }

    public double findMedian() {
        int minLen=minQ.size();
        int maxLen=maxQ.size();
        if(maxLen>minLen) {
            return maxQ.peek();
        }else if(maxLen<minLen) {
            return minQ.peek();
        }else {
            return (double)((maxQ.peek()+minQ.peek())/2);
        }
    }
}
