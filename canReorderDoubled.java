import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class canReorderDoubled {
    //二倍数对数组
    //本质是问arr能否分成n/2对元素每对元素中一个数是另一个数的两倍
    public static boolean canReorderDoubled(int[] arr) {
        int len=arr.length;
        Arrays.sort(arr);
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <arr.length ; i++) {
            //添加负数时，已经在map中的负数是更小的，则通过乘2 与之组队
            //添加0 和 正数时，在map的是 负数应该匹配完毕，正数添加先与其/2 的正数匹配
            if(arr[i]>=0&&arr[i]%2==0&&map.containsKey(arr[i]/2)) {
                int num=map.get(arr[i]/2);
                if(num>1) {
                    map.put(arr[i]/2,num-1);
                } else {
                    map.remove(arr[i]/2);
                }
                continue;
            } else if(arr[i]<0&&map.containsKey(arr[i]*2)) {
                int num=map.get(arr[i]*2);
                if(num>1) {
                    map.put(arr[i]*2,num-1);
                } else {
                    map.remove(arr[i]*2);
                }
                continue;
            }
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        return map.isEmpty();
//        int len=arr.length;
//        //将arr中的数全部变为正数
//        int count=0;
//        //存正数
//        List<Integer> list1=new ArrayList<>();
//        //存负数
//        List<Integer> list2=new ArrayList<>();
//        for (int i = 0; i <len ; i++) {
//            if(arr[i]==0) {
//                count++;
//            }else if(arr[i]<0) {
//                list2.add(arr[i]);
//            }else {
//                list1.add(arr[i]);
//            }
//        }
//        if(count%2!=0||list1.size()%2!=0||list2.size()%2!=0) {
//            return false;
//        }
//        int[] arr1=new int[list1.size()];
//        for (int i = 0; i <arr1.length ; i++) {
//            arr1[i]=list1.get(i);
//        }
//        Arrays.sort(arr1);
//        int i1=0;
//        int j1=arr1.length/2;
//        while (i1<arr1.length/2&&j1<arr1.length) {
//            if(arr1[i1]*2!=arr1[j1]) {
//                return false;
//            }
//        }
//        int[] arr2=new int[list2.size()];
//        for (int i = 0; i <arr2.length ; i++) {
//            arr2[i]=list2.get(i);
//        }
//        Arrays.sort(arr2);
//        int i2=0;
//        int j2=arr2.length/2;
//        while (i2<arr2.length/2&&j2<arr2.length) {
//            if(arr2[i2]*2!=arr2[j2]) {
//                return false;
//            }
//        }
//        return true;
    }

    public static void main(String[] args) {
        int[] a={2,4,0,0,8,1};
        canReorderDoubled(a);
    }
}
