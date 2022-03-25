import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class test20220325 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int t=sc.nextInt();
            for (int i = 0; i <t ; i++) {
                int[] tmp=new int[5];
                for (int j = 0; j <5 ; j++) {
                    tmp[j]=sc.nextInt();
                }
                System.out.println(counts(tmp));
            }
        }
    }

    private static int counts(int[] tmp) {
        for (int i = 1; i <5 ; i++) {
            tmp[i]=tmp[i]-1;
        }
        int n=0;
        for (int i = 1; i <5 ; i++) {
            if(tmp[i]<=0) {
                n++;
            }
            if(n>=2) {
                return 1;
            }
        }
        Arrays.sort(tmp);
        return counts(tmp)+1;
    }

//    public static void main(String[] args) {
//        int[] a={1,1,1,100,1};
//        Arrays.sort(a);
//        int r=counts(a);
//        System.out.println(r);
//    }

    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        String[] strings=new String[t];
        for (int i = 0; i <t ; i++) {
            strings[i]=sc.next();
        }
        HashSet<String> set=new HashSet<>();
        for (int i = 0; i <strings.length ; i++) {
            boolean flag=true;
            int len=strings[i].length();
            if(len<6||len>12) {
                flag=false;
                System.out.println("illegal length");
            }
            char[] chars=strings[i].toCharArray();
            for (int j = 0; j <chars.length ; j++) {
                if(!((chars[j]>='A'&&chars[j]<='Z')||(chars[j]>='a'&&chars[j]<='z'))) {
                    flag=false;
                    System.out.println("illegal charactor");
                }
            }
            if(flag) {
                if(set.contains(strings[i])) {
                    System.out.println("acount existed");
                } else {
                    set.add(strings[i]);
                    System.out.println("registration complete");
                }
            }
        }
    }
}
