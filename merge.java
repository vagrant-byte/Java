import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class merge {
    //合并区间
    public static int[][] merge(int[][] intervals) {
        //将元素按照第一列元素排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
//        ArrayList<int[]> list=new ArrayList<>();
//        for (int i = 0; i <intervals.length ; i++) {
//            int left=intervals[i][0];
//            int right=intervals[i][1];
//            if(list.size()==0||list.get(list.size()-1)[1]<left) {
//                list.add(new int[]{left,right});
//            } else {
//                list.get(list.size()-1)[1]=Math.max(list.get(list.size()-1)[1],right);
//            }
//        }
        if(intervals.length==1) {
            return intervals;
        }
        ArrayList<int[]> list=new ArrayList<>();
        if(intervals[0][1]<intervals[1][0]) {
            int[] tmp=new int[]{2};
            tmp[0]=intervals[0][0];
            tmp[1]=intervals[0][1];
            list.add(tmp);
        }
        for (int i = 1; i <intervals.length; i++) {
            if(intervals[i-1][1]>=intervals[i][0]) {
                int[] tmp=new int[2];
                tmp[0]=intervals[i-1][0];
                tmp[1]=Math.max(intervals[i-1][1],intervals[i][1]);
                list.add(tmp);
            } else {
                int[] tmp=new int[2];
                tmp[0]=intervals[i][0];
                tmp[1]=intervals[i][1];
                list.add(tmp);
            }
        }
        return list.toArray(new int[list.size()][2]);

    }

    public static void main(String[] args) {
        int[][] a={{1,3},{2,6},{8,10},{15,18}};
        int[][] r=merge(a);
        for (int i = 0; i <r.length ; i++) {
            for (int j = 0; j <r[i].length ; j++) {
                System.out.print(r[i][j]);
            }
            System.out.println();
        }
    }
}
