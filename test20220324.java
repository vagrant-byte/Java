package 阿里;

import com.sun.xml.internal.ws.api.server.LazyMOMProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class test20220324 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int A=sc.nextInt();
        int B=sc.nextInt();
        int a=sc.nextInt();
        int b=sc.nextInt();
        int max=0;
        int[] tmp=new int[]{0,0};
        for (int i = 1; i <=A ; i++) {
            for (int j = 1; j <=B ; j++) {
                if(i/j==a/b) {
                    if(max<i*j) {
                        max=i*j;
                        tmp[0]=i;
                        tmp[1]=j;
                    }
                }
            }
        }
        System.out.println(tmp[0]+" "+tmp[1]);

    }
    public static void main4(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        HashSet<List<Integer>> set=new HashSet<>();
        for (int i = 1; i <=n ; i++) {
            List<Integer> list1=new ArrayList<>();
            while (m>0) {
                list1.add(i);
                m--;
            }
            set.add(list1);

        }
    }

    //完美数
    public static void main3(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int k=sc.nextInt();
            int[][] tmp=new int[n][k];
            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <k; j++) {
                    tmp[i][j]=sc.nextInt();
                }
            }
            int res=0;
            for (int i = 0; i <n-1 ; i++) {
                for (int j = i+1; j <n ; j++) {
                    if(check(tmp,i,j,k)) {
                        res++;
                    }
                }
            }
            System.out.println(res);
        }
    }

    private static boolean check(int[][] tmp, int i, int j, int k) {
        int sum=tmp[i][0]+tmp[j][0];
        for (int l = 1; l <k ; l++) {
            if(tmp[i][l]+tmp[j][l]!=sum) {
                return false;
            }
        }
        return true;
    }

    public static void main2(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int T=sc.nextInt();
            for (int i = 0; i <T ; i++) {
                int A=sc.nextInt();
                int B=sc.nextInt();
                int n=sc.nextInt();
                long x= count(A,B,n);
                System.out.println(x);
            }
        }
    }

    private static long count(int a, int b, int n) {
        int tmp=a*a-2*b;
        if(n==0) {
            return 2;
        }
        if(n==1) {
            return a;
        }
        if(n==2) {
            return tmp;
        }
        for (int i = 0; i <=n-2 ; i++) {
            tmp*=a;
        }
        return tmp;
    }

    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int T=sc.nextInt();//几组数据
            for (int i = 0; i <T ; i++) {
                int n=sc.nextInt();//物品个数
                int[] x=new int[n];
                int[] y=new int[n];
                for (int j = 0; j <n ; j++) {
                    x[j]=sc.nextInt();
                }
                for (int j = 0; j <n ; j++) {
                    y[j]=sc.nextInt();
                }
                int count=compare(x,y);
                System.out.println(count);
            }
        }
    }

    public static int compare(int[] x, int[] y) {
        int count=0;
        int n=x.length;
        for (int i = 0; i <n-1 ; i++) {
            for (int j = i+1; j <n ; j++) {
                if(x[i]>x[j]&&y[i]>y[j]) {
                    count++;
                }
                if(x[i]<x[j]&&y[i]<y[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
