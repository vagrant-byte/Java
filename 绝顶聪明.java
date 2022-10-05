package 动态规划;

import org.omg.CORBA.MARSHAL;

public class 绝顶聪明 {
    public int Winner(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int sum1=f(nums,0,nums.length-1);
        int sum2=s(nums,0,nums.length-1);
        return Math.max(sum1,sum2);
    }
    public int f(int[] num,int i,int j) {
        if(i==j) {
            return num[i];
        }
        return Math.max(num[i]+s(num,i+1,j),num[j]+s(num,i,j-1));
    }
    public int s(int[] num,int i,int j) {
        if(i==j) {
            return 0;
        }
        return Math.min(f(num,i+1,j),f(num,i,j-1));
    }

    public int Winner2(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int N=nums.length;
        int[][] f=new int[N][N];
        int[][] s=new int[N][N];
        for(int i=0;i<N;i++) {
            f[i][i]=nums[i];
        }
        for (int i = 1; i <N ; i++) {
            int L=0;
            int R=i;
            while (L<N&&R<N) {
                f[L][R]=Math.max(nums[L]+s[L+1][R],nums[R]+s[L][R+1]);
                s[L][R]=Math.min(f[L+1][R],f[L][R+1]);
            }
            L++;
            R++;
        }
        return Math.max(f[0][N-1],s[0][N-1]);

    }
}
