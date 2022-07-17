package 数组;

public class 股票的最大利润 {
    public static int maxProfit(int[] prices) {
//        if(prices==null||prices.length<=1) {
//            return 0;
//        }
//        int res=0;
//        int min=prices[0];
//        for (int i = 1; i <prices.length ; i++) {
//            if(prices[i]<min) {
//                min=prices[i];
//            } else {
//                res=Math.max(res,prices[i]-min);
//            }
//        }
//        return res;
        if(prices==null||prices.length==0) {
                return 0;
        }
        int min=prices[0];
        int sum=0;
        for (int i = 1; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
            }
            int max=Integer.MIN_VALUE;
            for (int j = i; j <prices.length ; j++) {
                if(prices[j]>max&&prices[j]>min) {
                    max=prices[j];
                }
                if(max!=Integer.MIN_VALUE) {
                    sum=Math.max(sum,max-min);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a={7,1,5,3,6,4};
        int n=maxProfit(a);
        System.out.println(n);

    }
}
