import java.util.Scanner;

public class Day4 {
    //山顶峰
    public int peakIndexInMountainArray(int[] arr) {
        int max=arr[0];
        for(int i=1;i<arr.length;i++) {
            if(max<arr[i]) {
                max=arr[i];
            }
        }
        for(int i=0;i<arr.length;i++) {
            if(arr[i]==max) {
                return i;
            }
        }
        return -1;

    }
    //进制转换
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int M=scanner.nextInt();
        int N=scanner.nextInt();
        String str="";
        int remainder;//余数
        int quotient=M;//商
        while (quotient>0) {
            remainder=quotient%N;
            quotient=quotient/N;
            if(remainder>9) {
                str=(char)('a'+(remainder-10))+str;
            } else {
                str=remainder+str;
            }
        }
        System.out.println(str);
    }
    //三元一次方程求解
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] arr=new int[4];
        for (int i = 0; i <arr.length; i++) {
            arr[i]=scanner.nextInt();
        }
        int a=(arr[0]+arr[2])/2;
        int b1=(arr[1]+arr[3])/2;
        int b2=(arr[3]-arr[0])/2;
        int c=(arr[3]-arr[1])/2;
        if(b1==b2) {
            System.out.println("No");
            return;
        }
        if(a-((arr[0]+arr[2])/2)!=0) {
            System.out.println("No");
            return;
        }
        if(b1-((arr[1]+arr[3])/2)!=0) {
            System.out.println("No");
            return;
        }
        if(c-((arr[3]-arr[1])/2)!=0) {
            System.out.println("No");
            return;
        }
        System.out.println(a+" "+b1+" "+c);

    }
}
