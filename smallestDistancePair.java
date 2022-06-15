import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class smallestDistancePair {

    //找出第K小的数对距离
    //public static int smallestDistancePair(int[] nums, int k) {
    //
    //        int len=nums.length;
    //        int size=(len)*(len-1)/2;
    //        int[] arr=new int[size];
    //        int index=0;
    //        for (int i = 0; i <nums.length-1 ; i++) {
    //            for (int j = i+1; j <nums.length ; j++) {
    //                int num=Math.abs(nums[i]-nums[j]);
    //                arr[index++]=num;
    //            }
    //        }
    //        Arrays.sort(arr);
    //        return arr[k-1];
    //   }
    public static int smallestDistancePair(int[] nums, int k) {
        int len=nums.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <len ; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }
        int zeroNum=0;
        int size=map.size();
        int[] arr=new int[size];
        int index=0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            arr[index++]=entry.getKey();
            if(entry.getValue()!=1) {
                zeroNum+=entry.getValue()/2;
            }
        }
        if(arr.length==1) {
            return 0;
        }
        if(zeroNum>=k) {
            return 0;
        }else {
            k-=zeroNum;
        }
        int count=(arr.length)*(arr.length-1)/2;
        int[] tmp=new int[count];
        index=0;
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                tmp[index++]=Math.abs(arr[i]-arr[j]);
            }
        }
        Arrays.sort(tmp);
        if(k>tmp.length) {
            return tmp[0];
        } else {
            return tmp[k-1];
        }

    }

    public static void main(String[] args) {
        int[] nums={1,6,1};
        int k=2;
        int n=smallestDistancePair(nums, k);
        System.out.println(n);
    }
}
