import java.util.Scanner;

public class Day28 {
    //猴子分桃
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            if (n==0) {
                break;
            }
            long to=(long)Math.pow(5,n)-4;
            long left=(long)Math.pow(4,n)+n-4;
            System.out.println(to+" "+left);
        }
    }
    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
           int monkey=scanner.nextInt();
           if(monkey==0) {
               return;
           }
            int i=1;//循环次数
            int peach=1;//桃子数
            int tmp=1;
            while (i<=monkey) {
                if(peach%5==1) {
                    peach=(peach/5)*4;
                    i++;
                }else {
                    tmp++;
                    peach=tmp;
                    i=1;
                }
            }
            System.out.println(tmp);
        }
    }
    //奇数位上都是奇数偶数位上都是偶数
    public static void oddInOddEvenInEven(int[] arr) {
        int i=0;
        int j=1;
        while (i<arr.length&&j<arr.length) {
            if(arr[i]%2==0) {
                i+=2;
                continue;
            }
            if(arr[j]%2==1) {
                j+=2;
                continue;
            }
            int tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }

    }
    public static void main1(String[] args) {
        int[] a={1,2,3,4};
        oddInOddEvenInEven(a);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]);
        }
    }
}
