import java.util.Arrays;
import java.util.Comparator;

public class findRightInterval {
    //寻找右区间
    public int[] findRightInterval(int[][] intervals) {
        int len=intervals.length;
        int[][] tmp=new int[len][2];
        for (int i = 0; i <len ; i++) {
            tmp[i][0]=intervals[i][0];
            tmp[i][1]=i;
        }
        Arrays.sort(tmp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int[] res=new int[len];
        for (int i = 0; i <len ; i++) {
            int left=0;
            int right=len-1;
            int target=-1;
            while (left<=right) {
                int mid=(left+right)/2;
                if(tmp[mid][0]>=intervals[i][1]) {
                    target=tmp[mid][1];
                    right=mid-1;
                } else {
                    left=mid+1;
                }
            }
            res[i]=target;
        }
        return res;
    }
}
