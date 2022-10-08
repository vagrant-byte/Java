package 动态规划;

public class 绳子最多点数 {
    public int maxPoint(int[] arr,int L) {
        int left=0;
        int right=0;
        int N=arr.length;
        int max=0;
        while (left<N) {
            while (right<N&&arr[right]-arr[left]<=L) {
                right++;
            }
            max=Math.max(max,right-(left++));
        }
        return max;
    }
}
