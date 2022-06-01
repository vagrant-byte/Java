import java.util.Arrays;

//火柴拼正方形
public class makesquare {
    public static boolean makesquare(int[] matchsticks) {
        int n=matchsticks.length;
        if(n<4) {
            return false;
        }
        int sum=0;
        for (int num:matchsticks) {
            sum+=num;
        }
        if(sum%4!=0) {
            return false;
        }
        Arrays.sort(matchsticks);
        return allocate(matchsticks,n-1,new int[4],sum/4);
    }

    private static boolean allocate(int[] matchsticks, int pos, int[] sums, int avg) {
        if(pos==-1) {
            return sums[0]==avg&&sums[1]==avg&&sums[2]==avg;
        }
        for (int i = 0; i < 4; i++) {
            if(sums[i]+matchsticks[pos]>avg) {
                continue;
            }
            sums[i]+=matchsticks[pos];
            if (allocate(matchsticks,pos-1,sums,avg)) {
                return true;
            }
            sums[i]-=matchsticks[pos];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a={1,1,2,2,2};
        boolean b=makesquare(a);
    }

}
