package 美团;

import java.util.Scanner;

public class test20220312 {
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            int[] tmp=new int[n];
            for (int i = 0; i <tmp.length ; i++) {
                tmp[i]=sc.nextInt();
            }
            String[] strings=new String[n];
            for (int i = 0; i <tmp.length ; i++) {
                if(i%11==0||two(i)) {
                    strings[i]="yes";
                } else {
                    strings[i]="no";
                }
            }
            for (int i = 0; i <strings.length ; i++) {
                System.out.println(strings[i]);
            }
        }
    }

    private static boolean two(int i) {
        int count=0;
        while (i>0) {
            if(i%10==1) {
                count++;
            }
            if(count>=2) {
                return true;
            }
            i/=10;
        }
        return false;
    }

}
