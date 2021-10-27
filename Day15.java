import java.util.Scanner;

import static java.lang.Character.MAX_VALUE;

public class Day15 {
    //查找输入整数二进制中1的个数
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);

        while (scanner.hasNext()) {
            int num=scanner.nextInt();
            int count=0;
            for (int i = 0; i <32 ; i++) {
                if((num&1)==1) {
                    count++;
                }
                num=num>>1;
            }
            System.out.println(count);
        }
    }

    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        int count=0;
        while (num>0) {
            num=num&(num-1);
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        int[] left={0,7,1,6};
        int[] right={1,5,0,6};
        int n=4;
        System.out.println(findMinimum(n,left,right));
    }


    //手套
    public static int findMinimum(int n, int[] left, int[] right) {
        // write code here
        int sumLeft=0;
        int sumRight=0;
        int leftMin=MAX_VALUE;
        int rightMin=MAX_VALUE;
        int sum=0;
        for (int i = 0; i <n ; i++) {
            if(left[i]*right[i]==0) {
                sum=sum+left[i]+right[i];
            } else {
                sumLeft+=left[i];
                if(sumLeft<leftMin) {
                    leftMin=sumLeft;
                }
                sumRight+=right[i];
                if(sumRight<rightMin) {
                    rightMin=sumRight;
                }
            }
        }
        return sum+Math.min(sumLeft-leftMin+1,sumRight-rightMin+1)+1;
    }
}
