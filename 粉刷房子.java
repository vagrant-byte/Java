package 数组;

import java.util.Arrays;

public class 粉刷房子 {
    public static int minCost(int[][] costs) {
        int row=costs.length;
        for (int i = 1; i <row ; i++) {
            costs[i][0]+=Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1]+=Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2]+=Math.min(costs[i-1][0],costs[i-1][1]);
        }
        return Math.min(costs[row-1][0],Math.min(costs[row-1][1],costs[row-1][2]));
    }

    public static void main(String[] args) {
        int[][] a={{3,5,3},{6,17,6},{7,13,18},{9,10,18}};
        int n=minCost(a);
        System.out.println(n);
    }
}
