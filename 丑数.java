public class 丑数 {
//这个题用三指针，第一个丑数是1，以后的丑数都是基于前面的小丑数分别乘2，3，5构成的。
// 我们每次添加进去一个当前计算出来个三个丑数的最小的一个，并且是谁计算的，谁指针就后移一位
    public int nthUglyNumber(int n) {
        if(n<=0) {
            return -1;
        }
        int[] dp=new int[n];
        dp[0]=1;
        int id2=0;
        int id3=0;
        int id5=0;
        for (int i = 1; i <n ; i++) {
            dp[i]=Math.min(dp[id2]*2,Math.min(dp[id3]*3,dp[id5]*5));
            if(dp[id2]*2==dp[i]) {
                id2+=1;
            }
            if(dp[id3]*3==dp[i]) {
                id3++;
            }
            if(dp[id5]*5==dp[i]) {
                id5++;
            }
        }
        return dp[n-1];


    }
}
