import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class q {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] strings=s.split(",");
        int len=strings.length;
        int[] num=new int[len];
        int res=0;
        for (int i = 0; i <len ; i++) {
            num[i]=Integer.valueOf(strings[i]);
        }
        Arrays.sort(num);
        for (int i = 0; i <len ; i++) {
            if(num[i]!=i) {
                res=i;
                break;
            }
        }
        System.out.println(res);
    }
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] strings=s.split(",");
        int size=strings.length;
        int[] nums=new int[size];
        for (int i = 0; i <strings.length ; i++) {
            nums[i]=Integer.valueOf(strings[i]);
        }
        String res="";
        for(int i=0;i<size-1;i++) {
            for(int j=i+1;j<size;j++) {
                int num1=nums[i];
                int num2=nums[j];
                while(num1>10) {
                    num1/=10;
                }
                while(num2>10) {
                    num2/=10;
                }
                if(num1<num2) {
                    int tmp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=tmp;
                }
            }
        }
        for(int i=0;i<size;i++) {
            res+=nums[i]+"";
        }
        System.out.println(res);
    }
}
