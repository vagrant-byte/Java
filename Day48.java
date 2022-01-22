import java.util.ArrayList;
import java.util.List;

public class Day48 {
    //顺时针打印矩阵
    public static int count=0;
    public static int[] clockwisePrint(int[][] mat, int n, int m) {
        // write code here
        if(mat==null||n<=0||m<=0) {
            return null;
        }
        int start=0;
        int[] res=new int[n*m];
        while(n>start*2&&m>start*2) {
            Circle(res,mat,n,m,start);
            start++;
        }
        return res;
    }
    public static void Circle(int[] res,int[][] mat,int n,int m,int start) {
        int endx=n-1-start;
        int endy=m-1-start;
        //从左到右打印
        for(int i=start;i<=endx;i++) {
            res[count]=mat[start][i];
            count++;
        }
        //从上到下打印
        if(start<endy) {
            for(int i=start+1;i<=endy;i++) {
                res[count]=mat[i][endx];
                count++;
            }
        }
        //从右到左
        if(start<endx&&start<endy) {
            for(int i=endx-1;i>=start;i--) {
                res[count]=mat[endy][i];
                count++;
            }
        }
        //从下到上
        if(start<endx&&start<endy-1) {
            for(int i=endy-1;i>=start+1;i--) {
                res[count]=mat[i][start];
                count++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] mat=new int[][]{{1,2},{3,4}};
        int n=2;
        int m=2;
        int[] res=clockwisePrint(mat, n, m);
        for (int i = 0; i <res.length ; i++) {
            System.out.print(res[i]);
        }
    }
    //左右最值最大差
    public static int findMaxGap(int[] A, int n) {
        // write code here
        int max=Integer.MIN_VALUE;
        int index=0;
        for(int i=0;i<n;i++) {
            if(A[i]>max) {
                max=A[i];
                index=i;
            }
        }
        if(index==0) {
            return A[0]-A[n-1];
        } else if(index==n-1) {
            return A[n-1]-A[0];
        } else {
            int left=A[index]-A[0];
            int right=A[index]-A[n-1];
            return left>right?left:right;
        }
    }

    public static void main1(String[] args) {
        int[] A=new int[]{1,2,4,5,9};
        int n=5;
        System.out.println(findMaxGap(A, n));
    }
}
