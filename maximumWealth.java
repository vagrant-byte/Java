import java.util.Scanner;

public class maximumWealth {
    //最富有客户的资产总量
    public static int maximumWealth(int[][] accounts) {
        int i=accounts.length;
        int j=accounts[0].length;
        int max=0;
        for (int k = 0; k <i ; k++) {
            int sum=0;
            for (int l = 0; l <j ; l++) {
                sum+=accounts[k][l];
            }
            if(sum>max) {
                max=sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] a={{1,5},{7,3},{3,5}};
        System.out.println(maximumWealth(a));
    }

}
