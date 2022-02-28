package 二进制;

public class divide {
    //整数除法
    public static int divide1(int a, int b) {
        if(a==Integer.MIN_VALUE&&b==-1) {
            return Integer.MAX_VALUE;
        }
        int sign=(a>0)^(b>0)?-1:1;
        if(a>0) {
            a=-a;
        }
        if(b>0) {
            b=-b;
        }
        int count=0;
        while (a<=b) {
            a-=b;
            count++;
        }
        return sign==1?count:-count;
    }
    public static int divide2(int a, int b) {
        if(a==Integer.MIN_VALUE&&b==-1) {
            return Integer.MAX_VALUE;
        }
        int sign=(a>0)^(b>0)?-1:1;
        a=Math.abs(a);
        b=Math.abs(b);
        int count=0;
        for (int i = 31; i >=0 ; i--) {
            if((a>>>i)-b>=0) {
                a-=(b<<i);
                count+=(1<<i);
            }
        }
        return sign==1?count:-count;
    }
    //前n个数字二进制中1的个数
    public int[] countBits(int n) {
        int[] res=new int[n+1];
        for (int i = 0; i <=n ; i++) {
            res[i]=res[i>>1]+(i&1);
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(divide2(7, -2));
    }
}
