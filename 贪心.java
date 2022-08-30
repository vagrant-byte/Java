import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class 贪心 {
    //最多可以参加的会议数目
    //按照结束时间最早排序
    public int maxEvents(int[][] events) {
        //如果结束时间相同按照开始时间升序排序
        HashSet<Integer> set=new HashSet<>();
        Arrays.sort(events,(first,second)->first[1]==second[1]?
                first[0]-second[0]:first[1]-second[1]);
        int time=events[0][0];
        for (int[] event:events) {
            for (int i = event[0]; i <=event[1] ; i++) {
                if(set.add(i)) {
                    break;
                }
            }
        }
        return set.size();

    }
}
