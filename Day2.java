package 每日一题;

import java.util.ArrayList;
import java.util.List;

public class Day2 {
    //将数据流变为多个不相交的区间
    private boolean[] nums= new boolean[10001];

    public void SummaryRanges() {

    }

    public void addNum(int val) {
        //将存在的数字标记为true
        nums[val]=true;

    }

    public int[][] getIntervals() {
        int start=-1;
        int end=-1;
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<10001;i++) {
            if(nums[i]) {
                if(start==-1) {
                    start=i;
                    end=i;
                } else {
                    end=i;
                }

            }else {
                if(start!=-1) {
                    list.add(new int[]{start,end});
                    start=-1;
                    end=-1;
                }

            }

        }
        if(start!=-1) {
            list.add(new int[]{start,end});
        }
        return list.toArray(new int[list.size()][2]);

    }
}
