import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day35 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String str=scanner.nextLine();
            String name=scanner.nextLine();
            int i=0;
            int j=0;
            Set<String> set=new HashSet<>();
            while (i<str.length()) {
                if(str.charAt(i)=='\"') {
                    j=str.indexOf('\"',i+1);
                    set.add(str.substring(i+1,j));
                    i=j+2;
                } else {
                    j=str.indexOf(",",i+1);
                    if(j==-1) {
                        set.add(str.substring(i,str.length()));
                        break;
                    }
                    set.add(str.substring(i,j));
                    i=j+1;
                }
            }
           if(set.contains(name)) {
               System.out.println("Ignore");
           } else {
               System.out.println("Important!");
           }
        }
    }

    //年会抽奖
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            float num1=method(n);
            float num2=fac(n);
            float res=(num1/num2)*100;
            System.out.println(String.format("%.2f",res)+"%");
        }
    }
    //阶乘
    private static float fac(int n) {
        float res=1;
        if(n==0) {
            return 1;
        }else if(n>0) {
            res=n*fac(n-1);
        }
        return res;
    }

    //错排
    private static float method(int n) {
        if(n==1) {
            return 0;
        }else if(n==2) {
            return 1;
        } else {
            return (n-1)*(method(n-1)+method(n-2));
        }
    }
}
