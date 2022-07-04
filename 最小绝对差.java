import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 最小绝对差 {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> list=new ArrayList<>();
        if(arr.length<2) {
            return list;
        }
        Arrays.sort(arr);
        int min=Integer.MAX_VALUE;
        for (int i = 1; i <arr.length ; i++) {
            int tmp=arr[i]-arr[i-1];
            if (tmp<min) {
                min=tmp;
            }
        }
        for (int i = 1; i <arr.length ; i++) {
            if(arr[i]-arr[i-1]==min) {
                List<Integer> list1=new ArrayList<>();
                list1.add(arr[i-1]);
                list1.add(arr[i]);
                list.add(list1);
            }
        }
        return list;
    }
}
