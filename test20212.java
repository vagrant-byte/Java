package 贝壳;

import java.util.*;

public class test20212 {
    //简化字符串
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int n=sc.nextInt();
        if(n>s.length()) {
            System.out.println(0);
            return;
        }
        int count=0;
        HashMap<Character,Integer> map=new HashMap<>();
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            if(map.containsKey(chars[i])) {
                map.put(chars[i],map.get(chars[i])+1);
            } else {
                map.put(chars[i],1);
                count++;
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(Map.Entry<Character,Integer> entry:map.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(entry.getValue());
        }
        if(count<=n) {
            System.out.println(stringBuilder.toString().length()-2*count);
        } else {

        }
    }
    static class item {
        public int w;
        public int c;
        public item(int w,int c) {
            this.w=w;
            this.c=c;
        }
    }
    //商场收益统计
    public static void main3(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int m=sc.nextInt();
            HashMap<String,item> map1=new HashMap<>();
            HashMap<String,Integer> map2=new HashMap<>();
            for (int i = 0; i <n ; i++) {
                String s=sc.next();
                int w=sc.nextInt();
                int c=sc.nextInt();
                item e=new item(w,c);
                map1.put(s,e);
            }
            for (int i = 0; i <m ; i++) {
                String t=sc.next();
                int d=sc.nextInt();
                map2.put(t,d);
            }
            int res=0;
            for (Map.Entry<String,Integer> entry:map2.entrySet()) {
                if(map1.get(entry.getKey()).c>=entry.getValue()) {
                    res+=map1.get(entry.getKey()).w*entry.getValue();
                } else {
                    System.out.println(entry.getValue()*(-1));
                    return;
                }
            }
            System.out.println(res);
        }
    }
    //结盟
    public static void main2(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int k=sc.nextInt();
            int[] arr1=new int[n];
            int[] arr2=new int[n];
            for (int i = 0; i <n ; i++) {
                arr1[i]=sc.nextInt();
            }
            for (int i = 0; i <n ; i++) {
                arr2[i]=sc.nextInt();
            }
            int res=0;
            for (int i = 0; i <n ; i++) {
                if(arr2[i]==1) {
                    res+=arr1[i];
                    arr1[i]=0;
                }
            }
            Arrays.sort(arr1);
            int i=arr1.length-1;
            while (k>0&&i>=0) {
                res+=arr1[i];
                k--;
                i--;
            }
            System.out.println(res);
        }
    }

}
