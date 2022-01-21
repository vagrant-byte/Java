import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Day47 {
    //马戏团
    public  static class people {
        public int height;
        public int weight;
        public people(int height,int weight) {
            this.height=height;
            this.weight=weight;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            people[] peoples=new people[n];
            for (int i = 0; i <n ; i++) {
                int index=sc.nextInt();
                peoples[index-1]=new people(sc.nextInt(),sc.nextInt());
            }
            //按身高排序
            Arrays.sort(peoples, new Comparator<people>() {
                @Override
                public int compare(people o1, people o2) {
                    int res=Integer.compare(o1.height,o2.height);
                    if(res!=0) {
                        return res;
                    } else {
                        return Integer.compare(o1.weight,o2.weight);
                    }
                }
            });
            int[] dp=new int[n];
            int max=Integer.MIN_VALUE;
            for (int i = 0; i <n ; i++) {
                dp[i]=1;
                for (int j = i-1; j >=0 ; j--) {
                    if(peoples[i].weight>peoples[j].weight || (peoples[i].weight==peoples[j].weight)&& peoples[i].height==peoples[j].height) {
                        dp[i]=Math.max(dp[i],dp[j]+1);
                    }
                    max=Math.max(max,dp[i]);
                }
            }
            System.out.println(max);
        }
    }
}
