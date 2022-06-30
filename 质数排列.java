public class 质数排列 {
    public int numPrimeArrangements(int n) {
        //统计质数
        int count=n-1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 2; j*j <=i ; j++) {
                if(i%j==0) {
                    count--;
                    break;
                }
            }
        }
        return getSeqNum(count)*getSeqNum(n-count)%(1000000000 + 7);

    }

    private int getSeqNum(int count) {
        int res=1;
        for (int i = 1; i <=count ; i++) {
            res*=i;
            res%=(1000000000 + 7);
        }
        return res;
    }
}
