import java.util.PriorityQueue;

public class helpTest {
    //最后一块石头的重量
    public int lastStoneWeight(int[] stones) {
        //大堆
        PriorityQueue<Integer> p = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i <stones.length ; i++) {
            p.offer(stones[i]);
        }
        while (p.size()>=2) {
            int x=p.poll();
            int y=p.poll();
            if(x>y) {
                p.offer(x-y);
            }
        }
        return p.size()==1?p.peek():0;
    }
}
