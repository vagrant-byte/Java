import java.util.ArrayList;
import java.util.List;

//复写零
public class duplicateZeros {
    public static void duplicateZeros(int[] arr) {
        List<Integer> list1=new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            list1.add(arr[i]);
            if(arr[i]==0) {
                list1.add(0);
            }
        }
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=list1.get(i);
        }
    }

    public static void main(String[] args) {
        int[] a={1,0,2,3,0,4,5,0};
        duplicateZeros(a);
    }
}
