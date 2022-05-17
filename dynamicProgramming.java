package 动态规划;

import java.util.HashMap;

public class dynamicProgramming {
    //斐波那契数列 递归实现
    public int fib(int n) {
        if(n==0||n==1) {
            return n;
        }
        return fib(n-1)+fib(n-2);
    }
    /*针对上述的递归实现复杂度过高,其中一些值进行了重复计算
    * 因此下面使用剪枝的操作，使用hashmao进行数值的存储避免重复计算*/
    private HashMap<Integer,Integer> map=new HashMap<>();
    public int fib1(int n) {
        if(n==0||n==1) {
            return n;
        }
        int pre=0;//保存n-1的值
        int ppre=0;//保存n-2的值
        if(map.containsKey(n-2)) {
            //hashmap中存在值 就取出
            ppre=map.get(n-2);
        } else {
            //不存在 计算 插入
            ppre=fib1(n-2);
            map.put(n-2,ppre);
        }
        if(map.containsKey(n-1)) {
            pre=map.get(n-1);
        } else {
            pre=fib1(n-1);
            map.put(n-1,pre);
        }
        return (pre+ppre)%1000000007;
    }
    //使用迭代的方法实现
    public int fib2(int n) {
        if(n==0) {
            return 0;
        }
        int first=1;
        int second=1;
        int third=1;
        while (n>2) {
            third=first+second;
            first=second;
            second=third;
            n--;
        }
        return third;
    }
    //动归实现
    public int fib3(int n) {
        if(n==0||n==1) {
            return n;
        }
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for (int i = 2; i <=2 ; i++) {
            dp[i]=(dp[i-1]+dp[i-2]);
        }
        return dp[n];
    }
    //矩形覆盖
    public int rectCover(int target) {
        if(target<2) {
            return target;
        }
        int[] dp=new int[target+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=target;i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[target];
    }
    //青蛙跳台阶
    public int numWays(int n) {
        if(n<=1) {
            return 1;
        }
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) {
            dp[i]=(dp[i-1]+dp[i-2])%1000000007;
        }
        return dp[n];

    }
}
