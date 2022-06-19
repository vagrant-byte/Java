import java.util.Arrays;

public class 你可以获得的最大硬币数目 {
    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int res=0;
        int n=piles.length/3;
        int i=piles.length-2;
        while (n>0) {
            res+=piles[i];
            i-=2;
            n--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] piles={9,8,7,6,5,1,2,3,4};
        System.out.println(maxCoins(piles));
    }
}
