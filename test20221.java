package 贝壳;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test20221 {
    public long[] FarmerNN (int n, long m) {
        long[] res=new long[n];
        if(n>m) {
            for (int i = 0; i <m ; i++) {
                res[i]=1;
            }
            return res;
        }
        //表示可以循环几趟
        long tmp=m/n;
        for (int i = 0; i <n ; i++) {
            res[i]=tmp;
        }
        //剩下的肥料
        long index=m%n;
        //循环了偶数趟
        if(tmp%2==0) {
            while (index>0&&n>=0) {
                res[n-2]++;
                n--;
                index--;
            }
        } else {
            int i=1;
            while (index>0&&i<n) {
                res[i]++;
                i++;
                index--;
            }
        }
        return res;
    }
    //关键词提取
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            String[] strings=new String[n];
            for (int i = 0; i <n ; i++) {
                strings[i]=sc.next().toLowerCase();
            }
            HashMap<String,Integer> map=new HashMap<>();
            for (int i = 0; i <n ; i++) {
                if(map.containsKey(strings[i])) {
                    map.put(strings[i],map.get(strings[i])+1);
                } else {
                    map.put(strings[i],1);
                }
            }
            int max=0;
            String res="";
            for (Map.Entry<String,Integer> entry:map.entrySet()) {
                if(max<entry.getValue()) {
                    max=entry.getValue();
                    res=entry.getKey();
                } else if(max==entry.getValue()) {
                    res=res.charAt(0)>entry.getKey().charAt(0)?entry.getKey():res;
                }
            }
            System.out.println(res+" "+max);
        }
    }
}
